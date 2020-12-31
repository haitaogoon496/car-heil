package com.mljr.heil.voconvertor;

import com.alibaba.fastjson.JSONObject;
import com.mljr.heil.common.util.StringUtils;
import com.mljr.heil.entity.PdHealthReport;
import com.mljr.heil.vo.healthCheck.PdHealthReportVo;
import com.mljr.util.TimeTools;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @Date : 2018/9/19$ 16:27$
 * @Author : liht
 */
@Slf4j
public class PdHealthReportVoConvertor extends VoConvertor<PdHealthReportVo,PdHealthReport> {
    @Override
    public PdHealthReportVo convert(PdHealthReport bo) {
        PdHealthReportVo vo = new PdHealthReportVo();
        vo.setId(StringUtils.killNull(bo.getId()));
        if (!StringUtils.isEmpty(bo.getReportDetail())) {
            try {
                vo.setReportDetail(JSONObject.parseObject(bo.getReportDetail()));
            } catch (Exception e) {
                log.info("当前数据不符合json格式,主键id:{} - 数据:{}", bo.getId(), bo.getReportDetail());
            }
        }
        vo.setReportTime(TimeTools.format4YYYYMMDDHHMISS(bo.getReportTime()));
        return vo;
    }
}
