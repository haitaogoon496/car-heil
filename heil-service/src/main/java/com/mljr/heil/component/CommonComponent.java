package com.mljr.heil.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant.AuthModelEnumForProduct;
import com.lyqc.base.enums.UserOperateLogConstant.AuthTypeEnum;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.re.SyArgControlRe;
import com.mljr.common.TwoTuple;
import com.mljr.heil.base.entity.BaseEntity;
import com.mljr.heil.base.entity.BaseRule;
import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.enums.DictionaryConstant.RuleClassNameEnum;
import com.mljr.heil.common.exception.AlertException;
import com.mljr.heil.common.util.ArithUtil;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.entity.CalcModelParams;
import com.mljr.heil.entity.PdTag;
import com.mljr.heil.entity.ProductProps;
import com.mljr.heil.facade.common.ArgControlFacade;
import com.mljr.heil.form.CalcModelParamsForm;
import com.mljr.heil.form.PdTagForm;
import com.mljr.heil.form.ProductPropsForm;
import com.mljr.heil.param.RuleReqPam;
import com.mljr.heil.service.calc.CalcModelParamsService;
import com.mljr.heil.service.common.PdTagService;
import com.mljr.heil.service.product.ProductPropsService;
import com.mljr.heil.vo.product.ProductPropVo;
import com.mljr.heil.vo.product.ProductVo;
import com.mljr.util.CollectionsTools;
import com.mljr.util.StringTools;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @description: 共同处理facade
 * @Date : 2018/3/9$ 11:17$
 * @Author : liht
 */
@Component
public class CommonComponent {

    private static Logger logger = LoggerFactory.getLogger(CommonComponent.class);
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CalcModelParamsService calcModelParamsService;
    @Autowired
    private ProductPropsService productPropsService;
    @Autowired
    private PdTagService pdTagService;

    @Autowired
    private ArgControlFacade argControlFacade;

    /**
     * 通用保存操作
     * @param record 实体对象
     * @param AuthModelEnumForProduct
     * @param logTitle 日志标题
     * @param service service
     * @param c1 回调函数，对于record相应特殊处理
     * @Date : 上午8:15 2018/4/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     * @return
     */
    public Result<String> saveRecord(BaseEntity record, AuthModelEnumForProduct AuthModelEnumForProduct, String logTitle, BaseService service, Consumer<BaseEntity> c1){
        return saveRecord(record,AuthModelEnumForProduct,logTitle,service,c1,c -> {},p -> record.isInsert());
    }

    /**
     * 通用保存操作
     * @param record 实体对象
     * @param AuthModelEnumForProduct
     * @param logTitle 日志标题
     * @param service service
     * @param c1 回调函数，对于record相应特殊处理
     * @param predicate 鉴定回调，判断当前是否新增操作
     * @Date : 上午8:15 2018/4/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     * @return
     */
    public Result<String> saveRecord(BaseEntity record, AuthModelEnumForProduct AuthModelEnumForProduct, String logTitle, BaseService service,
                                     Consumer<BaseEntity> c1, Predicate<BaseEntity> predicate){
        return saveRecord(record,AuthModelEnumForProduct,logTitle,service,c1,c -> {},predicate);
    }

    /**
     * 通用保存操作
     * @param record 实体对象
     * @param AuthModelEnumForProduct
     * @param logTitle 日志标题
     * @param service service
     * @param c1 回调函数，对于record相应特殊处理
     * @Date : 上午8:15 2018/4/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     * @return
     */
    public Result<String> saveRecord(BaseEntity record, AuthModelEnumForProduct AuthModelEnumForProduct, String logTitle, BaseService service,
                                     Consumer<BaseEntity> c1,Consumer<BaseEntity> c2){
        return saveRecord(record,AuthModelEnumForProduct,logTitle,service,c1,c2,x -> record.isInsert());
    }

