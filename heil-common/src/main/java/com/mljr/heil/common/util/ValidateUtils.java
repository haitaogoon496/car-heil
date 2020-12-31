package com.mljr.heil.common.util;

import java.util.Collection;

import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.exception.AlertException;
import com.mljr.heil.common.exception.ParamException;

public class ValidateUtils {

    public static void isTrue(Boolean value, int code, String msg) {
        if (!Boolean.TRUE.equals(value)) {
            throw new AlertException(code, msg);
        }
    }

    public static void isFalse(Boolean value, int code, String msg) {
        if (!Boolean.FALSE.equals(value)) {
            throw new AlertException(code, msg);
        }
    }

    public static void notEmpty(String value, int code, String msg) {
        if (value == null || value.isEmpty()) {
            throw new AlertException(code, msg);
        }
    }

    public static void notTrimEmpty(String value, int code, String msg) {
        if (value == null || value.trim().isEmpty()) {
            throw new AlertException(code, msg);
        }
    }

    public static void notTrimEmptyParam(String value, String paramName) {
        if (value == null || value.trim().isEmpty()) {
            throw new ParamException(paramName, "参数错误", "不能为空");
        }
    }

    public static void notNull(Object value, int code, String msg) {
        if (value == null ) {
            throw new AlertException(code, msg);
        }
    }

    public static void notNullParam(Object value, String paramName) {
        if (value == null ) {
            throw new AlertException(HeilCode.E_400, "参数错误", paramName);
        }
    }

    public static void maxLength(String value, int maxLength, int code, String msg) {
        if (value != null && value.length() > maxLength) {
            throw new AlertException(code, msg);
        }
    }

    public static void isNull(Object value, int code, String msg) {
        if (value != null ) {
            throw new AlertException(code, msg);
        }
    }

    public static void notEmpty(Collection value, int code, String msg) {
        if (value == null || value.isEmpty()) {
            throw new AlertException(code, msg);
        }
    }

    public static void notEmptyParam(Collection value, String paramName) {
        if (value == null || value.isEmpty()) {
            throw new AlertException(HeilCode.E_400, "参数错误", paramName);
        }
    }

    public static void isEmpty(Collection value, int code, String msg) {
        if (value != null && !value.isEmpty()) {
            throw new AlertException(code, msg);
        }
    }

    public static <T> boolean isEquals(T expected, T actual) {
        return expected == null? actual == null : expected.equals(actual);
    }

    public static <T> void isEquals(T expected, T actual, int code, String msg) {
        if (!isEquals(expected, actual)) {
            throw new AlertException(code, msg);
        }
    }

    public static <T> void isEquals(T expected, T actual, int code, String msg, CompareCallback errorCallback) {
        if (!isEquals(expected, actual)) {
            if (errorCallback != null) {
                errorCallback.beforeThrow();
            }
            throw new AlertException(code, msg);
        }
    }

    public static <T> void notEquals(T expected, T actual, int code, String msg) {
        if (isEquals(expected, actual)) {
            throw new AlertException(code, msg);
        }
    }

    public static interface CompareCallback {
        void beforeThrow();
    }

}
