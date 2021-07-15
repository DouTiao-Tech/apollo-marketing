package com.doutiaotech.apollo.core.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class DateTimeUtilsTests {

    @Test
    public void test_toTimestamp() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-04-02 17:23:33", DateTimeUtils.DATE_TIME);
        long timestamp = DateTimeUtils.toTimestamp(dateTime);
        Assert.assertEquals(1617355413, timestamp);
    }

    @Test
    public void test_longToDateTime() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-04-02 17:23:33", DateTimeUtils.DATE_TIME);
        LocalDateTime localDateTime = DateTimeUtils.longToDateTime(1617355413);
        Assert.assertEquals(dateTime, localDateTime);
    }

}
