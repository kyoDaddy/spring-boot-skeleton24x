package com.kyo.basic.process.repository.book;

import com.kyo.basic.process.vo.table.book.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserRepository {

    /* sqlSession */
    @Resource(name="book-sst")
    private SqlSession sqlSession;

    /* TRANSACTION MANAGER */
    @Qualifier("book-tm")
    private DataSourceTransactionManager transactionManager;

    private final String userMapperNameSpace = "com.kyo.basic.process.mappers.book.userMapper.";

    /* 사용자 정보 (목록) */
    public List<UserVo> selectUser(UserVo vo) {
        return this.sqlSession.selectList(userMapperNameSpace + "selectUser", vo);
    }

    /* 사용자 정보 (단일) */
    public UserVo getUser(UserVo vo) {
        return this.sqlSession.selectOne(userMapperNameSpace + "selectUser", vo);
    }

    /* 사용자 가입 */
    public int saveUser(UserVo vo) {
        return this.sqlSession.insert(userMapperNameSpace + "insertUser", vo);
    }

    /* 사용자 정보 수정 */
    public int updateUser(UserVo vo) {
        return this.sqlSession.insert(userMapperNameSpace + "updateUser", vo);
    }
}
