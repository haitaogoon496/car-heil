package com.mljr.heil.component;

import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.transfer.dto.DealerDTO;
import com.lyqc.transfer.enums.TransferRestApiEnum;
import com.lyqc.transfer.re.DealerRe;
import com.mljr.annotation.Action;
import com.mljr.annotation.LogMonitor;
import com.mljr.heil.common.util.RestTemplateUtil;
import com.mljr.util.CollectionsTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 门店
 * @author lingyu.shang
 */
@Component
@Slf4j
public class DealerComponent {

    private static final String LOG_TITLE = "【门店】";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${transfer.url:unknown}")
    private String transferUrl;

    @LogMonitor(value = LOG_TITLE, action = @Action("获取门店列表分页结果"))
    public Result<PageVO<DealerRe>> loadRecords(PageForm<DealerDTO> dto) {
        ResponseEntity<Result<PageVO<DealerRe>>> exchange = restTemplate.exchange(transferUrl + TransferRestApiEnum.DEALER_QUERY_LOADRECORD.getName(), HttpMethod.POST, new HttpEntity<PageForm<DealerDTO>>(dto), new ParameterizedTypeReference<Result<PageVO<DealerRe>>>() {
        });
        RestTemplateUtil.checkResponse(exchange);
        return exchange.getBody();
    }

    @LogMonitor(value = LOG_TITLE, action = @Action("获取门店列表"))
    public List<DealerRe> queryList(PageForm<DealerDTO> dto) {
        Result<PageVO<DealerRe>> pageVOResult = loadRecords(dto);
        if(pageVOResult != null && pageVOResult.isSuccess() && pageVOResult.getData() != null) {
            List<DealerRe> list = pageVOResult.getData().getData();
            return list != null ? list : CollectionsTools.emptyList();
        } else {
            log.warn("{} transfer request={}, result={}", LOG_TITLE, JSONObject.toJSONString(dto), JSONObject.toJSONString(pageVOResult));
        }
        return CollectionsTools.emptyList();
    }

}
