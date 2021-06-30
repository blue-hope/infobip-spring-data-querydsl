package com.infobip.spring.data.common;

import com.querydsl.sql.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.*;

@Configuration
public class InfobipSpringDataCommonConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public SQLTemplates sqlTemplates(DataSource dataSource) throws SQLException {
        SQLTemplatesRegistry sqlTemplatesRegistry = new SQLTemplatesRegistry();
        DatabaseMetaData metaData;
        try (Connection connection = dataSource.getConnection()) {
            metaData = connection.getMetaData();
        }

        return sqlTemplatesRegistry.getTemplates(metaData);
    }
}
