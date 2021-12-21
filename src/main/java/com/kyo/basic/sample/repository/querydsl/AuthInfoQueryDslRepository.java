package com.kyo.basic.sample.repository.querydsl;

import com.kyo.basic.sample.repository.entity.AuthInfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.kyo.basic.sample.repository.entity.QAuthInfo.authInfo;

@Repository
@RequiredArgsConstructor
public class AuthInfoQueryDslRepository {

    private final JPAQueryFactory queryFactory;

    public AuthInfo getAuthInfoByMappingId(String mappingId) {
        return queryFactory
                .select(
                        authInfo
                )
                .from(authInfo)
                .where(
                        authInfo.mappingId.eq(mappingId)
                )
                .limit(1)
                .fetchFirst();
    }

}
