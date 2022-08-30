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
 * @create 2022-08-30 13:36
 **/
@Configuration
@MapperScan(basePackages = "com.jimisun.xatranscation.mapper.jimisun2",
        sqlSessionFactoryRef  = "jimisun2SqlSessionFactory")
public class DataSource2Config {

    @Value("${spring.datasource.test2.jdbc-url}")
    private String url;

    @Value("${spring.datasource.test2.username}")
    private String username;

    @Value("${spring.datasource.test2.password}")
    private String password;

    @Bean(name = "jimisun2DataSource")
    public DataSource testDataSource() {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(url);
        mysqlXADataSource.setUser(username);
        mysqlXADataSource.setPassword(password);
        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setUniqueResourceName("jimisun2DataSource");
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        return atomikosDataSourceBean;
    }

    @Bean(name = "jimisun2SqlSessionFactory")
    public SqlSessionFactory
    testSqlSessionFactory
            (@Qualifier("jimisun2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations
                (new PathMatchingResourcePatternResolver().
                        getResources("classpath:myabtis/mapper/jimisun2/*.xml"));
        return bean.getObject();
    }


}
