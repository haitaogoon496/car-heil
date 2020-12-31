package com.mljr.heil.service.common;

import com.mljr.heil.base.service.BasePagingService;
import com.mljr.heil.entity.UserLog;
import com.mljr.heil.form.UserLogForm;

/**
 * @description: 用户操作日志 Service
 * @Date : 下午6:27 2018/3/2
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface UserLogService extends BasePagingService<UserLog,UserLogForm> {
    /**
     * 新增记录
     * @param record
     * @return
     */
    int insertRecord(UserLog record);
    /**
     * 查询详情
     * @param primaryKey
     * @return
     */
    UserLog queryRecord(Integer primaryKey);
}
