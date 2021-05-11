package com.kyo.basic.process.repository.test;

import com.kyo.basic.process.vo.table.book.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class TestRepository {

    /* sqlSession */
    @Resource(name="test-sst")
    private SqlSession sqlSession;

    /* TRANSACTION MANAGER */
    @Qualifier("test-tm")
    private DataSourceTransactionManager transactionManager;

    private final String testMapperNameSpace = "com.kyo.basic.process.mappers.test.testMapper.";

    /* 사용자 테스트 목록 */
    public UserVo selectTestUser() {
        return this.sqlSession.selectOne(testMapperNameSpace + "selectTestUser");
    }
}
