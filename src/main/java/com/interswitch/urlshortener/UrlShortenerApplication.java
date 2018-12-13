package com.interswitch.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@SpringBootApplication
public class UrlShortenerApplication {

//	@Bean
//	JdbcTemplate getJdbcTemplate() {
//		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//		dataSource.setDriver(new org.apache.derby.jdbc.ClientDriver());
//		String dbName = "urlshortner";
//		String connectionURL = "jdbc:derby://172.31.29.133:1527/" + dbName
//				+ ";create=true";
//		dataSource.setUrl(connectionURL);
//		dataSource.setUsername("SA");
//		dataSource.setPassword("SA");
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//
//		return jdbcTemplate;
//	}

	public static void main(String[] args) {
		SpringApplication.run(UrlShortenerApplication.class, args);
	}

}

