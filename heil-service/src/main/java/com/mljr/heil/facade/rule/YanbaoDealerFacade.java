package com.mljr.heil.facade.rule;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.service.rule.YanbaoDealerService;
import com.mljr.heil.vo.common.DealerVo;
import com.mljr.heil.voconvertor.common.DealerVoConvertor;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * TODO 只有对应 Controller 调用，Controller 删除后删除
 * @description: 第二/三年保险费规则 门店管理 门面
 * @Date : 上午11:51 2018/2/7
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
//@Component
public class YanbaoDealerFacade implements BaseFacade<DealerVo,BaseDealerRes,BaseDealerRes,DealerQueryForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = "第二/三年保险费规则门店管理";
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.YAN_BAO_RULE;
    @Autowired
    private YanbaoDealerService yanbaoDealerService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;

    @Override
    public Result<PageVO<DealerVo>> loadRecords(PageForm<DealerQueryForm> form) {
        LOGGER.info("{} - loadRecords 参数:{}",LOG_TITLE,JSON.toJSON(form));
        List<DealerVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<BaseDealerRes> queryList = this.yanbaoDealerService.queryByPage(form);
            count = this.yanbaoDealerService.queryCount(form);
            voList = new DealerVoConvertor().convertList(queryList);
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        LOGGER.info("{} - loadRecords 结果result:{}",LOG_TITLE,JSON.toJSON(voList));
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @Override
    public Result<DealerVo> queryRecord(BaseDealerRes primaryKey) {
        LOGGER.info("{} - queryRecord 参数:{}",LOG_TITLE,JSON.toJSON(primaryKey));
        DealerVo vo = null;
        try {
            BaseDealerRes record = this.yanbaoDealerService.queryRecord(primaryKey);
            if (record == null) {
                return Result.failInEmptyRecord(null);
            }
            vo = new DealerVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        LOGGER.info("{} - queryRecord 结果result:{}",LOG_TITLE,JSON.toJSON(vo));
        return Result.suc(vo);
    }

    @Override
    public Result<String> saveRecord(BaseDealerRes record) {
        boolean isNotExists = !Optional.ofNullable(yanbaoDealerService.queryRecord(record)).isPresent();
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,yanbaoDealerService,entity -> {
            BaseDealerRes res = (BaseDealerRes)entity;
            res.setUpdateTime(TimeTools.createNowTime());
        },x -> isNotExists);
    }

    @Override
    public Result<String> deleteRecord(BaseDealerRes primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,yanbaoDealerService,(PK) ->
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
