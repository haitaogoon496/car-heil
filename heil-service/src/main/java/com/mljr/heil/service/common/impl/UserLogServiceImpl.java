package com.mljr.heil.service.common.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.component.MongodbComponent;
import com.mljr.heil.entity.UserLog;
import com.mljr.heil.form.UserLogForm;
import com.mljr.heil.mapper.UserLogMapper;
import com.mljr.heil.document.UserLogDoc;
import com.mljr.heil.service.common.UserLogService;
import com.mljr.util.TimeTools;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 用户操作日志 Service
 * @Date : 下午6:28 2018/3/2
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Service
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    MongodbComponent mongodbComponent;
    @Autowired
    private UserLogMapper userLogMapper;
    @Override
    public List<UserLog> queryByPage(PageForm<UserLogForm> form) {
        return userLogMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<UserLogForm> form) {
        return userLogMapper.queryCount(form);
    }

    @Override
    public UserLog queryRecord(Integer primaryKey) {
        return userLogMapper.selectByPrimaryKey(Long.valueOf(primaryKey));
    }

    @Override
    public int insertRecord(UserLog record) {
        UserLogDoc userLogDoc = new UserLogDoc();
        BeanUtils.copyProperties(record,userLogDoc);
        mongodbComponent.insert(userLogDoc);
        return userLogMapper.insert(record);
    }
}
