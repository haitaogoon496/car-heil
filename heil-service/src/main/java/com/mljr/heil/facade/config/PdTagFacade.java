package com.mljr.heil.facade.config;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.Action;
import com.mljr.annotation.LogMonitor;
import com.mljr.annotation.OvalValidator;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.form.PdTagForm;
import com.mljr.heil.service.common.PdTagService;
import com.mljr.heil.vo.config.PdTagVo;
import com.mljr.heil.voconvertor.config.PdTagVoConvertor;
import com.mljr.util.CollectionsTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

/**
 * @description 标签 facade
 * @author zhaoxin
 * @date 2018/6/4 上午10:20
 **/
@Component
public class PdTagFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.TAG.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.RULE_TAG;

    @Autowired
    private PdTagService pdTagService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;

    /**
     * 查询分类列表
     *
     * @param form
     * @return
     */
    @OvalValidator("标签设置")
    @LogMonitor("标签设置")
    public Result<PdTagVo> queryList(PdTagForm form) {
        List<PdTagVo> voList = new PdTagVoConvertor().convertList(pdTagService.queryList(form));
        if (CollectionsTools.isEmpty(voList)){
            return Result.suc(new PdTagVo());
        }
        return Result.suc(voList.get(0));
    }

    /**
     * 标签下拉列表
     *
     * @param form
     * @return
     */
    @OvalValidator("标签设置")
    @LogMonitor("标签设置")
    public Result<List<String>> queryAllTag(PdTagForm form) {
        List<String> voList = new PdTagVoConvertor().convertTagList(pdTagService.queryList(form));
        return Result.suc(voList);
    }

    @OvalValidator("标签设置")
    @LogMonitor("标签设置")
    public Result<String> saveRecord(PdTag record) {
        if (record == null) {
            return Result.failInEmptyRecord(null);
        }
        try {
            Integer optType = record.getOptType();
            List<Integer> sourceIds = record.getSourceList();
            List<String> recordList = record.getTagList();
            UserOperateLogConstant.AuthTypeEnum authTypeEnum = UserOperateLogConstant.AuthTypeEnum.getByIndex(optType);
            if (UserOperateLogConstant.AuthTypeEnum.CREATE == authTypeEnum) {
                LOGGER.info("saveRecord 进行标签添加操作");
                //查找新增标签是否存在
                PdTag pdTag = new PdTag();
                for (Integer sourceId:sourceIds){
                    record.setSourceId(sourceId);
                    List<PdTag> voList = pdTagService.queryList(new PdTagVoConvertor().convertForm(record));
                    if (CollectionsTools.isNotEmpty(voList)){
                        StringBuffer tsgStrNew = new StringBuffer();
                        tsgStrNew.append(voList.get(0).getTags());
                        String[] listTag = voList.get(0).getTags().split(",");
                        List<String> existTagList = Arrays.asList(listTag);
                        for (String tag:recordList){
                            if (!existTagList.contains(tag)){
                                tsgStrNew.append(",").append(tag);
                            }
                        }
                        LOGGER.info("修改的标签为:" + existTagList);
                        pdTag.setTags(tsgStrNew.toString());
                        pdTag.setBuzType(voList.get(0).getBuzType());
                        pdTag.setSourceId(voList.get(0).getSourceId());
                        pdTagService.updateRecord(pdTag);
                        this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("更新标签param={0}",JSON.toJSON(pdTag)));
                    }else {
                        String tagStr = StringUtils.collectionToDelimitedString(recordList, ",");
                        pdTag.setBuzType(record.getBuzType());
                        pdTag.setSourceId(sourceId);
                        pdTag.setTags(tagStr);
                        pdTagService.insertRecord(pdTag);
                        this.saveLog(UserOperateLogConstant.AuthTypeEnum.CREATE, MessageFormat.format("添加标签param={0}",JSON.toJSON(pdTag)));
                    }
                }
            } else if (UserOperateLogConstant.AuthTypeEnum.UPDATE == authTypeEnum) {
                if (CollectionsTools.isNotEmpty(sourceIds)) {
                    PdTag pdTag = new PdTag();
                    sourceIds.forEach(sourceId -> {
                        pdTag.setBuzType(record.getBuzType());
                        pdTag.setSourceId(sourceId);
                        String tag = StringUtils.collectionToDelimitedString(recordList, ",");
                        if(StringUtils.isEmpty(tag)){
                            pdTagService.deleteRecord(pdTag);
                            this.saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除标签param={0}",JSON.toJSON(pdTag)));
                        }else {
                            pdTag.setTags(tag);
                            pdTagService.updateRecord(pdTag);
                            this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("更新标签param={0}",JSON.toJSON(pdTag)));
                        }
                    });
                }
            }else {
                return Result.failInEmptyRecord("保存异常");
            }
        } catch (Exception e) {
            LOGGER.error("{}保存异常,record={}", LOG_TITLE, JSON.toJSON(record), e);
            return Result.failInServer("保存异常");
        }
        return Result.suc();
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

    /**
     * 分页加载数据
     * @param form
     * @return
     */
    @LogMonitor(value = "标签管理", action = @Action("分页加载数据"))
    public Result<PageVO<PdTagVo>> loadRecords(PageForm<PdTagForm> form) {
        List<PdTagVo> voList = new PdTagVoConvertor().convertTagVoList(pdTagService.queryByPage(form));
        if (CollectionsTools.isEmpty(voList)) {
            return Result.failInEmptyRecord(null);
        }
        int count = pdTagService.queryCount(form);
        return Result.suc(new PageVO<>(form.getDraw(), count, voList));
    }

    @LogMonitor(value = "标签管理", action = @Action("批量删除标签"))
    public Result<String> batchDelete(List<PdTag> list) {
        if(CollectionsTools.isEmpty(list)) {
            return Result.failWithEmptyParam(null);
        }
        try {
            pdTagService.batchDelete(list);
            saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, JSON.toJSONString(list));
        } catch (Exception e) {
            LOGGER.error("{}batchDelete 批量删除标签异常,form={}", LOG_TITLE, JSON.toJSON(list), e);
            return Result.failInServer("数据保存异常");
        }
        return Result.suc();
    }
}
