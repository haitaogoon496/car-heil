package com.mljr.heil.facade.rule;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.LogMonitor;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.service.rule.AccountDealerService;
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
 * @description: 账户管理费门店配置
 * @Date : 2018/2/8 15:49
 * @Author : lihaitao
 */
//@Component
public class AccountDealerFacade implements BaseFacade<DealerVo,BaseDealerRes,BaseDealerRes,DealerQueryForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = "账号管理费规则门店管理";
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.ACCOUNT_RULE;
    @Autowired
    private AccountDealerService accountDealerService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;

    @Override
    @LogMonitor("账户管理费规则配置")
    public Result<PageVO<DealerVo>> loadRecords(PageForm<DealerQueryForm> form) {
        List<BaseDealerRes> list = Collections.emptyList();
        List<DealerVo> voList = Collections.emptyList();
        int count = 0;
        try {
            list = this.accountDealerService.queryByPage(form);
            count = this.accountDealerService.queryCount(form);
            voList = new DealerVoConvertor().convertList(list);
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("账户管理费规则配置")
    @Override
    public Result<DealerVo> queryRecord(BaseDealerRes primaryKey) {
        DealerVo vo = new DealerVo();
        BaseDealerRes record = null;
        try {
            record = this.accountDealerService.queryRecord(primaryKey);
            if(record == null){
                return Result.failInEmptyRecord(vo);
            }
            vo = new DealerVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    public Result<String> saveRecord(BaseDealerRes record) {
        boolean isNotExists = !Optional.ofNullable(accountDealerService.queryRecord(record)).isPresent();
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,accountDealerService,entity -> {
            BaseDealerRes res = (BaseDealerRes)entity;
            res.setUpdateTime(TimeTools.createNowTime());
        },x -> isNotExists);
    }

    @Override
    public Result<String> deleteRecord(BaseDealerRes primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,accountDealerService,(PK) ->
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
