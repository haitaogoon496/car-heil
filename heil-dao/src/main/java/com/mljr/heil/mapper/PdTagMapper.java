package com.mljr.heil.mapper;

import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.form.PdTagForm;

import java.util.List;

public interface PdTagMapper extends BaseMapper<PdTag,Integer,PdTagForm> {

    int updateRecord(PdTag tag);

    int deleteRecord(PdTag tag);
}