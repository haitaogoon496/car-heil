package com.mljr.heil.core.bindlink;

import com.alibaba.fastjson.JSONObject;
import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.component.DealerComponent;
import com.mljr.heil.param.config.BatchLinkParam;
import com.mljr.heil.vo.config.DealerFeeRuleVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description: 数据关联处理上下文对象
 * @Date : 2018/10/17 下午6:46
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class LinkProcessContext implements Serializable{
    private static final long serialVersionUID = -2256983963793509832L;
    /**
     * 封装请求参数
     */
    protected BatchLinkParam batchLinkParam;
    /**
     * 批量门店集合
     */
    protected List<Integer> dealerScope;
    /**
     * 批量产品集合
     */
    protected List<Integer> productScope;
    /**
     * 业务处理类型
     */
    protected LinkProcessBuzType buzType;
    /**
     * 回显前端页面的VO对象
     */
    protected DealerFeeRuleVo feeRuleVo;
    /**
     * 依赖service map容器
     */
    protected Map<RuleServiceEnum,BaseService> serviceMap;
    /**
     * 操作日志
     */
    protected JSONObject operateLog;
    /**
     * 门店 Facade
     */
    protected DealerComponent dealerComponent;

}