    /**
     * 通用保存操作
     * @param record 实体对象
     * @param AuthModelEnumForProduct
     * @param logTitle 日志标题
     * @param service service
     * @param c1 回调函数，对于record相应特殊处理
     * @param c2 回调函数，后置处理
     * @param predicate 鉴定回调，判断当前是否新增操作
     * @Date : 上午8:15 2018/4/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     * @return
     */
    public Result<String> saveRecord(BaseEntity record, AuthModelEnumForProduct AuthModelEnumForProduct, String logTitle, BaseService service,
                                     Consumer<BaseEntity> c1, Consumer<BaseEntity> c2, Predicate<BaseEntity> predicate){
        logger.info("{} - saveRecord 参数record:{}",logTitle, JSON.toJSON(record));
        try {
            Validator validator = new Validator();
            List<ConstraintViolation> violations = validator.validate(record);
            if (CollectionsTools.isNotEmpty(violations)) {
                return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), violations.get(0).getMessage());
            }
            c1.accept(record);
            if (predicate.test(record)) {
                service.insertRecord(record);
                this.saveLog(AuthTypeEnum.CREATE, AuthModelEnumForProduct,JSON.toJSONString(record));
            } else {
                if(record instanceof BaseRule) {
                    ((BaseRule) record).setIsOld(Optional.ofNullable(((BaseRule) record).getIsOld()).orElse(""));
                }
                service.updateRecord(record);
                this.saveLog(AuthTypeEnum.UPDATE, AuthModelEnumForProduct, JSON.toJSONString(record));
            }
            c2.accept(record);
        } catch (DuplicateKeyException e) {
            throw e;
        } catch (Exception e) {
            logger.error("{}保存异常,record={}", logTitle, JSON.toJSON(record), e);
            return Result.failInServer("保存异常");
        }
        return Result.suc();
    }

    /**
     * 保存操作日志
     * @param authTypeEnum
     * @param authDetail
     * @Date : 上午8:15 2018/4/5
     * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
     */
    public void saveLog(AuthTypeEnum authTypeEnum,AuthModelEnumForProduct AuthModelEnumForProduct,String authDetail){
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authDetail);
            log.setAuthModel(AuthModelEnumForProduct);
            log.setAuthType(authTypeEnum);
        });
    }
    /**
     * 通用删除操作
     * @param primaryKey 主键ID
     * @param logTitle 日志标题
     * @param service service
     * @param consumer
     * @param <PK> 主键
     * @return
     */
    public <PK> Result<String> deleteRecord(PK primaryKey,String logTitle, BaseService service, Consumer consumer){
        logger.info("{} - deleteRecord 参数:{}",logTitle,primaryKey);
        try {
            int row = service.deleteRecord(primaryKey);
            consumer.accept(primaryKey);
            if(row == 0){
                return Result.failInEmptyRecord("没找到相关记录");
            }
        } catch (Exception e) {
            logger.error("{}删除异常,id={}",logTitle,primaryKey,e);
            return Result.failInServer("删除异常");
        }
        return Result.suc();
    }

    /**
     * 对相关查询的规则VO绑定标签
     * 适用业务场景：平台费规则、利率规则、GPS规则 配置标签，查询列表返回相应标签
     * @param tuple 数据传输通道容器
     * @param function 回调函数，返回集合元素对象，让调用方返回对应的主键id
     * @param consumer 接口，返回获取的tags
     */
    public <T,R> void bindTags(TwoTuple<Integer,List<T>> tuple, Function<T,R> function, BiConsumer<T,List<String>> consumer){
        try {
            PdTagForm form = new PdTagForm();
            form.setBuzType(tuple.getA());
            List<PdTag> tagList = this.pdTagService.queryList(form);
            if(CollectionsTools.isNotEmpty(tagList)){
                Map<Integer,String> tagMap = tagList.stream().collect(Collectors.toMap(PdTag::getSourceId,PdTag::getTags));
                tuple.getB().forEach(rule -> {
                    String tags = tagMap.get(function.apply(rule));
                    if(StringTools.isNotEmpty(tags)){
                        consumer.accept(rule,Arrays.asList(tags.split(",")));
                    }
                });
            }
        } catch (Exception e) {
            logger.error("{}设置标签异常,tuple={}",JSON.toJSON(tuple),e);
        }
    }

    /**
     * 对车贷产品VO绑定属性VO
     * @param voList
     */
    public void bindProductProps(List<ProductVo> voList){
        try {
            if (CollectionsTools.isEmpty(voList)) {
                return;
            }
            List<Integer> pIdList = voList.stream().map(ProductVo::getPId).collect(Collectors.toList());
            ProductPropsForm propsForm = new ProductPropsForm();
            propsForm.setProductIdList(pIdList);
            List<ProductProps> props = productPropsService.queryList(propsForm);
            Map<Integer, ProductPropVo> productIdMap = new HashMap<>();
            if (CollectionsTools.isNotEmpty(props)) {
                Map<Integer, List<ProductProps>> propsMap = props.stream().collect(Collectors.groupingBy(ProductProps::getpId));
                propsMap.entrySet().forEach(entry -> {
                    JSONObject jsonObject = new JSONObject();
                    entry.getValue().forEach(prop -> jsonObject.put(prop.getPropName(), prop.getPropValue()));
                    ProductPropVo productPropVo = JSONObject.parseObject(jsonObject.toJSONString(), ProductPropVo.class);
                    productPropVo.convertEnum();
                    productIdMap.put(entry.getKey(), productPropVo);
                });
            }
            voList.forEach(vo -> vo.setPropVo(Optional.ofNullable(productIdMap.get(vo.getPId())).orElse(new ProductPropVo())));
        }catch (Exception e){
            logger.error("车贷产品VO绑定属性VO exp:{}",e);
        }
    }

    /**
     * 产品规则计算校验
     * @param exp
     * @param
     * @return
     */
    public Result<String> validateProductExp(String exp){
        List<String> exps = (List<String>)JSONArray.parseArray(exp,String.class);
        logger.info("validateProductExp - exp:{},customType:{}",exp);
        ValidateUtils.notNull(exps, HeilCode.E_400,"计算表达式为空");
        RuleReqPam rrp = new RuleReqPam();
        rrp.setCarAge("0");
        rrp.setSalePrice("0");
        for (String per : exps){
            try {
                validate(per, rrp, "1", "");// 每个参数值都为1
            } catch (Exception e) {
                logger.error("产品计算表达式错误 exp:{}",exp,e);
                return Result.fail(HeilCode.E_400, "计算表达式错误");
            }
        }
        return Result.suc(null,HeilCode.SUCCESS,"计算表达式正确");
    }

    /**
     * 产品公式计算校验
     * @param exp
     * @param formulaCode
     * @return
     */
    public Result<String> validateCalcModelExp(String exp, String formulaCode){
        String logTitle = "产品公式校验";

        logger.info("{} validateProductExp - exp:{},formulaCode:{}",logTitle, exp,formulaCode);
        ValidateUtils.notNull(exp, HeilCode.E_400,"计算表达式为空");
        // 如果有自定义公式的话
        CalcModelParamsForm form = new CalcModelParamsForm();
        form.setStatus(new Byte("1"));
        form.setFormulaCode(formulaCode);
        List<CalcModelParams> calcModelParamses = calcModelParamsService.queryByPage(new PageForm(false,form));
        logger.info("{} validateProductExp - calcModelParams数据:{}",logTitle, JSON.toJSON(calcModelParamses));
        if (!CollectionUtils.isEmpty(calcModelParamses)) {
            List<CalcModelParams> replaceParam = calcModelParamses.stream().filter(e -> e.getCustom() == 1).collect(Collectors.toList());
            logger.info("自定义公式有:条数:{},数据：{}", replaceParam == null?0:replaceParam.size(),JSON.toJSON(replaceParam));
            for(CalcModelParams param : replaceParam){
                // 如果包含特殊的参数类型-自定义的
                if (exp.contains(param.getParamName())) {
                    exp = exp.replace(param.getParamName(), "1");
                    logger.info("自定义公式替换后的内容:{}", exp);
                }
            }
        }

        try {
            validate(exp, calcModelParamses);// 每个参数值都为1
        } catch (Exception e) {
            logger.error("产品计算表达式错误 exp:{}",exp,e);
            return Result.fail(HeilCode.E_400, "计算表达式错误");
        }

        return Result.suc(null,HeilCode.SUCCESS,"计算表达式正确");
    }

    public static Object validate(String expression,RuleReqPam obj,String newCarPrice,String carry) throws IllegalArgumentException, IllegalAccessException{
        /*Jep jep = new Jep(); //一个数学表达式
//		String exp = "(950+1700+(590+a*0.014)+(120+a*0.0045)+(1700*0.15+(590+a*0.014)*0.15+(120+a*0.0045)*0.2))*0.9"; //给变量赋值
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) { //给计算公式的变量赋值
            fields[i].setAccessible(true);
            String field = (String) fields[i].get(obj);
            if (field==null||field=="") //当参数为空时 转换为1
                field = "1";
            jep.addVariable(fields[i].getName(), Double.parseDouble(field));
        }*/
        String flag=null;
        if ("0".equals(obj.getIsOld())) {//新车,车龄设为0
            obj.setCarAge("0");
            flag="0";
        }else if (Integer.parseInt(obj.getCarAge())>=0&&Integer.parseInt(obj.getCarAge())<=24) { //0-2年
            flag="1";
        }else if (Integer.parseInt(obj.getCarAge())>24) { //二手车龄上限 放开控制
            flag="2";
        }else {
            throw new AlertException(HeilCode.E_400, "车龄有误");
        }
        Double guidePrice=0d;
        /*if (newCarPrice!=null&&newCarPrice!="") {
            guidePrice = ComputeFeeUtil.lowPrice(Integer.parseInt(obj.getCarAge()), Double.parseDouble(newCarPrice), Double.parseDouble(obj.getSalePrice()), flag);
            jep.addVariable("guidePrice",guidePrice);
        }
        jep.parse(expression);//执行
        Double result = jep.evaluateD();//计算结果*/
        Double result = 0D;
        if (RuleClassNameEnum.R_SECURE.getName().equals(carry)) {//根据费用对象 判断进位方式
            result= ArithUtil.round_down_hundreds(result,0);//160506
        }else if(RuleClassNameEnum.R_INTE.getName().equals(carry)){
            result=result;
        }
        return result;
    }

    private static Object validate(String expression,List<CalcModelParams> calcModelParamses) throws Exception{
        ValidateUtils.notEmpty(calcModelParamses, HeilCode.E_400, "公式参数数据{calcModelParam}为空，请核实操作");
        logger.info("产品公式校验 - 获取参数数据为:{}", JSON.toJSON(calcModelParamses));
        /*Jep jep = new Jep();
        for (CalcModelParams param : calcModelParamses) {
            jep.addVariable(param.getParamName(), 1D);
        }
        jep.parse(expression);//执行
        Double result = jep.evaluateD();//计算结果*/
        return null;
    }

    /**
     * @param tuple 接口容器
     * @param function 循环迭代的集合元素的自定义函数
     * @param consumer 循环迭代的集合元素的自定义回调函数
     * @param <T> 循环迭代的集合元素
     * @param <R> 获取循环迭代的集合元素的属性
     */
    public <T,R> void bindConArgs(TwoTuple<List<Integer>,List<T>> tuple , Function<T,R> function, BiConsumer<T,String> consumer) {
        Map<String, List<SyArgControlRe>> argsMap = argControlFacade.queryList(tuple.getA());
        if(CollectionsTools.isNotEmpty(argsMap)) {
            tuple.getA().forEach(conArgType -> tuple.getB().forEach(vo -> {
                List<SyArgControlRe> syArgControls = argsMap.get(conArgType.toString());
                Map<String, String> map = syArgControls.stream().collect(Collectors.toMap(SyArgControlRe::getValue, SyArgControlRe::getName));
                String desc = Optional.ofNullable(map.get(function.apply(vo))).orElse("");
                consumer.accept(vo,desc);
            }));
        }
    }
}
