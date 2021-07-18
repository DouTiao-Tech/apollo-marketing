package com.doutiaotech.apollo.core.utils;

import javax.annotation.Nonnull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {

    private static final ObjectMapper mapper = buildObjectMapper();

    @SneakyThrows
    public static <T> T fromJson(@NonNull String json, @Nonnull Class<T> valueType) {
        return mapper.readValue(json, valueType);
    }

    @SneakyThrows
    public static <T> String toJson(@NonNull T value) {
        return mapper.writeValueAsString(value);
    }


    private static ObjectMapper buildObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModules(new Jdk8Module(),new JavaTimeModule());
        return objectMapper;
    }
}
