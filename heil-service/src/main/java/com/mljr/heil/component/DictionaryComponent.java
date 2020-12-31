package com.mljr.heil.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.DealerTypeEnum;
import com.lyqc.base.enums.DictionaryBizCodeEnum;
import com.lyqc.base.enums.EnumDesc;
import com.lyqc.base.enums.EnumRelevance;
import com.lyqc.base.enums.EnumValue;
import com.lyqc.base.enums.ProductConstant;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.re.SyArgControlRe;
import com.lyqc.product.enums.FundNoEnum;
import com.mljr.heil.common.enums.DictionaryEnum;
import com.mljr.heil.facade.common.ConfigParamsFacade;
import com.mljr.heil.vo.common.SyArgControlVo;
import com.mljr.util.PropertiesReader;
import com.mljr.util.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:数据字典组件
 * @Date : 下午6:27 2018/3/3
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class DictionaryComponent{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ConfigParamsFacade configParamsFacade;
    /**
     * 是否直营
     */
    private final boolean isZy = "100000".equals(PropertiesReader.getAppointPropertiesAttribute("config","system_code",String.class));

    /**
     * 基于枚举的接口
     * @param code 数据字典code
     * @return Result<List<SyArgControlVo>>
     */
    public Result<List<SyArgControlVo>> enums(String code){
        logger.info("enums方法 - code:{}", code);
        List<SyArgControlVo> list = new ArrayList<>();
        EnumValue[] enums = DictionaryEnum.getEnumsByCode(code);
        if(null != enums){
            for(EnumValue value : enums){
                if(filter(value)){
                    SyArgControlVo argVo = new SyArgControlVo(value.getIndex(),value.getName(),String.valueOf(value.getIndex()),String.valueOf(value.getName()));
                    specialReset(value,argVo);
                    list.add(argVo);
                }
            }
        }
        return Result.suc(list);
    }
    /**
     * 批量查询基于静态枚举的接口
     * @param enumList
     * @return Result<Map<String,List<SyArgControlVo>>>
     */
    public Result<Map<String,List<SyArgControlVo>>> enumsList(@RequestBody List<String> enumList){
        Map<String,List<SyArgControlVo>> resultMap = new HashMap<>();
        for(DictionaryEnum dictionaryEnum : DictionaryEnum.values()){
            String enumCode = dictionaryEnum.getCode();
            if(enumList.contains(dictionaryEnum.getCode())){
                resultMap.put(enumCode,enums(enumCode).getData());
            }
        }
        return Result.suc(resultMap);
    }

    /**
     * 特殊重置
     * @param value
     * @param argVo
     * @return
     */
    protected void specialReset(EnumValue value,SyArgControlVo argVo){
        if(value instanceof EnumDesc){
            EnumDesc enumDesc = (EnumDesc)value;
            argVo.setValue(enumDesc.getDesc());
            if(value instanceof FundNoEnum){
                if(StringTools.isEmpty(enumDesc.getDesc())){
                    return;
                }
            }
        }
        if(value instanceof EnumRelevance) {
            EnumRelevance enumRelevance = (EnumRelevance) value;
            EnumValue relevance = enumRelevance.getRelevance();
            if(relevance == null) {
                return;
            }
            SyArgControlVo vo = new SyArgControlVo(relevance.getIndex(),relevance.getName(),String.valueOf(relevance.getIndex()),String.valueOf(relevance.getName()));
            specialReset(relevance, vo);
            argVo.setRelevance(vo);
        }
    }

    /**
     * 针对枚举的业务场景的特殊过滤处理
     * @param value
     * @return
     */
    protected boolean filter(EnumValue value){
        boolean filter = true;
        // 门店类型，对于直营 需要过滤掉
        if(value instanceof DealerTypeEnum && isZy){
            filter = false;
            DealerTypeEnum[] enums = DealerTypeEnum.valuesBySystem(isZy);
            List<Integer> list = new ArrayList<>(enums.length);
            for(DealerTypeEnum each : enums){
                list.add(each.getIndex());
            }
            if(list.contains(value.getIndex())){
                filter = true;
            }
        }
        //  用户操作日志 操作类型 枚举
        if(value instanceof UserOperateLogConstant.AuthModelEnumForProduct){
            filter = !value.getName().startsWith("微信");
        }
        if (value instanceof ProductConstant.ProductStatusEnum) {
            filter = value.getName().contains("启用")||value.getName().contains("暂停");
        }
        if(value instanceof EnumDesc){
            EnumDesc enumDesc = (EnumDesc)value;
            if(value instanceof FundNoEnum){
                if(StringTools.isEmpty(enumDesc.getDesc())){
                    filter = false;
                }
            }
        }
        return filter;
    }

    /**
     * 基于查询库的数据字典
     * @param code 数据字典code
     * @return Result<List<SyArgControlVo>>
     */
    public Result<List<SyArgControlRe>> dicts(DictionaryBizCodeEnum code){
        List<SyArgControlRe> list = new ArrayList<>();
        switch (code){
            case flowSeq:
                String configValue = configParamsFacade.getValueByParamKey("appFlowJSON",String.class);
                if(StringTools.isNotEmpty(configValue)){
                    list = JSON.parseArray(configValue,SyArgControlRe.class);
                }
                break;
        }
        return Result.suc(list);
    }

    /**
     * 基于全局配置参数的接口
     * @param keyList
     * @return
     */
    public Result<Map<String,Object>> params(List<String> keyList) {
        Map<String,Object> map = new HashMap<>();
        try {
            if(!CollectionUtils.isEmpty(keyList)) {
                keyList.forEach(key ->{
                    String result = configParamsFacade.getValueByParamKey(key, String.class);
                    if("PricingLabel".equals(key)) {
                        List<String> list = null;
                        if(!StringUtils.isEmpty(result)){
                             list = JSONObject.parseObject(result, new TypeReference<List<String>>() {});
                        }
                        map.put(key, list);
                    }else {
                        map.put(key, result);
                    }
                });
            }
        }catch (Exception e){
            return Result.failInServer(map);
        }
        return Result.suc(map);
    }

}
