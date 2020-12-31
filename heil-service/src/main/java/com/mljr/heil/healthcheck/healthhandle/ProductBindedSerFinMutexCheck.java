package com.mljr.heil.healthcheck.healthhandle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mljr.common.TwoTuple;
import com.mljr.heil.common.enums.HealthCheckIndexEnum;
import com.mljr.heil.common.enums.ProductMutexSerFinRuleEnum;
import com.mljr.heil.entity.Product;
import com.mljr.heil.entity.ProductProps;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.form.ProductForm;
import com.mljr.heil.form.ProductPropsForm;
import com.mljr.heil.service.product.ProductPropsService;
import com.mljr.heil.service.product.ProductService;
import com.mljr.heil.service.rule.SerFinRuleService;
import com.mljr.heil.vo.healthCheck.HealthCheckMainPartVo;
import com.mljr.heil.vo.product.ProductPropVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * @description: 产品绑定平台费规则互斥检查，（产品和平台费各自有一套规则）
 * @Date : 2018/7/25 20:09
 * @Author : 樊康康-(kangkang.fan@mljr.com)
 */
@Service
public class ProductBindedSerFinMutexCheck extends AbstractHealthCheck {

    private  static  final Logger logger = LoggerFactory.getLogger(ProductBindedSerFinMutexCheck.class);

    @Autowired
    SerFinRuleService serFinRuleService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductPropsService productPropsService;

    @Override
    public void doCheck() {
        List<HealthCheckMainPartVo> healthCheckMainPartVoList = new ArrayList<>();
        ProductForm productForm = new ProductForm();
        // 启用
        productForm.setStatus(1);
        List<Product> products = productService.queryList(productForm);
        products.forEach(product ->{
            //获取产品的规则内容
            ProductPropsForm productPropsForm = new ProductPropsForm();
            productPropsForm.setProductId(product.getpId());
            List<ProductProps> productProps = productPropsService.queryList(productPropsForm);
            if(productProps.size() > 0) {
                Map<String,String> unMatchContent = new HashMap<>();
                //获取产品绑定的平台费规则
                List<SerFinRule> serFinRules = serFinRuleService.queryRulesByProductId(product.getpId());
                serFinRules.forEach(serFinRule -> {
                    //校验某个产品和某个平台费的规则是否有冲突，冲突则返回法false
                    TwoTuple<Boolean,ProductMutexSerFinRuleEnum> twoTuple = checkProductAndSerfin(productProps, serFinRule);
                    if(! Boolean.TRUE.equals(twoTuple.getA())){
                        //如果这个产品没有未匹配的平台费规则，则map中新加一个，否则将结果append到后面
                        if(unMatchContent.get(twoTuple.getB().getName()) == null){
                            unMatchContent.put(twoTuple.getB().getName(),serFinRule.getRuleSeq().toString());
                        }else {
                           String matchIds = unMatchContent.get(twoTuple.getB().getName());
                            unMatchContent.put(twoTuple.getB().getName(),matchIds+","+serFinRule.getRuleSeq().toString());
                        }
                    }
                });

                //如果规则校验结果不是空，则加入集合
                if(unMatchContent.size() > 0){
                    HealthCheckMainPartVo healthCheckMainPartVo = new HealthCheckMainPartVo<List<TwoTuple<String,String>>>();
                    healthCheckMainPartVo.setEvent(product.getpId());
                    healthCheckMainPartVo.setEventName(product.getpName());
                    healthCheckMainPartVo.setEventDetail(unMatchContent);
                    healthCheckMainPartVoList.add(healthCheckMainPartVo);
                }
            }else {
                HealthCheckMainPartVo healthCheckMainPartVo = new HealthCheckMainPartVo();
                healthCheckMainPartVo.setEvent(product.getpId());
                healthCheckMainPartVo.setEventName(product.getpName());
                healthCheckMainPartVo.setEventDetail("产品丢失规则");
                healthCheckMainPartVoList.add(healthCheckMainPartVo);
            }
        });
        healthCheckMap.put(HealthCheckIndexEnum.PRODUCT_SERFIN_MUTEX,healthCheckMainPartVoList);
    }

