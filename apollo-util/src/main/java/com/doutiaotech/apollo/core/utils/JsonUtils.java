package com.doutiaotech.apollo.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;

public class JsonUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public static <T> T fromJson(@NonNull String json, @Nonnull Class<T> valueType) {
        return mapper.readValue(json, valueType);
    }

    @SneakyThrows
    public static <T> String toJson(@NonNull T value) {
        return mapper.writeValueAsString(value);
    }
}
