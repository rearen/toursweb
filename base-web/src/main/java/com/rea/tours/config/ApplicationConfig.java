package com.rea.tours.config;

import com.github.pagehelper.PageInterceptor;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@ComponentScans({@ComponentScan("com.rea.tours.service"), @ComponentScan("com.rea.tours.security")})
@PropertySource(value = "classpath:jdbcConfig.properties",encoding = "UTF-8")
@MapperScan("com.rea.tours.dao")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class ApplicationConfig {

    @Value("${jdbc.driver}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String user;
    @Value("${jdbc.password}")
    private String password;
    @Value("${c3p0.maxPoolSize}")
    private int maxPoolSize;
    @Value("${c3p0.minPoolSize}")
    private int minPoolSize;
    @Value("${c3p0.checkoutTimeout}")
    private int checkoutTimeout;
    @Value("${c3p0.acquireRetryAttempts}")
    private int acquireRetryAttempts;
    @Value("${c3p0.autoCommitOnClose}")
    private boolean autoCommitOnClose;

//    @Bean(destroyMethod = "close")
    @Bean(destroyMethod="close")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setMaxPoolSize(maxPoolSize);
        dataSource.setMinPoolSize(minPoolSize);
        dataSource.setCheckoutTimeout(checkoutTimeout);
        dataSource.setAcquireRetryAttempts(acquireRetryAttempts);
        dataSource.setAutoCommitOnClose(autoCommitOnClose);
        dataSource.close();
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory=new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setPlugins(new Interceptor[]{pageHelper()});
        return sqlSessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PageInterceptor pageHelper() {
        PageInterceptor pageHelper=new PageInterceptor();
        Properties p=new Properties();
        p.setProperty("helperDialect","mysql");
        p.setProperty("reasonable","true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

}
