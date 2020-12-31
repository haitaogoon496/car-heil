package com.mljr.heil.facade.fund;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.Product;
import com.mljr.heil.entity.fund.Fund;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.form.fund.FundForm;
import com.mljr.heil.mapper.ProductMapper;
import com.mljr.heil.service.fund.FundService;
import com.mljr.heil.vo.product.ProductVo;
import com.mljr.heil.vo.fund.FundVo;
import com.mljr.heil.voconvertor.product.ProductVoConvertor;
import com.mljr.heil.voconvertor.fund.FundVoConvertor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @description: 资金方 facade
 * @Date : 2018/6/12 19:05
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Component
public class FundFacade implements BaseFacade<FundVo,Fund,Integer,FundForm>{
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.FUND.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.FUND;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private UserOperateLogComponent userOperateLogComponent;

    @Autowired
    FundService fundService;
    @Autowired
    private ProductMapper productMapper;

    @LogMonitor("资金方管理")
    @Override
    public Result<PageVO<FundVo>> loadRecords(PageForm<FundForm> form) {
        List<FundVo> fundVos = Collections.emptyList();
        int count = 0;
        try {
               List<Fund> funds = fundService.queryByPage(form);
               fundVos = new FundVoConvertor().convertList(funds);
               count = fundService.queryCount(form);
        }catch (Exception e){
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return  Result.fail(new PageVO<>(form.getDraw(),count,fundVos));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,fundVos));
    }

    @Override
    public Result<FundVo> queryRecord(Integer primaryKey) {
        FundVo vo = null;
        try {
            Fund fund = this.fundService.queryRecord(primaryKey);
            if(null != fund ){
                vo = new FundVoConvertor().convert(fund);
            }else{
                return Result.failInEmptyRecord(vo);
            }
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @LogMonitor("资金方管理")
    @Override
    public Result<String> saveRecord(Fund record) {
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,fundService, baseEntity -> {
            Fund res = (Fund)baseEntity;
            res.setCreateTime(new Date());
        });
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey, LOG_TITLE, fundService, (PK) ->
                saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",PK)));
    }

    /**
     * 查询已经绑定的资金方的产品
     * @param form
     * @return
     */
    public Result<PageVO<ProductVo>> queryBindProducts(PageForm<FundForm> form) {
        LOGGER.info("{} - queryBindProducts 参数:{}", LOG_TITLE, JSON.toJSON(form));
        ValidateUtils.notNull(form.getForm(), HeilCode.E_400, "资金方id不能为空");
        ValidateUtils.notNull(form.getForm().getId(), HeilCode.E_400, "资金方id不能为空");

        List<Product> productList = fundService.selectBindProductByParams(form);
        if (CollectionUtils.isEmpty(productList)) {
            return Result.failInEmptyRecord(null);
        }
        int count = fundService.getBindProductCount(form);
        List<ProductVo> productVoList = new ProductVoConvertor().convertList(productList);
        return Result.suc(new PageVO<>(form.getDraw(), count, productVoList));
    }

    /**
     * 查询没有绑定的资金方的产品
     * @param form
     * @return
     */
    public Result<PageVO<ProductVo>> queryNotBindProducts(PageForm<FundForm> form) {
        LOGGER.info("{} - queryNotBindProducts 参数:{}", LOG_TITLE, JSON.toJSON(form));
        ValidateUtils.notNull(form.getForm(), HeilCode.E_400, "资金方id不能为空");
        ValidateUtils.notNull(form.getForm().getId(), HeilCode.E_400, "资金方id不能为空");

        List<Product> productList = fundService.selectNotBindProductByParams(form);
        if (CollectionUtils.isEmpty(productList)) {
            return Result.failInEmptyRecord(null);
        }
        int count = fundService.getNotBindProductCount(form);
        List<ProductVo> productVoList = new ProductVoConvertor().convertList(productList);
        return Result.suc(new PageVO<>(form.getDraw(), count, productVoList));
    }


    @Transactional
    public Result<Integer> bindProductForFund(ProductForm productForm) {
        LOGGER.info("资金方绑定/解绑产品 - 参数:{}", JSON.toJSON(productForm));
        ValidateUtils.notEmpty(productForm.getProductIdList(), HeilCode.E_400, "请选择要操作的产品");
        ValidateUtils.notNull(productForm.getFundId(), HeilCode.E_400, "fundId错误，不能为空");

        int count = productMapper.bindProductForFund(productForm);
        return Result.suc(count);
    }

    @LogMonitor("资金方管理")
    public Result<String> modifyStatus(Fund record) {
        LOGGER.info("{} - 状态更新 - 参数:{}", LOG_TITLE,JSON.toJSON(record));
        ValidateUtils.notNull(record.getId(), HeilCode.E_400, "参数主键错误");

        int i = this.fundService.updateByPrimaryKeySelective(record);
        ValidateUtils.notEquals(0, i, HeilCode.E_400, "更新状态失败");
        this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("状态更新param={0}",JSON.toJSON(record)));
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
}
