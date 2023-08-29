package com.ses.test3.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

//@EnableWebMvc
@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {

//	resource.path=file:///‪c:/upload/
	@Value("${spring.servlet.multipart.location}")
	String resourcePath;
	
//	upload.path=/upload/**
	@Value("${upload.path}")
	String uploadPath;

	//config
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.debug("* WebConfig_var= resourcePath: {}, uploadPath: {}", resourcePath, uploadPath);
		
		registry.addResourceHandler("uploadPath").addResourceLocations("file:///‪C:/upload/");
	}
	
}
