package com.mljr.heil.service.common.impl;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.form.PdTagForm;
import com.mljr.heil.mapper.PdTagMapper;
import com.mljr.heil.service.common.PdTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description 标签 Service
 * @author zhaoxin
 * @date 2018/6/4 下午2:56
 **/
@Service
public class PdTagServiceImpl implements PdTagService{

    @Autowired
    private PdTagMapper pdTagMapper;

    @Override
    public List<PdTag> queryList(PdTagForm form) {
        return pdTagMapper.queryList(form);
    }

    @Override
    public int updateRecord(PdTag record) {
        return pdTagMapper.updateRecord(record);
    }

    @Override
    public int insertRecord(PdTag record) {
        return pdTagMapper.insert(record);
    }

    @Override
    public int deleteRecord(PdTag record) {
         return pdTagMapper.deleteRecord(record);
    }

    @Override
    public List<PdTag> queryByPage(PageForm<PdTagForm> form) {
        return pdTagMapper.pageQuery(form);
    }

    @Override
    public int queryCount(PageForm<PdTagForm> form) {
        return pdTagMapper.queryCount(form);
    }

    @Override
    public int batchDelete(List<PdTag> list) {
        return pdTagMapper.batchDelete(list);
    }

}
