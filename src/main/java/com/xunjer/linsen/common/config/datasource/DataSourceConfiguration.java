package com.xunjer.linsen.common.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author linsen
 * @date 2020/3/16 19:18
 * @tips 明日复明日 明日何其多
 */
@Configuration
public class DataSourceConfiguration {

    @Bean(name = "primaryDataSource")
    @Primary
    public DataSource primaryDataSource(@Value("${spring.datasource.alinsen.driver-class-name}") String driver,
                                        @Value("${spring.datasource.alinsen.url}") String url,
                                        @Value("${spring.datasource.alinsen.username}") String username,
                                        @Value("${spring.datasource.alinsen.password}") String password) {
        return this.newDataSource(driver, url, username, password);
    }



    private DataSource newDataSource(String driver, String url, String userName, String passWord) {
        DruidDataSource druidDataSource = this.getInitParam();
        druidDataSource.setDriverClassName(driver);
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(userName);
        druidDataSource.setPassword(passWord);
        return druidDataSource;
    }

    /**
     * 获得初始化的数据源对象
     */
    private DruidDataSource getInitParam() {
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }


}
