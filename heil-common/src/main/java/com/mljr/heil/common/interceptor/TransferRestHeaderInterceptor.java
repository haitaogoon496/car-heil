package com.mljr.heil.common.interceptor;

import interception.MljrClientHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * RestTemplate Transfer 请求头设置
 * @author lingyu.shang
 */
@Component
public class TransferRestHeaderInterceptor implements MljrClientHttpRequestInterceptor {

    @Value("${transfer.header:unknown}")
    private String systemSource;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.add("systemSource", systemSource);
        return execution.execute(request, body);
    }

}
