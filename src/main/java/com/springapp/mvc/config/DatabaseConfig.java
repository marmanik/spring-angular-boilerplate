package com.springapp.mvc.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@PropertySource("classpath:system.properties")
public class DatabaseConfig {

    DataSource dataSource;

    @Autowired
    Environment environment;

    DataSource getDataSource() {
        if (dataSource == null) {
            try {
                dataSource = dataSource();
                return dataSource;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }

    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource() throws IOException {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setDefaultAutoCommit(true);
        return dataSource;

    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate() throws IOException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate;
    }
}
