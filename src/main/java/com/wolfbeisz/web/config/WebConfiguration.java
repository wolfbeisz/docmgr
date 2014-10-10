package com.wolfbeisz.web.config;

import java.io.IOException;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.context.embedded.MultiPartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wolfbeisz.web.service.FileManager;
import com.wolfbeisz.web.service.LocalFileManager;

@Configuration
public class WebConfiguration {
	@Bean
    MultipartConfigElement multipartConfigElement() {
        MultiPartConfigFactory factory = new MultiPartConfigFactory();
        factory.setMaxFileSize("128KB");
        factory.setMaxRequestSize("128KB");
        return factory.createMultipartConfig();
    }
	
	@Bean
	public FileManager fileManager() throws IOException
	{
		return LocalFileManager.get("docs");
	}
}
