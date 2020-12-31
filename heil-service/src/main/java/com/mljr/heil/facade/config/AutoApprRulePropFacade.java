package com.mljr.heil.facade.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.seig.dto.ExpressionCheckDTO;
import com.mljr.annotation.Action;
import com.mljr.annotation.LogMonitor;
import com.mljr.annotation.OvalValidator;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.common.exception.BizException;
import com.mljr.heil.common.util.HttpUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.AutoApprRuleProp;
import com.mljr.heil.form.AutoApprRulePropForm;
import com.mljr.heil.service.config.AutoApprRulePropService;
import com.mljr.heil.vo.config.AutoApprRulePropVo;
import com.mljr.heil.voconvertor.config.AutoApprRulePropVoConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @description: 前置规则 - CA系统自动审批规则属性表
 * @Date
 * @Author : liht
 */
@Component
public class AutoApprRulePropFacade implements BaseFacade<AutoApprRulePropVo,AutoApprRuleProp,Integer,AutoApprRulePropForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.AUTO_APPR_RULE_PROP.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.AUTO_APPR_RULE_PROP;

    @Autowired
    private AutoApprRulePropService autoApprRulePropService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Value("${lyqcSeig}")
    private String lyqcSeigUrl;

    @LogMonitor("前置规则属性配置")
    @Override
    public Result<PageVO<AutoApprRulePropVo>> loadRecords(PageForm<AutoApprRulePropForm> form) {
        List<AutoApprRulePropVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<AutoApprRuleProp> list = autoApprRulePropService.queryByPage(form);
            if (CollectionUtils.isEmpty(list)) {
                return Result.failInEmptyRecord(null);
            }
            voList = new AutoApprRulePropVoConvertor().convertList(list);
            count = autoApprRulePropService.queryCount(form);
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("前置规则属性配置")
    @Override
    public Result<AutoApprRulePropVo> queryRecord(Integer primaryKey) {
        AutoApprRulePropVo vo = null;
        try {
            AutoApprRuleProp record = this.autoApprRulePropService.queryRecord(primaryKey);
            if(null == record ){
                return Result.failInEmptyRecord(null);
            }
            vo = new AutoApprRulePropVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    @Transactional(rollbackFor = {BizException.class,Exception.class})
    public Result<String> saveRecord(AutoApprRuleProp record) {
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,autoApprRulePropService,entity -> {
        });
    }


    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,autoApprRulePropService,(PK) ->
                saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",PK))
        );
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

    @OvalValidator("前置规则-验证公式")
    @LogMonitor("前置规则-验证公式")
    public Result<Boolean> checkExpression(ExpressionCheckDTO dto) {
        String url = lyqcSeigUrl + "api/checkExpression";
        Result<Boolean> result;
        try {
            String body = HttpUtils.post(url, JSON.toJSONString(dto), new HashMap<>());
            result = JSON.parseObject(body,new TypeReference<Result<Boolean>>(){});
        } catch (Exception e) {
            LOGGER.error("前置规则-验证公式异常,dto={}",JSON.toJSON(dto),e);
            return Result.failInServer(false);
        }
        return result;
    }

    @OvalValidator("前置规则-执行公式")
    @LogMonitor("前置规则-执行公式")
    public Result<Object> execExpression(ExpressionCheckDTO dto) {
        String url = lyqcSeigUrl + "api/execExpression";
        Result<Object> result;
        try {
            String body = HttpUtils.post(url, JSON.toJSONString(dto),new HashMap<>());
            result = JSON.parseObject(body,new TypeReference<Result<Object>>(){});
        } catch (Exception e) {
            LOGGER.error("前置规则-执行公式异常,dto={}",JSON.toJSON(dto),e);
            return Result.failInServer("前置规则-执行公式失败");
        }
        return result;
    }

    /**
     * 修改规则属性状态
     * @param record
     * @return
     */
    @LogMonitor(value = "【前置规则属性配置】", action = @Action("【启用/停用】"))
    public Result<String> modifyStatus(AutoApprRuleProp record) {
        Assert.notNull(record, "参数不能为空");
        Assert.notNull(record.getId(), "id不能为空");
        Assert.hasLength(record.getStatus(), "status不能为空");
        try {
            autoApprRulePropService.modifyStatus(record);
            saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("修改规则属性状态param={0}", JSONObject.toJSONString(record)));
        } catch (Exception e) {
            LOGGER.error("{}modifyStatus 修改规则属性状态param,form={}", LOG_TITLE, JSON.toJSON(record), e);
            return Result.failInServer("数据保存异常");
        }
        return Result.suc();
    }
}
