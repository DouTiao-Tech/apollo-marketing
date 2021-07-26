package com.doutiaotech.apollo.web;

import com.doutiaotech.apollo.core.utils.DateTimeUtils;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebPortalAutoConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> {
            builder.serializers(new LocalDateTimeSerializer(DateTimeUtils.DATE_TIME));
            builder.serializers(new LocalDateSerializer(DateTimeUtils.DATE));
            builder.serializers(new LocalTimeSerializer(DateTimeUtils.TIME));
        };
    }
}
