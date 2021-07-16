package com.doutiaotech.apollo.core.utils;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;

public class JsonUtilsTest {

    @Test
    public void test_toJson() {
        String json = JsonUtils.toJson(new SimpleObject("value"));
        Assert.assertEquals("{\"key\":\"value\"}", json);
        json = JsonUtils.toJson(new SnakeObject("value"));
        Assert.assertEquals("{\"snake_key\":\"value\"}", json);
        json = JsonUtils.toJson(new CamelObject("value"));
        Assert.assertEquals("{\"camelKey\":\"value\"}", json);
        Assert.assertThrows(NullPointerException.class, () -> JsonUtils.toJson(null));
    }

    @Test
    public void test_fromJson() {
        SimpleObject simpleObject = JsonUtils.fromJson("{\"key\":\"value\"}", SimpleObject.class);
        Assert.assertEquals("value", simpleObject.key);
        SnakeObject snake_object = JsonUtils.fromJson("{\"snake_key\":\"value\"}", SnakeObject.class);
        Assert.assertEquals("value", snake_object.snake_key);
        CamelObject camelObject = JsonUtils.fromJson("{\"camelKey\":\"value\"}", CamelObject.class);
        Assert.assertEquals("value", camelObject.camelKey);
        Assert.assertThrows(NullPointerException.class, () -> JsonUtils.fromJson(null, SimpleObject.class));
        Assert.assertThrows(MismatchedInputException.class, () -> JsonUtils.fromJson("", SimpleObject.class));
        Assert.assertThrows(IllegalArgumentException.class, () -> JsonUtils.fromJson("{\"key\":\"value\"}", null));
        Assert.assertThrows(UnrecognizedPropertyException.class, () -> JsonUtils.fromJson("{\"key\":\"value\"}", SnakeObject.class));
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
