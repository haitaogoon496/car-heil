package com.mljr.heil.facade.rule;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.PdFeeDealer;
import com.mljr.heil.form.PdFeeDealerForm;
import com.mljr.heil.service.rule.PdFeeDealerService;
import com.mljr.heil.vo.common.DealerVo;
import com.mljr.heil.voconvertor.rule.PdFeeDealerVoConvertor;
import com.mljr.util.CollectionsTools;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

/**
 * TODO 只有对应 Controller 调用，Controller 删除后删除
 * @description: GPS门店配置门面
 * @Date : 上午11:51 2018/2/7
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
//@Component
public class PdFeeDealerFacade implements BaseFacade<DealerVo,PdFeeDealer,Integer,PdFeeDealerForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.LIFE_INSURANCE.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.LIFE_INSURANSE;
    @Autowired
    private PdFeeDealerService pdFeeDealerService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;

    @LogMonitor("费用规则配置门店")
    @Override
    public Result<PageVO<DealerVo>> loadRecords(PageForm<PdFeeDealerForm> form) {
        List<DealerVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<PdFeeDealer> queryList = this.pdFeeDealerService.queryByPage(form);
            count = this.pdFeeDealerService.queryCount(form);
            voList = new PdFeeDealerVoConvertor().convertList(queryList);
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("费用规则配置门店")
    @Override
    public Result<DealerVo> queryRecord(Integer primaryKey) {
        DealerVo vo = null;
        try {
            PdFeeDealer record = this.pdFeeDealerService.queryRecord(primaryKey);
            if(null != record ){
                vo = new PdFeeDealerVoConvertor().convert(record);
            }else{
                return Result.failInEmptyRecord(vo);
            }
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    public Result<String> saveRecord(PdFeeDealer record) {
        LOGGER.info("{} - saveRecord 参数:{}",LOG_TITLE,JSON.toJSON(record));
        try {
            Validator validator = new Validator();
            List<ConstraintViolation> violations = validator.validate(record);
            if (CollectionsTools.isNotEmpty(violations)) {
                return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(),violations.get(0).getMessage());
            }
            PdFeeDealer queryRecord = this.pdFeeDealerService.queryRecord(record.getId());
            if(null == queryRecord){
                pdFeeDealerService.insertRecord(record);
                this.saveLog(UserOperateLogConstant.AuthTypeEnum.CREATE, MessageFormat.format("新增管理门店record={0}",JSON.toJSON(record)));
            }else{
                pdFeeDealerService.updateRecord(record);
                this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("修改管理门店record={0}",JSON.toJSON(record)));
            }
        } catch (Exception e) {
            LOGGER.error("{}保存异常,record={}",LOG_TITLE,JSON.toJSON(record),e);
            return Result.failInServer("保存异常");
        }
        return Result.suc();
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,pdFeeDealerService,(PK) ->
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
}
