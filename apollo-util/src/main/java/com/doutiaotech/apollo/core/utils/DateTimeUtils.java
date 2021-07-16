package com.doutiaotech.apollo.core.utils;

import lombok.experimental.UtilityClass;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtils {

    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final ZoneOffset ZONE_OFFSET = OffsetTime.now(ZoneId.of("Asia/Shanghai")).getOffset();

    public static long toTimestamp(@Nonnull LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZONE_OFFSET).getEpochSecond();
    }

    public static LocalDateTime longToDateTime(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 0, ZONE_OFFSET);
    }

}
