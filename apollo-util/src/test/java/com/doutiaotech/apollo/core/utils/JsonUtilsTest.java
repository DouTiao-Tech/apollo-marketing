package com.doutiaotech.apollo.core.utils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JsonUtilsTest {

    @Test
    public void test_toJson() {
        String json = JsonUtils.toJson(new SimpleObject("value"));
        assertThat(json, equalTo("{\"key\":\"value\"}"));
        json = JsonUtils.toJson(new SnakeObject("value"));
        assertThat(json, equalTo("{\"snake_key\":\"value\"}"));
        json = JsonUtils.toJson(new CamelObject("value"));
        assertThat(json, equalTo("{\"camelKey\":\"value\"}"));
        assertThrows(NullPointerException.class, () -> JsonUtils.toJson(null));
    }

    @Test
    public void test_fromJson() {
        SimpleObject simpleObject = JsonUtils.fromJson("{\"key\":\"value\"}", SimpleObject.class);
        assertEquals("value", simpleObject.key);
        SnakeObject snake_object = JsonUtils.fromJson("{\"snake_key\":\"value\"}", SnakeObject.class);
        assertEquals("value", snake_object.snake_key);
        CamelObject camelObject = JsonUtils.fromJson("{\"camelKey\":\"value\"}", CamelObject.class);
        assertEquals("value", camelObject.camelKey);
        assertThrows(NullPointerException.class, () -> JsonUtils.fromJson(null, SimpleObject.class));
        assertThrows(MismatchedInputException.class, () -> JsonUtils.fromJson("", SimpleObject.class));
        assertThrows(IllegalArgumentException.class, () -> JsonUtils.fromJson("{\"key\":\"value\"}", null));
        assertThrows(UnrecognizedPropertyException.class,
                () -> JsonUtils.fromJson("{\"key\":\"value\"}", SnakeObject.class));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class SimpleObject {
        String key;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class SnakeObject {
        String snake_key;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class CamelObject {
        String camelKey;
    }

}
