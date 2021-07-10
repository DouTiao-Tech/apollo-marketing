package com.doutiaotech.apollo.external.dy.response;

import lombok.Data;

@Data
public class Response<T> {
    T data;
    int errNo;
    String message;
}
