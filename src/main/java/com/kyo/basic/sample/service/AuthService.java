package com.kyo.basic.sample.service;

import com.kyo.basic.base.enumeraion.Result;
import com.kyo.basic.base.exceptiion.CustomException;
import com.kyo.basic.base.utils.RandomByteUtils;
import com.kyo.basic.sample.repository.AuthInfoRepository;
import com.kyo.basic.sample.repository.RedisAuthInfoRepository;
import com.kyo.basic.sample.repository.dto.AuthInfoDto;
import com.kyo.basic.sample.repository.entity.AuthInfo;
import com.kyo.basic.sample.repository.mapper.AuthInfoMapper;
import com.kyo.basic.sample.repository.querydsl.AuthInfoQueryDslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final RedisAuthInfoRepository redisAuthInfoRepository;
    private final AuthInfoQueryDslRepository authInfoQueryDslRepository;
    private final AuthInfoRepository authInfoRepository;
    private final AuthInfoMapper authInfoMapper;
    private final WebClient webClient;


    /**
     *  o header uniqueKey 기준으로 db 조회
     *  o 매핑값(uuid) 생성 후 DB 등록 완료
     *  o 암복호화 규칙
     *      > (key->randomByteHex(16자리), AES/CBC/PKCS5Padding 우선 AES256Utils 만들어둠)
     */
    public AuthInfoDto check(String uniqueKey) {

        return authInfoRepository.getAuthInfoByUniqueKey(uniqueKey)
                .map(authInfo -> {
                    // 호출 시 마다 암호화 키/수정일시 갱신
                    authInfo.setEncKey(RandomByteUtils.makeKey());
                    authInfo.setEncKeyUpdatedAt(LocalDateTime.now());
                    // 사용자 정보 갱신
                    // redis 갱신
                    redisAuthInfoRepository.save(authInfoMapper.authInfoToRedisAuthInfo(authInfo));
                    //log.info("authinfo mappingId {}, redis patch {}", authInfo.getMappingId(), save.getId());
                    // mysql 갱신
                    return authInfoMapper.authInfoToAuthInfoDto(authInfoRepository.save(authInfo));
                })
                .orElseGet(() -> makeAuthInfo(uniqueKey));

    }

    private AuthInfoDto makeAuthInfo(String uniqueKey) {

        String mappingId = UUID.randomUUID().toString();
        // encryption key 생성
        String encKey = RandomByteUtils.makeKey();

        AuthInfo authInfo = AuthInfo.builder()
                .uniqueKey(uniqueKey)
                .mappingId(mappingId)
                .encKey(encKey)
                .encKeyUpdatedAt(LocalDateTime.now())
                .build();

        // redis 저장
        redisAuthInfoRepository.save(authInfoMapper.authInfoToRedisAuthInfo(authInfo));
        //log.info("authinfo mappingId {}, redis save {}", authInfo.getMappingId(), save.getId());
        // mysql 저장
        return authInfoMapper.authInfoToAuthInfoDto(
                authInfoRepository.save(authInfo)
        );
    }

    public AuthInfoDto getAuthInfo(String uniqueKey, String mappingId) {
        return Optional.ofNullable(authInfoQueryDslRepository.getAuthInfoByMappingId(mappingId))
                .filter(authInfo -> authInfo.getMappingId().equals(mappingId))
                .map(authInfoMapper::authInfoToAuthInfoDto)
                .orElseThrow(() -> new CustomException(Result.NOT_EXISTS, String.format("npsn:%s, mapping-id:%s", uniqueKey, mappingId)));
    }

    public AuthInfo getAuthInfo(String uniqueKey) {
        // redis 조회 후 db 조회
        return redisAuthInfoRepository.findRedisAuthInfoByUniqueKey(uniqueKey)
                .map(redisAuthInfo -> authInfoMapper.redisAuthInfoDtoToAuthInfo(redisAuthInfo))
                .orElseGet(() -> authInfoRepository.getAuthInfoByUniqueKey(uniqueKey).orElse(null));
    }


}
