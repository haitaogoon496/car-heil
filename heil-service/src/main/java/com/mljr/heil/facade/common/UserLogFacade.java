package com.mljr.heil.facade.common;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.entity.UserLog;
import com.mljr.heil.form.UserLogForm;
import com.mljr.heil.service.common.UserLogService;
import com.mljr.heil.vo.common.UserLogVo;
import com.mljr.heil.voconvertor.common.UserOperateLogVoConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @description: 用户操作日志
 * @Date : 2018/6/10 下午8:59
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Component
public class UserLogFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.ACCOUNT_RULE.getName();
    @Autowired
    private UserLogService userOperateLogService;

    public Result<PageVO<UserLogVo>> loadRecords(PageForm<UserLogForm> form) {
        List<UserLogVo> voList = Collections.emptyList();
        List<UserLog> list;
        int count = 0;
        try {
            list = this.userOperateLogService.queryByPage(form);
            if (CollectionUtils.isEmpty(list)) {
                return Result.failInEmptyRecord(null);
            }
            count = this.userOperateLogService.queryCount(form);
            voList = new UserOperateLogVoConvertor().convertList(list);
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(PageVO.newInstance(form.getDraw(),count,voList));
    }

    public Result<UserLogVo> queryRecord(Integer primaryKey) {
        UserLog record;
        UserLogVo vo = null;
        try {
            record = this.userOperateLogService.queryRecord(primaryKey);
            if (record == null) {
                return Result.failInEmptyRecord(null);
            }
            vo = new UserOperateLogVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }
}
