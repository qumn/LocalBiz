package xyz.qumn.lb.common.mybatis.test.util;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.EnumTypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.BeforeClass;
import org.locationtech.jts.geom.Geometry;
import xyz.qumn.lb.common.mybatis.test.dao.BaseDaoTest;
import xyz.qumn.lb.common.mybatis.GeometryTypeHandler;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class MybatisPlusUtil {
    public static String DAO_PACKAGE = "xyz.qumn.lb.management.core.dao";
    public static String JDBC_URL = "jdbc:mysql://localhost:3304/lb?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8";
    public static String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static String USER = "root";
    public static String PASSWORD = "root";
    private static DataSource DATA_SOURCE = null;
    private static SqlSessionFactory SQL_SESSION_FACTORY = null;

    @BeforeClass
    public static void init() {
        initSqlSessionFactory();
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        if (SQL_SESSION_FACTORY == null) {
            synchronized (BaseDaoTest.class) {
                if (SQL_SESSION_FACTORY != null) {
                    return SQL_SESSION_FACTORY;
                }
                initSqlSessionFactory();
            }
        }
        return SQL_SESSION_FACTORY;
    }

    public static DataSource getDataSource() {
        if (DATA_SOURCE == null) {
            synchronized (BaseDaoTest.class) {
                if (DATA_SOURCE != null) {
                    return DATA_SOURCE;
                }
                HikariDataSource dataSource = new HikariDataSource();
                dataSource.setJdbcUrl(JDBC_URL);
                dataSource.setDriverClassName(DRIVER_CLASS_NAME);
                dataSource.setUsername(USER);
                dataSource.setPassword(PASSWORD);
                dataSource.setIdleTimeout(60000);
                dataSource.setAutoCommit(true);
                dataSource.setMaximumPoolSize(5);
                dataSource.setMinimumIdle(1);
                dataSource.setMaxLifetime(60000 * 10);
                dataSource.setConnectionTestQuery("SELECT 1");
                DATA_SOURCE = dataSource;
            }
        }
        return DATA_SOURCE;
    }

    private static void initSqlSessionFactory() {
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("Production", transactionFactory, getDataSource());
        MybatisConfiguration conf = new MybatisConfiguration(environment);
        registryMapperXml(conf, "mapper");
        initConfiguration(conf);
        conf.setLogImpl(StdOutImpl.class);
        conf.addMappers(DAO_PACKAGE);
        conf.addInterceptor(new MybatisPlusInterceptor());
        registerTypeHandler(conf);
        GlobalConfigUtils.getGlobalConfig(conf).setIdentifierGenerator(new DefaultIdentifierGenerator());
        SQL_SESSION_FACTORY = new MybatisSqlSessionFactoryBuilder().build(conf);
    }

    private static void registerTypeHandler(MybatisConfiguration conf) {
        TypeHandlerRegistry typeHandlerRegistry = conf.getTypeHandlerRegistry();
        typeHandlerRegistry.register(Geometry.class, GeometryTypeHandler.class);
        typeHandlerRegistry.register(Enum.class, EnumTypeHandler.class);
    }

    private static void initConfiguration(MybatisConfiguration configuration) {
        //开启驼峰大小写转换
        configuration.setMapUnderscoreToCamelCase(true);
        //配置添加数据自动返回数据主键
        configuration.setUseGeneratedKeys(true);
    }

    /**
     * 解析mapper.xml文件
     *
     * @param configuration
     * @param classPath
     * @throws IOException
     */
    private static void registryMapperXml(MybatisConfiguration configuration, String classPath) {
        try {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            Enumeration<URL> mapper = null;
            mapper = contextClassLoader.getResources(classPath);
            while (mapper.hasMoreElements()) {
                URL url = mapper.nextElement();
                if (url.getProtocol().equals("file")) {
                    String path = url.getPath();
                    File file = new File(path);
                    File[] files = file.listFiles();
                    for (File f : files) {
                        FileInputStream in = new FileInputStream(f);
                        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(in, configuration, f.getPath(), configuration.getSqlFragments());
                        xmlMapperBuilder.parse();
                        in.close();
                    }
                } else {
                    JarURLConnection urlConnection = (JarURLConnection) url.openConnection();
                    JarFile jarFile = urlConnection.getJarFile();
                    Enumeration<JarEntry> entries = jarFile.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry jarEntry = entries.nextElement();
                        if (jarEntry.getName().endsWith(".xml")) {
                            InputStream in = jarFile.getInputStream(jarEntry);
                            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(in, configuration, jarEntry.getName(), configuration.getSqlFragments());
                            xmlMapperBuilder.parse();
                            in.close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
