package xyz.qumn.lb.management.core.dao.rule;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import xyz.qumn.lb.management.core.util.MybatisPlusUtil;

import java.sql.Connection;


// 每一个测试方法都会创建一个新的SqlSession
public class SqlSessionProvider implements TestRule {
    private final static SqlSessionFactory ssf = MybatisPlusUtil.getSqlSessionFactory();
    private SqlSession sqlSession; // one session per test method

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    sqlSession = ssf.openSession();
                    base.evaluate();
                } finally {
                    sqlSession.clearCache();
                    sqlSession.close();
                }
            }
        };
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }
    public <T> T getMapper(Class<T> type){
        return sqlSession.getMapper(type);
    }

    public void commit() {
        sqlSession.commit();
    }

    public void rollback() {
        sqlSession.rollback();
    }
}
