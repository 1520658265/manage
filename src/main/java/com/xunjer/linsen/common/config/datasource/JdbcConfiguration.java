package com.xunjer.linsen.common.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author linsen
 * @date 2020/3/16 19:17
 * @tips 明日复明日 明日何其多
 */
@Configuration
public class JdbcConfiguration {

    /**
     * 简单SQL查询
     */
    @Bean(name = "jdbc_linsen")
    public JdbcTemplate jdbc_picture_book(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 参数化SQL查询【防QL注入】
     */
    @Bean(name = "jdbc_param_linsen")
    public NamedParameterJdbcTemplate jdbc_param_picture_book(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * 数据库事务
     */
    @Bean(name = "jdbc_transaction_linsen")
    public DataSourceTransactionManager jdbc_transaction_picture_book(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
