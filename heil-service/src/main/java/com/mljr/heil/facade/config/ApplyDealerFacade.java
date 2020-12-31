package com.mljr.heil.facade.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.transfer.dto.DealerDTO;
import com.lyqc.transfer.re.DealerRe;
import com.mljr.annotation.LogMonitor;
import com.mljr.annotation.OvalValidator;
import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.form.DealerRefQueryForm;
import com.mljr.heil.common.enums.ApplyDealerTableEnum;
import com.mljr.heil.common.exception.AlertException;
import com.mljr.heil.component.ApplyDealerComponent;
import com.mljr.heil.component.DealerComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.PdFeeDealer;
import com.mljr.heil.form.PdFeeDealerForm;
import com.mljr.heil.service.common.SyDealerService;
import com.mljr.heil.service.product.ProductDealerService;
import com.mljr.heil.service.rule.AccountDealerService;
import com.mljr.heil.service.rule.GpsDealerService;
import com.mljr.heil.service.rule.PdFeeDealerService;
import com.mljr.heil.service.rule.RateDealerService;
import com.mljr.heil.service.rule.SerFinDealerService;
import com.mljr.heil.service.rule.YanbaoDealerService;
import com.mljr.heil.vo.common.DealerVo;
import com.mljr.heil.vo.config.ApplyDealerVo;
import com.mljr.heil.voconvertor.common.DealerRefConvertor;
import com.mljr.util.CollectionsTools;
import com.mljr.util.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description: 管理所属门店
 * @Date : 2018/5/29 下午8:00
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Component
public class ApplyDealerFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = "管理所属门店";
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.APPLY_DEALER;
    @Autowired
    private ApplyDealerComponent applyDealerComponent;
    @Autowired
    private RateDealerService rateDealerService;
    @Autowired
    private GpsDealerService gpsDealerService;
    @Autowired
    private SerFinDealerService serFinDealerService;
    @Autowired
    private AccountDealerService accountDealerService;
    @Autowired
    private YanbaoDealerService yanbaoDealerService;
    @Autowired
    private ProductDealerService productDealerService;
    @Autowired
    private PdFeeDealerService pdFeeDealerService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;

    @Autowired
    private DealerComponent dealerComponent;
    @Autowired
    private SyDealerService syDealerService;

    @LogMonitor("费用规则管理所属门店")
    public Result<PageVO<DealerVo>> loadRecords(PageForm<DealerQueryForm> form) {
        List<DealerVo> voList = CollectionsTools.emptyList();
        int count = 0;
        form.setPaging(Boolean.FALSE);
        ApplyDealerTableEnum tableClassify = ApplyDealerTableEnum.getByIndex(form.getForm().getClassify());
        if (tableClassify == null) {
            return Result.fail(RemoteEnum.ERROR_IN_SERVER.getIndex(),"classify错误,没有对应操作表");
        }

        // 配合前端入参
        String jsonStr = JSONObject.toJSONString(form);
        PageForm<DealerDTO> dto = JSONObject.parseObject(jsonStr, new TypeReference<PageForm<DealerDTO>>() {
        });
        if(form != null && form.getForm() != null && dto != null && dto.getForm() != null) {
            dto.getForm().setDealerCode(StringTools.isNotEmpty(form.getForm().getCode()) ? Integer.valueOf(form.getForm().getCode()) : null);
            dto.getForm().setDealerName(form.getForm().getName());
        }

        // 查询门店列表，捕获异常为配合前端出参
        List<DealerRe> dealerReList = null;
        try {
            dealerReList = dealerComponent.queryList(dto);
        } catch (AlertException e) {
            if(!Objects.equals(e.getCode(), RemoteEnum.WARN_EMPTY_RECORD.getIndex())) {
                throw e;
            }
            dealerReList = CollectionsTools.emptyList();
        }
        if(CollectionsTools.isNotEmpty(dealerReList)) {
            List<Integer> dealerCodeList = syDealerService.queryDealerCodeListByParam(DealerRefQueryForm.builder().tableName(tableClassify.getName()).queryName(tableClassify.getDealerName()).refIdName(tableClassify.getRefIdName()).refIdValue(form.getForm().getRuleId()).build());
            List<Integer> queryDealerCode = dealerReList.stream().map(DealerRe::getDealerCode).collect(Collectors.toList());
            List<Integer> bingDealerList = dealerCodeList.stream().filter(d -> queryDealerCode.contains(d)).collect(Collectors.toList());
            // 配合前端出参
            count = dealerReList.size();
            List<DealerVo> dealerVos = new DealerRefConvertor().convertList(dealerReList);
            dealerVos.forEach(dealerVo -> {
                dealerVo.setRuleSeq(form.getForm().getRuleId().toString());
                if(CollectionsTools.isNotEmpty(bingDealerList) && bingDealerList.contains(Integer.valueOf(dealerVo.getDealerCode()))) {
                    dealerVo.setLinked("1");
                } else {
                    dealerVo.setLinked("0");
                }
            });
            voList = dealerVos.stream().sorted(Comparator.comparing(DealerVo::getLinked).reversed()).collect(Collectors.toList());
            voList.get(0).setMapperDealerCount(CollectionsTools.isNotEmpty(bingDealerList) ? bingDealerList.size() : 0);
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    /**
     * 保存门店
     * 批量处理 新增  和 删除
     * @param record
     * @return
     */
    @OvalValidator("管理所属门店-保存门店")
    public Result<String> saveRecord(ApplyDealerVo record) {
        LOGGER.info("保存门店操作 record参数:{}", JSON.toJSON(record));
        if(null != record){
            Integer ruleId = record.getRuleSeq();
            Integer classify = record.getClassify();
            ApplyDealerTableEnum tableEnum = ApplyDealerTableEnum.getByIndex(classify);
            if (tableEnum == null) {
                return Result.fail(RemoteEnum.ERROR_IN_SERVER.getIndex(),"classify错误,没有对应操作表");
            }
            List<Integer> insertList = record.getInsertList();
            List<Integer> deleteList = record.getDeleteList();
            if(CollectionsTools.isNotEmpty(deleteList) && null != tableEnum){
                DealerQueryForm deleteForm = new DealerQueryForm();
                deleteForm.setDealerScopes(deleteList);
                deleteForm.setRuleId(record.getRuleSeq());
                deleteForm.setTableName(tableEnum.getName());
                // 如果人身险
                if (tableEnum == ApplyDealerTableEnum.PD_FEE_RULE) {
                    PdFeeDealerForm pdFeeDealerForm = new PdFeeDealerForm();
                    pdFeeDealerForm.setRuleId(record.getRuleSeq());
                    pdFeeDealerForm.setDealerScopes(deleteList);
                    pdFeeDealerService.batchDelete(pdFeeDealerForm);
                }else{
                    applyDealerComponent.batchDelete(deleteForm);
                }
            }
            if(CollectionsTools.isNotEmpty(insertList)){
                List<BaseDealerRes> batchList = new ArrayList<>(insertList.size());
                insertList.forEach(dealerCode -> {
                    BaseDealerRes dealerRes  = new BaseDealerRes();
                    dealerRes.setRuleSeq(ruleId);
                    dealerRes.setDealerCode(dealerCode);
                    batchList.add(dealerRes);
                });
                switch (tableEnum){
                    case RATE_RULE:
                        rateDealerService.batchInsert(batchList);
                        break;
                    case GPS_RULE:
                        gpsDealerService.batchInsert(batchList);
                        break;
                    case SER_FIN_RULE:
                        serFinDealerService.batchInsert(batchList);
                        break;
                    case ACCOUNT_RULE:
                        accountDealerService.batchInsert(batchList);
                        break;
                    case SECOND_INSURANCE_RULE:
                        yanbaoDealerService.batchInsert(batchList);
                        break;
                    case THIRD_INSURANCE_RULE:
                        yanbaoDealerService.batchInsert(batchList);
                        break;
                    case PRODUCT_RULE:
                        productDealerService.batchInsert(batchList);
                        break;
                    case PD_FEE_RULE:
                        List<PdFeeDealer> pdFeeInsertList = new ArrayList<>(insertList.size());
                        insertList.forEach(dealerCode -> {
                            PdFeeDealer pdFeeDealer  = new PdFeeDealer();
                            pdFeeDealer.setResId(ruleId);
                            pdFeeDealer.setDealerCode(dealerCode);
                            pdFeeInsertList.add(pdFeeDealer);
                        });
                        pdFeeDealerService.batchInsert(pdFeeInsertList);
                        break;
                }
            }
            JSONObject authDetailJSON = new JSONObject(3,true){{
               put("module",tableEnum.getDesc());
               put("insertList",insertList.toString());
               put("deleteList",deleteList.toString());
            }};
            saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE,authDetailJSON.toJSONString());
        }
        return Result.suc();
    }

    /**
     * 保存操作日志
     * @param authTypeEnum
     * @param authDetail
     */
    public void saveLog(UserOperateLogConstant.AuthTypeEnum authTypeEnum, String authDetail){
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authDetail);
            log.setAuthModel(authModelEnum);
            log.setAuthType(authTypeEnum);
        });
    }

}
