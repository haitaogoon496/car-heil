package com.mljr.heil.voconvertor.config;

import com.lyqc.heil.enums.TagConstant;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.form.PdTagForm;
import com.mljr.heil.vo.config.PdTagVo;
import com.mljr.heil.voconvertor.VoConvertor;
import com.mljr.util.CollectionsTools;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description VO转换
 * @author zhaoxin
 * @date 2018/6/4 下午2:39
 **/
public class PdTagVoConvertor extends VoConvertor<PdTagVo,PdTag> {

    @Override
    public PdTagVo convert(PdTag bo) {
        PdTagVo vo = new PdTagVo();
        if (!StringUtils.isEmpty(bo.getTags())){
            vo.setTagList(Arrays.asList(bo.getTags().split(",")));
        }
        return vo;
    }

    public List<String> convertTagList(List<PdTag> bo) {
        if (CollectionsTools.isEmpty(bo)){
            return CollectionsTools.emptyList();
        }
        List<String> idList;
        List<String> tagAllList = new ArrayList<>();
        for (PdTag pdTag:bo){
            if (pdTag != null){
                idList = Arrays.asList(pdTag.getTags().split(","));//根据逗号分隔转化为list;
                tagAllList.addAll(idList);
            }
        }
        Set<String> resultList = new HashSet(tagAllList);
        return new ArrayList<>(resultList);
    }


    public List<PdTagVo> convertTagVoList(List<PdTag> list) {
        if (CollectionsTools.isEmpty(list)){
            return CollectionsTools.emptyList();
        }
        List<PdTagVo> voList = new ArrayList<>(list.size());
        list.forEach(tag -> {
            PdTagVo vo = new PdTagVo();
            BeanUtils.copyProperties(tag, vo);
            vo.setTagList(Arrays.asList(tag.getTags().split(",")));
            vo.setBuzTypeName(TagConstant.BuzTypeEnum.getNameByIndex(vo.getBuzType()));
            voList.add(vo);
        });
        return voList;
    }

    public PdTagForm convertForm(PdTag bo) {
        PdTagForm vo = new PdTagForm();
        vo.setId(bo.getId());
        vo.setBuzType(bo.getBuzType());
        vo.setSourceId(bo.getSourceId());
        vo.setTags(bo.getTags());
        return vo;
    }

}
