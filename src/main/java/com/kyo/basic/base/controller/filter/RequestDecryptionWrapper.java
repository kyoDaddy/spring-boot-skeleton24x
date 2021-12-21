package com.kyo.basic.base.controller.filter;

import com.kyo.basic.base.constant.CommonConstants;
import com.kyo.basic.base.utils.AES256Utils;
import com.kyo.basic.sample.repository.entity.AuthInfo;
import com.kyo.basic.sample.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class RequestDecryptionWrapper extends HttpServletRequestWrapper {


    private final Charset encoding;
    private String decodingBody;
    private byte[] rawData;

    public RequestDecryptionWrapper(HttpServletRequest request, AuthService authService) {
        super(request);
        final String charEncoding = request.getCharacterEncoding();
        final String uniqueKey = request.getHeader(CommonConstants.REQ_HEADER_UNIQUE_KEY);

        this.encoding = ObjectUtils.isEmpty(charEncoding) ? StandardCharsets.UTF_8 : Charset.forName(charEncoding);

        try {
            AuthInfo authInfo = authService.getAuthInfo(uniqueKey);
            final String encKey = authInfo == null ? null : authInfo.getEncKey();

            InputStream inputStream = request.getInputStream();
            rawData = IOUtils.toByteArray(inputStream);

            // check body value
            if (ObjectUtils.isEmpty(rawData)) return;
            // check encKey by db
            else if (!StringUtils.hasText(encKey)) return;

            AES256Utils utils = new AES256Utils(encKey);
            this.decodingBody = utils.decrypt(new String(rawData, StandardCharsets.UTF_8));
            log.info("decrypt : {}, {}", this.decodingBody, request.getContentType());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decodingBody == null ? "".getBytes(encoding) : decodingBody.getBytes(encoding));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }


}
