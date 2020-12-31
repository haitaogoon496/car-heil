package com.mljr.heil.facade.product;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.ProductConstant;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.EntityComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.ProductType;
import com.mljr.heil.form.ProductTypeForm;
import com.mljr.heil.service.product.ProductTypeService;
import com.mljr.heil.vo.product.ProductTypeVo;
import com.mljr.heil.voconvertor.product.ProductTypeVoConvertor;
import com.mljr.util.TimeTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

/**
 * @description: GPS规则 Facade
 * @Date : 上午11:37 2018/2/7
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class ProductTypeFacade implements BaseFacade<ProductTypeVo,ProductType,Integer,ProductTypeForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.PRODUCT_TYPE.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.CAR_PRODUCT_MANAGE;

    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private EntityComponent entityComponent;

    @LogMonitor("产品分类管理")
    @Override
    public Result<PageVO<ProductTypeVo>> loadRecords(PageForm<ProductTypeForm> form) {
        List<ProductTypeVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<ProductType> queryList = this.productTypeService.queryByPage(form);
            count = this.productTypeService.queryCount(form);
            voList = new ProductTypeVoConvertor().convertList(queryList);
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("产品分类管理")
    @Override
    public Result<ProductTypeVo> queryRecord(Integer primaryKey) {
        ProductTypeVo vo = null;
        try {
            ProductType record = this.productTypeService.queryRecord(primaryKey);
            if(null != record ){
                vo = new ProductTypeVoConvertor().convert(record);
            }else{
                return Result.failInEmptyRecord(vo);
            }
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    public Result<String> saveRecord(ProductType record) {
        record.setId(record.getpTypeId());
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,productTypeService,entity -> {
            ProductType productType = (ProductType)entity;
            if(productType.isInsert()){
                entityComponent.setCreateInfo(record);
                record.setCreateUser(record.getUserName());
                record.setCreateTime(TimeTools.createNowTime());
            }
        });
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,productTypeService,(PK) ->
                saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",PK))
        );
    }


    /**
     * 查询分类列表
     * @param form
     * @return
     */
    @LogMonitor("产品分类管理")
    public Result<List<ProductTypeVo>> queryList(ProductTypeForm form){
        List<ProductTypeVo> voList = new ProductTypeVoConvertor().convertList(productTypeService.queryList(form));
        return Result.suc(voList);
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
     * 查询产品分类
     * 业务场景：用于相关模块下拉选择
     * @return Result<ProductTypeVo>
     */
    public Result<List<ProductTypeVo>> querySelectionList() {
        ProductTypeForm  form = new ProductTypeForm();
        List<ProductTypeVo> voList = Collections.emptyList();
        try {
            String status = String.valueOf(ProductConstant.ProductStatusEnum.ENABLE.getIndex());
            form.setStatus(status);
            List<ProductType> queryList = this.productTypeService.queryList(form);
            voList = new ProductTypeVoConvertor().convertList(queryList);
        } catch (Exception e) {
            LOGGER.error("{}queryTypeNameList查询异常,form={}",LOG_TITLE,JSON.toJSON(form),e);
            return Result.failInServer(voList);
        }
        return Result.suc(voList);
    }
}
