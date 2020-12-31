package com.mljr.heil.common.util;

import com.lyqc.base.common.Result;
import com.mljr.heil.common.exception.AlertException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * @description: RestTemplate 工具类
 * @Date : 2019/3/28 0028 15:08
 * @Author : 尚凌宇
 */
public class RestTemplateUtil {

    public static <T> void checkResponse(ResponseEntity<Result<T>> responseEntity) {
        if(!Objects.equals(responseEntity.getStatusCode(), HttpStatus.OK)) {
            throw new AlertException(responseEntity.getStatusCodeValue(), MessageFormat.format("调用 Transfer 服务失败，失败状态：{0}, 失败原因：{0}", responseEntity.getStatusCodeValue(), responseEntity.getStatusCode().getReasonPhrase()));
        }
        if(responseEntity.getBody() == null || !responseEntity.getBody().isSuccess()) {
            throw new AlertException(responseEntity.getBody() == null ? responseEntity.getStatusCodeValue() : responseEntity.getBody().getCode(), MessageFormat.format("调用 Transfer 服务返回异常，异常原因：{0}", responseEntity.getBody() == null ? "返回结果为null" : responseEntity.getBody().getMsg()));
        }
    }

}