    /**
     * 校验某个产品和某个平台费的规则是否有冲突，冲突则返回法false
     * @param productProps
     * @param serFinRule
     * @return
     */
    private TwoTuple<Boolean,ProductMutexSerFinRuleEnum> checkProductAndSerfin(List<ProductProps> productProps, SerFinRule serFinRule){
        Integer serRuleSeq = serFinRule.getRuleSeq();
        //产品的相关信息
        Integer pId = productProps.get(0).getpId();

        JSONObject jsonObject = new JSONObject();
        productProps.forEach(prop -> jsonObject.put(prop.getPropName(), prop.getPropValue()));
        ProductPropVo productPropVo = JSONObject.parseObject(jsonObject.toJSONString(), ProductPropVo.class);

        try {
            //车辆贷款金额比较
            if(Double.valueOf(productPropVo.getCarloanAmountMax()).doubleValue() < serFinRule.getLoanAmountMiin().doubleValue()
                    || Double.valueOf(productPropVo.getCarloanAmountMin()).doubleValue() > serFinRule.getLoanAmountMax().doubleValue()){
                logger.info("pId:{},serFinRuleSeq:{},产品与平台费贷款金额区间冲突",pId,serRuleSeq);
                return new TwoTuple(false, ProductMutexSerFinRuleEnum.PRODUCT_MUTEX_LOANAMOUNT);
            }

            //首付比比较
            if(Double.valueOf(productPropVo.getInitScaleMax()).doubleValue() < serFinRule.getInitScaleMin().doubleValue()
                    || Double.valueOf(productPropVo.getInitScaleMin()).doubleValue() > serFinRule.getInitScaleMax().doubleValue()){
                logger.info("pId:{},serFinRuleSeq:{},产品与平台费首付比区间冲突",pId,serRuleSeq);
                return new TwoTuple(false,ProductMutexSerFinRuleEnum.PRODUCT_MUTEX_INITSCALE);
            }
        }catch (Exception e){
            logger.error("[{}]产品或平台费区间规则字段为空，productProps:{},serFinRule:{}",pId,JSON.toJSONString(productPropVo),JSON.toJSONString(serFinRule));
            return new TwoTuple(false,ProductMutexSerFinRuleEnum.PRODUCT_OR_RULE_RANGE_FILE_NULL);
        }


        //是否二手车比较
        if(productPropVo.getIsOld() !=null && serFinRule.getIsOld() != null) {
            if(! StringsContailsCheck(productPropVo.getIsOld(),serFinRule.getIsOld()) ){
                logger.info("pId:{},serFinRuleSeq:{},产品与平台费是否二手车无相同",pId,serRuleSeq);
                return new TwoTuple(false,ProductMutexSerFinRuleEnum.PRODUCT_MUTEX_IS_OLD);
            }
        }

        //车类型比较
        if(productPropVo.getIsLcv() != null && serFinRule.getIsLcv() != null){
            if(! StringsContailsCheck(productPropVo.getIsLcv(),serFinRule.getIsLcv()) ){
                logger.info("pId:{},serFinRuleSeq:{},产品与平台费车类型冲突",pId,serRuleSeq);
                return new TwoTuple(false,ProductMutexSerFinRuleEnum.PRODUCT_MUTEX_IS_LCV);
            }
        }

        //贷款期数
        if(productPropVo.getLoanPeriods() != null && serFinRule.getrLoanPeriods() != null){
            if(! StringsContailsCheck(productPropVo.getLoanPeriods(),serFinRule.getrLoanPeriods()) ){
                logger.info("pId:{},serFinRuleSeq:{},产品与平台费贷款期数冲突",pId,serRuleSeq);
                return new TwoTuple(false,ProductMutexSerFinRuleEnum.PRODUCT_MUTEX_LOANPERIODS);
            }
        }

        //是否提供房产
        if(productPropVo.getIsHouse() != null && serFinRule.getRule1() != null){
            if(! StringsContailsCheck(productPropVo.getIsHouse(),serFinRule.getRule1()) ){
                logger.info("pId:{},serFinRuleSeq:{},产品与平台费是否提供房产冲突",pId,serRuleSeq);
                return new TwoTuple(false,ProductMutexSerFinRuleEnum.PRODUCT_MUTEX_IS_HOUSE);
            }
        }

        return  new TwoTuple(true,ProductMutexSerFinRuleEnum.NO_MUTEX);
    }

    /**
     * 检查两个逗号内容字符串是否有重复元素  如果没有重复则返回false
     * "1,2,3"   "3,4,5"
     * @param target1
     * @param target2
     * @return
     */
    private boolean StringsContailsCheck(String target1,String target2){
        List<String> list1 = new ArrayList<>(Arrays.asList(target1.split(",")));
        List<String> list2 = new ArrayList<>(Arrays.asList(target2.split(",")));
        list1.retainAll(list2);
        if(list1 == null || list1.size() == 0){
            return false;
        }
        return true;
    }
}
