package xyz.qumn.lb.common.mybatis.test.dao;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import org.apache.ibatis.session.SqlSession;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.qumn.lb.common.mybatis.test.dao.rule.SqlSessionProvider;
import xyz.qumn.lb.common.mybatis.test.util.MybatisPlusUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@RunWith(JUnit4.class)
@DBUnit(escapePattern = "`?`", schema = "lb")
public class BaseDaoTest {
    @Rule(order = 999)
    public SqlSessionProvider ssProvide = new SqlSessionProvider();
    protected static Logger log = LoggerFactory.getLogger(BaseDaoTest.class.getName());
    private static DataSource dataSource = MybatisPlusUtil.getDataSource();
    private static Connection connection;

    static {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static volatile boolean Initialed = false;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.
            instance(connection);


    @BeforeClass
    public static void initMigration() throws SQLException {
        if (!Initialed) {
            synchronized (BaseDaoTest.class) {
                if (!Initialed) {
                    ClassicConfiguration conf = new ClassicConfiguration();
                    conf.setDataSource(dataSource);
                    Flyway flyway = new Flyway(conf);
                    flyway.migrate();
                    Initialed = true;
                }
            }
        }
    }

    public SqlSession getSession() {
        return ssProvide.getSqlSession();
    }

    @Test
    public void shouldWorked() {
        log.info("test");
    }
}
