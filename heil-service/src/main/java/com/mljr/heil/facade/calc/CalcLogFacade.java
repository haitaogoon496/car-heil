package com.mljr.heil.facade.calc;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.LogMonitor;
import com.mljr.annotation.OvalValidator;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.common.exception.BizException;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.CalcLog;
import com.mljr.heil.form.CalcLogForm;
import com.mljr.heil.service.calc.CalcLogService;
import com.mljr.heil.vo.calc.CalcLogVo;
import com.mljr.heil.voconvertor.calc.CalcLogVoConvertor;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description: 产品公式计算日志 Facade
 * @Date : 2018/2/27 下午6:24
 * @Author : liht
 */
@Component
public class CalcLogFacade implements BaseFacade<CalcLogVo,CalcLog,Integer,CalcLogForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.CALC_LOG.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.PD_CALC_LOG;
    private static final String synLogTitle = "calc数据同步到bi_log";

    @Autowired
    private CalcLogService calcLogService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;

    @Override
    public Result<PageVO<CalcLogVo>> loadRecords(PageForm<CalcLogForm> form) {
        List<CalcLogVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<CalcLog> queryList = this.calcLogService.queryByPage(form);
            if (CollectionUtils.isEmpty(queryList)) {
                return Result.failInEmptyRecord(null);
            }
            count = this.calcLogService.queryCount(form);
            voList = new CalcLogVoConvertor().convertList(queryList);

        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @Override
    public Result<CalcLogVo> queryRecord(Integer primaryKey) {
        CalcLogVo vo = null;
        try {
            CalcLog record = this.calcLogService.queryRecord(primaryKey);
            if(null == record ){
                return Result.failInEmptyRecord(null);
            }
            vo = new CalcLogVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    @LogMonitor("链路分析日志")
    @OvalValidator("链路分析日志")
    @Transactional(rollbackFor = {BizException.class,Exception.class})
    public Result<String> saveRecord(CalcLog record) {
        Date current = TimeTools.createNowTime();
        Integer id = record.getId();
        if(null == id || id.equals(0) ){
            record.setCreateTime(current);
            calcLogService.insertRecord(record);
            this.saveLog(UserOperateLogConstant.AuthTypeEnum.CREATE, MessageFormat.format("新增record={0}",JSON.toJSON(record)));
        }else{
            calcLogService.updateRecord(record);
            this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("修改record={0}",JSON.toJSON(record)));
        }
        return Result.suc();
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,calcLogService,(PK) ->
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


    public static void main(String[] args) {
        String[] array = Optional.ofNullable(args).orElse(new String[]{});
        System.out.println(array);
    }
}
