package io.github.sbracely.responseformats.config;

import io.github.sbracely.responseformats.media.MediaTypes;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.http.converter.autoconfigure.ServerHttpMessageConvertersCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.JacksonJsonHttpMessageConverter;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@Configuration
public class HttpMessageConvertersConfig {

    @Bean
    ServerHttpMessageConvertersCustomizer jsonHttpMessageConverters(JsonMapper jsonMapper) {
        JacksonJsonHttpMessageConverter wrappedConverter =
                createWrappedJsonConverter(jsonMapper);
        JacksonJsonHttpMessageConverter nonNullConverter =
                createNonNullJsonConverter(jsonMapper);

        return builder -> builder
                .addCustomConverter(wrappedConverter)
                .addCustomConverter(nonNullConverter);
    }

    private JacksonJsonHttpMessageConverter createWrappedJsonConverter(JsonMapper jsonMapper) {
        JsonMapper wrapped = jsonMapper.rebuild()
                .enable(SerializationFeature.WRAP_ROOT_VALUE)
                .build();
        JacksonJsonHttpMessageConverter converter =
                new JacksonJsonHttpMessageConverter(wrapped);
        converter.setSupportedMediaTypes(List.of(
                MediaTypes.WRAPPED_JSON));
        return converter;
    }

    private JacksonJsonHttpMessageConverter createNonNullJsonConverter(JsonMapper jsonMapper) {
        JsonMapper includeNonNullJsonMapper = jsonMapper.rebuild()
                .changeDefaultPropertyInclusion(inclusion ->
                        inclusion.withValueInclusion(JsonInclude.Include.NON_NULL))
                .build();
        JacksonJsonHttpMessageConverter includeNonNullConverter =
                new JacksonJsonHttpMessageConverter(includeNonNullJsonMapper);
        includeNonNullConverter.setSupportedMediaTypes(List.of(
                MediaTypes.NON_NULL_JSON
        ));
        return includeNonNullConverter;
    }
}