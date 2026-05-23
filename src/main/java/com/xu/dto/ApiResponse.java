package com.xu.dto;

/**
 * 统一接口返回对象
 */
public record ApiResponse<T>(
        int code,
        String msg,
        T data
) {}
