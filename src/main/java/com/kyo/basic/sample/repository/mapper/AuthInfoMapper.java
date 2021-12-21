package com.kyo.basic.sample.repository.mapper;

import com.kyo.basic.sample.repository.dto.AuthInfoDto;
import com.kyo.basic.sample.repository.entity.AuthInfo;
import org.mapstruct.Mapper;

/**
 * MapStruct
 *  - 객체의 타입 변환 시에 유용하게 사용할 수 있는 라이브러리
 *  - @Mapper 가 붙은 인터페이스는 MapStruct Code Generator가 해당 인터페이스의 구현체를 생성
 *      - 매핑될 속성명이 다를 경우 @Mapping 어노테이션을 통해 매핑정보를 맞춰야 함
 *      - Dependency Injection 사용 가능
 *
 */
@Mapper(componentModel = "spring")
public interface AuthInfoMapper {

    default AuthInfoDto authInfoToAuthInfoDto(AuthInfo authInfo) {
        return AuthInfoDto.builder()
                .mappingId(authInfo.getMappingId())
                .encKey(authInfo.getEncKey())
                .build();
    }


}
