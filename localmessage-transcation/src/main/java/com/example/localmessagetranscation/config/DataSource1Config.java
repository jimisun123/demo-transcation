package com.example.localmessagetranscation.config;

import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author jimisun
 * @create 2022-08-30 13:35
 **/
@Configuration
@MapperScan(basePackages = "com.example.localmessagetranscation.mapper.jimisun",
        sqlSessionFactoryRef = "jimisunSqlSessionFactory")
public class DataSource1Config {

    @Value("${spring.datasource.test1.jdbc-url}")
    private String url;

    @Value("${spring.datasource.test1.username}")
    private String username;

    @Value("${spring.datasource.test1.password}")
    private String password;

    @Bean(name = "jimisunDataSource")
    public DataSource testDataSource() {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(url);
        mysqlXADataSource.setUser(username);
        mysqlXADataSource.setPassword(password);
        return mysqlXADataSource;
    }

    @Bean(name = "jimisunSqlSessionFactory")
    public SqlSessionFactory
    testSqlSessionFactory(@Qualifier("jimisunDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations
                (new PathMatchingResourcePatternResolver().
                        getResources("classpath:mapper/jimisun/*.xml"));
        return bean.getObject();
    }


    @Bean(name = "jimisunTransactionManager")
    public DataSourceTransactionManager testTransactionManager(
            @Qualifier("jimisunDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }



}
