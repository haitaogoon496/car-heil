package com.mljr.heil.facade.common;

import com.lyqc.base.common.Result;
import com.lyqc.base.re.SyArgControlRe;
import com.lyqc.transfer.enums.TransferRestApiEnum;
import com.mljr.annotation.Action;
import com.mljr.annotation.LogMonitor;
import com.mljr.heil.common.util.RestTemplateUtil;
import com.mljr.util.CollectionsTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @description: 数据字典查询
 * @Date : 2019/3/26 0026 13:21
 * @Author : 尚凌宇
 */
@Component
public class ArgControlFacade {

    private static final String LOG_TITLE = "【数据字典】";

    @Value("${transfer.url:unknown}")
    private String transferUrl;

    @Autowired
    private RestTemplate restTemplate;

    @LogMonitor(value = LOG_TITLE, action = @Action("根据 type 查询数据字典"))
    public List<SyArgControlRe> queryOne(Integer argType) {
        ResponseEntity<Result<List<SyArgControlRe>>> exchange = restTemplate.exchange(MessageFormat.format(transferUrl + TransferRestApiEnum.METADATA_QUERY_ONE.getName(), argType), HttpMethod.GET, new HttpEntity(argType), new ParameterizedTypeReference<Result<List<SyArgControlRe>>>() {
        });
        RestTemplateUtil.checkResponse(exchange);
        List<SyArgControlRe> data = exchange.getBody().getData();
        if(CollectionsTools.isEmpty(data)) {
            return CollectionsTools.emptyList();
        }
        return data;
    }

    @LogMonitor(value = LOG_TITLE, action = @Action("根据 typeList 查询数据字典"))
    public Map<String, List<SyArgControlRe>> queryList(List<Integer> argTypes) {
        ResponseEntity<Result<Map<String, List<SyArgControlRe>>>> exchange = restTemplate.exchange(transferUrl + TransferRestApiEnum.METADATA_QUERY_LIST.getName(), HttpMethod.POST, new HttpEntity<List<Integer>>(argTypes), new ParameterizedTypeReference<Result<Map<String, List<SyArgControlRe>>>>() {
        });
        RestTemplateUtil.checkResponse(exchange);
        Map<String, List<SyArgControlRe>> data = exchange.getBody().getData();
        if(CollectionsTools.isEmpty(data)) {
            return CollectionsTools.emptyMap();
        }
        return data;
    }

}
