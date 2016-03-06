package com.ca;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {
  @Bean
  public AWSCredentials awsCredentials() throws IOException {
    return new PropertiesCredentials(getClass().getClassLoader().getResourceAsStream("awsCredentials.properties"));
  }
}