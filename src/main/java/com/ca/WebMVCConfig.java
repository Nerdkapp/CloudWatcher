//package com.ca;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//@Configuration
//@EnableWebMvc
//public class WebMVCConfig extends WebMvcConfigurerAdapter {
//
//  // equivalents for <mvc:resources/> tags
//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//  }
//
//  @Override
//  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//    configurer.enable();
//  }
//}