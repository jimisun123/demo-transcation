package com.jimisun.xatranscation.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author jimisun
 * @create 2022-08-30 13:35
 **/
@Configuration
@MapperScan(basePackages = "com.jimisun.xatranscation.mapper.jimisun",
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
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("jimisunDataSource");
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        return atomikosDataSourceBean;
    }

    @Bean(name = "jimisunSqlSessionFactory")
    public SqlSessionFactory
    testSqlSessionFactory(@Qualifier("jimisunDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations
                (new PathMatchingResourcePatternResolver().
                        getResources("classpath:myabtis/mapper/jimisun/*.xml"));
        return bean.getObject();
    }




}
