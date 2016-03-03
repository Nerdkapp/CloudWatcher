package com.ca;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

@Configuration
public class AppConfig {
  @Bean
  public AWSCredentials awsCredentials() throws IOException {
    return new PropertiesCredentials(getClass().getClassLoader().getResourceAsStream("awsCredentials.properties"));
  }
}