package com.example.wrap.response.config;

import org.springframework.boot.http.converter.autoconfigure.ServerHttpMessageConvertersCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@Configuration
public class WrappedJsonConfig {

    @Bean
    ServerHttpMessageConvertersCustomizer wrappedJson(JsonMapper jsonMapper) {
        JsonMapper wrapped = jsonMapper.rebuild()
                .enable(SerializationFeature.WRAP_ROOT_VALUE)
                .build();

        JacksonJsonHttpMessageConverter converter =
                new JacksonJsonHttpMessageConverter(wrapped);
        converter.setSupportedMediaTypes(List.of(
                MediaType.valueOf("application/vnd.example.wrapped+json")));

        return builder -> builder.addCustomConverter(converter);
    }
}