package com.hugh.authentication.core.autoconfig;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 数据库配置
 * @author luoyulin1
 */
@Configuration
@MapperScan(basePackages = "com.hugh.authentication.core.infrastructure.mysql.*.mapper", sqlSessionTemplateRef  = DataSourceConfigurer.CORE_SQL_SESSION_TEMPLATE)
@Slf4j
public class DataSourceConfigurer {

    static final String CORE_SQL_SESSION_TEMPLATE = "coreSqlSessionTemplate";


    @Bean(name = "coreDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.core")
    public DataSource setDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "coreTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("coreDataSource") DataSource dataSource) {
        log.info("初始化事务管理器...");
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "coreSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("coreDataSource") DataSource dataSource) throws Exception {
        log.info("初始化sql会话工厂...");
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = CORE_SQL_SESSION_TEMPLATE)
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("coreSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        log.info("初始化sql会话模版...");
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
