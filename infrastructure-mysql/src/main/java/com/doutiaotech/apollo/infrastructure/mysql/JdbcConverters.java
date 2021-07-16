package com.doutiaotech.apollo.infrastructure.mysql;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * @see org.springframework.data.convert.Jsr310Converters
 */
public abstract class JdbcConverters {

    @ReadingConverter
    public enum IntegerToBooleanConverter implements Converter<Integer, Boolean> {

        INSTANCE;

        @Override
        public Boolean convert(Integer source) {
            return source == 0 ? Boolean.FALSE : Boolean.TRUE;
        }
    }

}
