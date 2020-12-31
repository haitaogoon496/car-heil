package com.mljr.heil.facade.calc;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.annotation.LogMonitor;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.common.exception.BizException;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.EntityComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.CalcModelParams;
import com.mljr.heil.form.CalcModelParamsForm;
import com.mljr.heil.service.calc.CalcModelParamsService;
import com.mljr.heil.vo.calc.CalcModelParamsVo;
import com.mljr.heil.voconvertor.calc.CalcModelParamsVoConvertor;
import com.mljr.util.CollectionsTools;
import com.mljr.util.TimeTools;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @description: 产品计算模型参数 Facade
 * @Date : 2018/2/27 下午6:24
 * @Author : liht
 */
@Component
public class CalcModelParamsFacade implements BaseFacade<CalcModelParamsVo,CalcModelParams,Integer,CalcModelParamsForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.CALC_MODEL_PARAMS.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.PD_CALC_MODEL_PARAMS;

    @Autowired
    private CalcModelParamsService calcModelParamsService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private EntityComponent entityComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Value("${spring.profiles.active}")
    private String env;

    @LogMonitor("产品计算模型参数配置")
    @Override
    public Result<PageVO<CalcModelParamsVo>> loadRecords(PageForm<CalcModelParamsForm> form) {
        List<CalcModelParamsVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<CalcModelParams> queryList = this.calcModelParamsService.queryByPage(form);
            if (CollectionUtils.isEmpty(queryList)) {
                return Result.failInEmptyRecord(null);
            }
            count = this.calcModelParamsService.queryCount(form);
            voList = new CalcModelParamsVoConvertor().convertList(queryList);

        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("产品计算模型参数配置")
    @Override
    public Result<CalcModelParamsVo> queryRecord(Integer primaryKey) {
        CalcModelParamsVo vo = null;
        try {
            CalcModelParams record = this.calcModelParamsService.queryRecord(primaryKey);
            if(null == record ){
                return Result.failInEmptyRecord(null);
            }
            vo = new CalcModelParamsVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    @Transactional(rollbackFor = {BizException.class,Exception.class})
    public Result<String> saveRecord(CalcModelParams record) {
        LOGGER.info("{} - saveRecord 参数:{}",LOG_TITLE,JSON.toJSON(record));
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(record);
        if (CollectionsTools.isNotEmpty(violations)) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(),violations.get(0).getMessage());
        }
        Date current = TimeTools.createNowTime();
        Integer id = record.getId();
        record.setUpdateTime(current);
        if(null == id || id.equals(0) ){
            record.setCreateTime(current);
            calcModelParamsService.insertRecord(record);
            this.saveLog(UserOperateLogConstant.AuthTypeEnum.CREATE, MessageFormat.format("新增record={0}",JSON.toJSON(record)));
        }else{
            calcModelParamsService.updateRecord(record);
            this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("修改record={0}",JSON.toJSON(record)));
        }

        return Result.suc();
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,calcModelParamsService,(PK) ->
                saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",PK))
        );
    }

    public Result<String> modifyStatus(CalcModelParams record) {
        LOGGER.info("状态更新 - 参数:{}", JSON.toJSON(record));
        ValidateUtils.notNull(record.getId(),HeilCode.E_400,"参数主键不能为空");
        ValidateUtils.notEquals(record.getId(), 0, HeilCode.E_400, "参数主键错误");

        this.calcModelParamsService.updateRecord(record);
        this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("状态更新param={0}",JSON.toJSON(record)));
        return Result.suc();
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
