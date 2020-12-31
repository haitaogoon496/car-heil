package com.mljr.heil.facade.fund;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.EntityComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.fund.PdFundRuleCensus;
import com.mljr.heil.entity.fund.PdFundRuleProp;
import com.mljr.heil.form.fund.PdFundRuleCensusForm;
import com.mljr.heil.form.fund.PdFundRulePropForm;
import com.mljr.heil.mapper.fund.PdFundRuleCensusMapper;
import com.mljr.heil.param.CityParam;
import com.mljr.heil.param.PdFundRulePropParam;
import com.mljr.heil.param.ProvinceParam;
import com.mljr.heil.service.fund.PdFundRulePropService;
import com.mljr.heil.service.fund.ProvinceCityService;
import com.mljr.heil.vo.fund.PdFundRulePropVo;
import com.mljr.util.CollectionsTools;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * @description:资金方规则属性处理facade
 * @Date : 2018/6/13 11:16
 * @Author : lihaitao
 */
@Component
public class PdFundRulePropFacade implements BaseFacade<PdFundRulePropVo,PdFundRuleProp, Integer, PdFundRulePropForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.PD_FUND_RULE_PROP.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.PD_FUND_RULE_PROP;
    @Autowired
    private PdFundRulePropService pdFundRulePropService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private EntityComponent entityComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private PdFundRuleCensusMapper pdFundRuleCensusMapper;
    @Autowired
    private ProvinceCityService provinceCityService;

    @LogMonitor("资金方准入规则属性")
    @Override
    public Result<PageVO<PdFundRulePropVo>> loadRecords(PageForm<PdFundRulePropForm> form) {
        List<PdFundRulePropVo> voList = new ArrayList<>();
        List<PdFundRuleProp> list = Collections.emptyList();
        form.setPaging(false);
        int count = 0;
        try {
            list = this.pdFundRulePropService.queryByPage(form);
            if (CollectionUtils.isEmpty(list)) {
                return Result.failInEmptyRecord(null);
            }
            Map<Integer, List<PdFundRuleProp>> propsMap = list.stream().collect(Collectors.groupingBy(PdFundRuleProp::getFundRuleId));
            LOGGER.info("{} - 查询结果集为:{}", LOG_TITLE, JSON.toJSON(propsMap));
            propsMap.entrySet().iterator().forEachRemaining(listEntry->{
                JSONObject jsonObject = new JSONObject();
                listEntry.getValue().stream().forEach(entity -> jsonObject.put(entity.getPropName(), entity.getPropValue()));
                PdFundRulePropVo pdFundRulePropVo = JSONObject.parseObject(jsonObject.toJSONString(), PdFundRulePropVo.class);
                pdFundRulePropVo.setFundRuleId(listEntry.getKey().toString());
                voList.add(pdFundRulePropVo);
            });
            count = propsMap.size();

        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("资金方准入规则属性")
    @Override
    public Result<PdFundRulePropVo> queryRecord(Integer fundRuleId) {
        if (fundRuleId == null) {
            return Result.fail(HeilCode.E_500, "fundRuleId不能为空");
        }
        List<PdFundRuleProp> recordList;
        PdFundRulePropVo vo = new PdFundRulePropVo();

        try {
            PdFundRulePropForm pdFundRulePropForm = new PdFundRulePropForm();
            pdFundRulePropForm.setFundRuleId(fundRuleId);
            recordList = this.pdFundRulePropService.queryByPage(new PageForm<>(false, pdFundRulePropForm));
            if (CollectionUtils.isEmpty(recordList)) {
                return Result.failInEmptyRecord(null);
            };
            JSONObject jsonObject = new JSONObject();
            recordList.stream().forEach(entity->{
                jsonObject.put(entity.getPropName(), entity.getPropValue());
            });
            vo = JSONObject.parseObject(jsonObject.toJSONString(), PdFundRulePropVo.class);
            vo.setFundRuleId(recordList.get(0).getFundRuleId().toString());

            if (!StringUtils.isEmpty(vo.getFundRuleId())) {
                PdFundRuleCensusForm pdFundRuleCensusForm = new PdFundRuleCensusForm();
                pdFundRuleCensusForm.setFundRuleId(Integer.parseInt(vo.getFundRuleId()));
                List<PdFundRuleCensus> pdFundRuleCensuses = pdFundRuleCensusMapper.pageQuery(new PageForm<>(false, pdFundRuleCensusForm));
                if (!CollectionUtils.isEmpty(pdFundRuleCensuses)) {
                    Map<String, List<PdFundRuleCensus>> pdFundRuleCensusMap = pdFundRuleCensuses.stream().collect(
                            Collectors.groupingBy(x->x.getProvinceCode()));
                    Map<String, List<String>> resuleMap = new HashMap<>();
                    pdFundRuleCensusMap.forEach((k,v)->{
                        List<String> list = new ArrayList<String>();
                        v.stream().forEach(item -> list.add(item.getCityCode()));
                        resuleMap.put(k, list);
                    });
                    LOGGER.info("{} - 资金方规则对应户籍数据->:{}",LOG_TITLE,JSON.toJSON(resuleMap));
                    vo.setPdFundRuleCensusMap(resuleMap);
                }
            }
            vo.setJsonObject(jsonObject);

        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,fundRuleId,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }
    @Deprecated
    @Override
    public Result<String> saveRecord(PdFundRuleProp record) {
        final boolean isInsert = record.isInsert();
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,pdFundRulePropService,entity -> {
            PdFundRuleProp rule = (PdFundRuleProp) entity;
            rule.setId(rule.getId());
            entityComponent.setCreateInfo(rule);
        },entity->{
            // 更新
            if (!isInsert) {
                if (record.getFundRuleId() != null && record.getFundRuleId() != 0) {
                    PdFundRuleCensusForm fundRuleCensusForm = new PdFundRuleCensusForm();
                    fundRuleCensusForm.setFundRuleId(record.getFundRuleId());
                    pdFundRuleCensusMapper.deleteByForm(fundRuleCensusForm);
                }
            }
            record.getPdFundRuleCensusList().forEach(item -> item.setFundRuleId(record.getFundRuleId()));
            pdFundRuleCensusMapper.batchInsert(record.getPdFundRuleCensusList());
        });
    }

    /**
     * 更新资金方规则属性表
     * @param pdFundRulePropParam
     * @return
     */
    @Transactional
    public Result<String> saveRecord(PdFundRulePropParam pdFundRulePropParam) {
        LOGGER.info("资金方规则属性saveRecord --- 参数:{}", JSON.toJSON(pdFundRulePropParam));
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(pdFundRulePropParam);
        if (CollectionsTools.isNotEmpty(violations)) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), violations.get(0).getMessage());
        }
        int count = pdFundRulePropService.deleteByFundRuleId(pdFundRulePropParam.getFundRuleId());
        LOGGER.info("资金方规则属性saveRecord -> 执行deleteByFundRuleId方法,影响行数:{}", count);
        pdFundRulePropParam.getPdFundRuleProps().forEach(entity->{
            if (entity.getFundRuleId() == null || entity.getFundRuleId() == 0) {
                entity.setFundRuleId(pdFundRulePropParam.getFundRuleId());
            }
        });
        pdFundRulePropService.batchInsert(pdFundRulePropParam.getPdFundRuleProps());

        // 更新规则属性对应的户籍信息
        PdFundRuleCensusForm fundRuleCensusForm = new PdFundRuleCensusForm();
        fundRuleCensusForm.setFundRuleId(pdFundRulePropParam.getFundRuleId());
        pdFundRuleCensusMapper.deleteByForm(fundRuleCensusForm);
        // 执行插入操作
        if (!CollectionUtils.isEmpty(pdFundRulePropParam.getPdFundRuleCensusList())) {
            pdFundRulePropParam.getPdFundRuleCensusList().forEach(item -> item.setFundRuleId(pdFundRulePropParam.getFundRuleId()));
            pdFundRuleCensusMapper.batchInsert(pdFundRulePropParam.getPdFundRuleCensusList());
        }
        return Result.suc();
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {

        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,pdFundRulePropService,(PK) -> {
            saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}", PK));
        });
    }

    /**
     * 获取省份
     * @return
     */
    public Result<List<ProvinceParam>> getProvinces() {
        LOGGER.info("{} - 获取省份城市",LOG_TITLE);
        List<ProvinceParam> provinceParams = provinceCityService.getProvinces();
        return Result.suc(provinceParams);
    }

    /**
     * 获取省份对应的城市信息
     *
     * @return
     */
    public Result<List<CityParam>> getCities(String provinceCode) {
        LOGGER.info("{} - 获取城市,code:{}", LOG_TITLE, provinceCode);
        List<CityParam> cities = provinceCityService.getCities(provinceCode);
        return Result.suc(cities);
    }

    /**
     * 保存操作日志
     * @param authTypeEnum
     * @param authDetail
     */
    public void saveLog(UserOperateLogConstant.AuthTypeEnum authTypeEnum,String authDetail){
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authDetail);
            log.setAuthModel(authModelEnum);
            log.setAuthType(authTypeEnum);
        });
    }
}
