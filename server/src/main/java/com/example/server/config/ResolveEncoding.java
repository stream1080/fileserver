package com.example.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 *
 * 转码配置类
 *
 * @author stream
 * @since 2021/11/2 22:36
 */
@Configuration
public class ResolveEncoding extends WebMvcConfigurerAdapter {
        @Bean
        public HttpMessageConverter<String> responseBodyConverter() {
            return new StringHttpMessageConverter(
                    StandardCharsets.UTF_8);
        }

        @Override
        public void configureMessageConverters(
                List<HttpMessageConverter<?>> converters) {
            super.configureMessageConverters(converters);
            converters.add(responseBodyConverter());
        }

        @Override
        public void configureContentNegotiation(
                ContentNegotiationConfigurer configurer) {
            configurer.favorPathExtension(false);
        }

}
