package com.kyo.basic.base.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kyo.basic.base.controller.request.CommonRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AES256UtilsTest {

    @Test
    void encryptTest() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        //String encKey = RandomByteUtils.makeKey();
        String encKey = "63b56bf523efe519";
        String mappingId = "bc7fabd7-acd1-4995-b478-675b5ee5a809";

        CommonRequestDto dto = CommonRequestDto.builder()
                .mappingId(mappingId).build();

        AES256Utils utils = new AES256Utils(encKey);

        String data = objectMapper.writeValueAsString(dto);
        System.out.println("data = " + data);

        String encrypt = utils.encrypt(data);
        System.out.println("encrypt = " + encrypt);

        String decrypt = utils.decrypt(encrypt);
        System.out.println("decrypt = " + decrypt);

        CommonRequestDto origin = objectMapper.readValue(decrypt, CommonRequestDto.class);

        assertEquals(dto.getMappingId(), origin.getMappingId());

    }

}