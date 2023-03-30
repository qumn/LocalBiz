package xyz.qumn.lb.management.core.dao;

import com.github.database.rider.core.DBUnitRule;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import xyz.qumn.lb.management.core.dao.rule.SqlSessionProvider;
import xyz.qumn.lb.management.core.util.MybatisPlusUtil;

import javax.sql.DataSource;
import java.sql.SQLException;


@RunWith(JUnit4.class)
public class BaseDaoTest {
    @Rule(order = 999)
    public SqlSessionProvider ssProvide = new SqlSessionProvider();
    private static DataSource dataSource = MybatisPlusUtil.getDataSource();
    private static volatile boolean Initialed = false;

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.
            instance(() -> dataSource.getConnection());


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

}
