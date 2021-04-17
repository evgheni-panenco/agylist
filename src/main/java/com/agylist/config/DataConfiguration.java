package com.agylist.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConfiguration {
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().driverClassName("org.postgresql.Driver")
				.url("jdbc:postgresql://localhost:5432/agylist").username("postgres").password("postgres").build();
	}
}
