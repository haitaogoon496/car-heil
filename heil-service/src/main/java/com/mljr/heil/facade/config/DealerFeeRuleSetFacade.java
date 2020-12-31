package com.mljr.heil.facade.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.heil.enums.TagConstant;
import com.lyqc.heil.enums.TagConstant.BuzTypeEnum;
import com.mljr.annotation.LogMonitor;
import com.mljr.annotation.OvalValidator;
import com.mljr.common.TwoTuple;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.enums.ApplyDealerTableEnum;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.DealerComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.core.bindlink.AbstractBatchLinkProcessMediator;
import com.mljr.heil.core.bindlink.BatchLinkProcessMediatorFactory;
import com.mljr.heil.core.bindlink.LinkProcessBuzType;
import com.mljr.heil.core.bindlink.LinkProcessContext;
import com.mljr.heil.core.bindlink.RuleServiceEnum;
import com.mljr.heil.entity.AccountRule;
import com.mljr.heil.entity.GpsRule;
import com.mljr.heil.entity.PdFeeRule;
import com.mljr.heil.entity.Product;
import com.mljr.heil.entity.RateRule;
import com.mljr.heil.entity.SerFinRule;
import com.mljr.heil.entity.YanbaoRule;
import com.mljr.heil.facade.rule.GpsRuleFacade;
import com.mljr.heil.facade.rule.SerFinRuleFacade;
import com.mljr.heil.facade.rule.YanbaoRuleFacade;
import com.mljr.heil.form.DealerRuleSetForm;
import com.mljr.heil.mapper.AccountRuleMapper;
import com.mljr.heil.mapper.GpsRuleMapper;
import com.mljr.heil.mapper.PdFeeRuleMapper;
import com.mljr.heil.mapper.ProductMapper;
import com.mljr.heil.mapper.RateRuleMapper;
import com.mljr.heil.mapper.SerFinRuleMapper;
import com.mljr.heil.mapper.YanbaoRuleMapper;
import com.mljr.heil.param.config.BatchLinkParam;
import com.mljr.heil.service.common.SyDealerService;
import com.mljr.heil.service.product.ProductDealerService;
import com.mljr.heil.service.product.ProductService;
import com.mljr.heil.service.product.impl.PdRuleProductServiceImpl;
import com.mljr.heil.service.rule.AccountDealerService;
import com.mljr.heil.service.rule.AccountRuleService;
import com.mljr.heil.service.rule.GpsDealerService;
import com.mljr.heil.service.rule.GpsRuleService;
import com.mljr.heil.service.rule.PdFeeDealerService;
import com.mljr.heil.service.rule.PdFeeProductService;
import com.mljr.heil.service.rule.PdFeeRuleService;
import com.mljr.heil.service.rule.RateDealerService;
import com.mljr.heil.service.rule.RateRuleService;
import com.mljr.heil.service.rule.SerFinDealerService;
import com.mljr.heil.service.rule.SerFinRuleService;
import com.mljr.heil.service.rule.YanbaoDealerService;
import com.mljr.heil.service.rule.YanbaoRuleService;
import com.mljr.heil.vo.config.DealerFeeRuleVo;
import com.mljr.heil.vo.product.ProductVo;
import com.mljr.heil.vo.rule.AccountRuleVo;
import com.mljr.heil.vo.rule.GpsRuleVo;
import com.mljr.heil.vo.rule.PdFeeRuleVo;
import com.mljr.heil.vo.rule.RateRuleVo;
import com.mljr.heil.vo.rule.SerFinRuleVo;
import com.mljr.heil.vo.rule.YanbaoRuleVo;
import com.mljr.heil.voconvertor.product.ProductVoConvertor;
import com.mljr.heil.voconvertor.rule.AccountRuleVoConvertor;
import com.mljr.heil.voconvertor.rule.GpsRuleVoConvertor;
import com.mljr.heil.voconvertor.rule.PdFeeRuleVoConvertor;
import com.mljr.heil.voconvertor.rule.RateRuleVoConvertor;
import com.mljr.heil.voconvertor.rule.SerFinRuleVoConvertor;
import com.mljr.heil.voconvertor.rule.YanbaoRuleVoConvertor;
import com.mljr.util.CollectionsTools;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:门店费用规则配置
 * @Date : 2018/6/7$ 17:49$
 * @Author : liht
 */
@Component
public class DealerFeeRuleSetFacade {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = "批量关联设置";
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.DEALER_FEE_RULE_SET;
    @Autowired
    private SyDealerService syDealerService;
    @Autowired
    private GpsRuleService gpsRuleService;
    @Autowired
    private RateRuleService rateRuleService;
    @Autowired
    private SerFinRuleService serFinRuleService;
    @Autowired
    private ProductService productService;
    @Autowired
    private GpsDealerService gpsDealerService;
    @Autowired
    private RateDealerService rateDealerService;
    @Autowired
    private SerFinDealerService serFinDealerService;
    @Autowired
    private ProductDealerService productDealerService;
    @Autowired
    private AccountRuleService accountRuleService;
    @Autowired
    private AccountDealerService accountDealerService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private RateRuleMapper rateRuleMapper;
    @Autowired
    private GpsRuleMapper gpsRuleMapper;
    @Autowired
    private SerFinRuleMapper serFinRuleMapper;
    @Autowired
    private YanbaoRuleMapper yanbaoRuleMapper;
    @Autowired
    private AccountRuleMapper accountRuleMapper;
    @Autowired
    private PdFeeRuleMapper pdFeeRuleMapper;
    @Autowired
    private GpsRuleFacade gpsRuleFacade;
    @Autowired
    private SerFinRuleFacade serFinRuleFacade;
    @Autowired
    private PdRuleProductServiceImpl pdRuleProductService;
    @Autowired
    private YanbaoRuleService yanbaoRuleService;
    @Autowired
    private YanbaoDealerService yanbaoDealerService;
    @Autowired
    private PdFeeRuleService pdFeeRuleService;
    @Autowired
    private PdFeeDealerService pdFeeDealerService;
    @Autowired
    private PdFeeProductService pdFeeProductService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private YanbaoRuleFacade yanbaoRuleFacade;
    /**
     *
     */
    protected Map<RuleServiceEnum,BaseService> serviceMap;

    @Autowired
    protected DealerComponent dealerComponent;

    @PostConstruct
    public void init(){
        LOGGER.info("############### start init serviceMap ###############");
        serviceMap = new ConcurrentHashMap<RuleServiceEnum,BaseService>(){{
            //贷款利率
            put(RuleServiceEnum.RATE,rateRuleService);
            put(RuleServiceEnum.RATE_DEALER,rateDealerService);
            //GPS
            put(RuleServiceEnum.GPS,gpsRuleService);
            put(RuleServiceEnum.GPS_DEALER,gpsDealerService);
            //平台费
            put(RuleServiceEnum.SER_FIN,serFinRuleService);
            put(RuleServiceEnum.SER_FIN_DEALER,serFinDealerService);
            //延保费
            put(RuleServiceEnum.YANBAO,yanbaoRuleService);
            put(RuleServiceEnum.YANBAO_DEALER,yanbaoDealerService);
            //产品
            put(RuleServiceEnum.PRODUCT,productService);
            put(RuleServiceEnum.PRODUCT_DEALER,productDealerService);
            //账号管理费
            put(RuleServiceEnum.ACCOUNT,accountRuleService);
            put(RuleServiceEnum.ACCOUNT_DEALER,accountDealerService);
            //人身保险费、续保押金、盗抢险
            put(RuleServiceEnum.FEE_RULE,pdFeeRuleService);
            put(RuleServiceEnum.FEE_RULE_DEALER,pdFeeDealerService);
            //费用关联产品
            put(RuleServiceEnum.FEE_PRODUCT,pdFeeProductService);
            //费用关联产品
            put(RuleServiceEnum.RULE_PRODUCT,pdRuleProductService);
        }};
        LOGGER.info("############### finish init serviceMap,size={} ###############");
    }

    /**
     * @description: 【批量关联设置】添加关联
     * @Date : 2018/6/7 18:05
     * @Author : lihaitao
     */
    public Result<DealerFeeRuleVo> dealerFeeRuleSet(BatchLinkParam batchLinkParam) {
        LOGGER.info("{} - 【批量关联设置】添加关联", LOG_TITLE);
        DealerFeeRuleVo vo;
        try {
            vo = bulkBindLink(batchLinkParam,LinkProcessBuzType.INSERT);
        } catch (Exception e) {
            LOGGER.error("{} - 【批量关联设置】添加关联,batchLinkParam={}", LOG_TITLE,JSON.toJSONString(batchLinkParam),e);
            return Result.fail(RemoteEnum.ERROR_IN_BUSINESS,e.getMessage());
        }
        return Result.suc(vo);
    }

    /**
     * @description: 【批量关联设置】删除关联
     * @Date : 2018/6/7 18:05
     * @Author : lihaitao
     */
    public Result<DealerFeeRuleVo> dealerFeeRuleDel(BatchLinkParam batchLinkParam) {
        LOGGER.info("{} - 【批量关联设置】删除关联", LOG_TITLE);
        DealerFeeRuleVo vo;
        try {
            vo = bulkBindLink(batchLinkParam,LinkProcessBuzType.DELETE);
        } catch (Exception e) {
            LOGGER.error("{} - 【批量关联设置】添加关联,batchLinkParam={}", LOG_TITLE,JSON.toJSONString(batchLinkParam),e);
            return Result.fail(RemoteEnum.ERROR_IN_BUSINESS,e.getMessage());
        }
        return Result.suc(vo);
    }

    @Transactional(rollbackFor = Exception.class)
    protected DealerFeeRuleVo bulkBindLink(BatchLinkParam batchLinkParam, LinkProcessBuzType buzType){
        DealerFeeRuleVo feeRuleVo = new DealerFeeRuleVo();
        LinkProcessContext context = new LinkProcessContext();
        context.setBuzType(buzType);
        context.setBatchLinkParam(batchLinkParam);
        context.setFeeRuleVo(feeRuleVo);
        context.setServiceMap(serviceMap);
        context.setDealerComponent(dealerComponent);
        AbstractBatchLinkProcessMediator batchLinkDealerProcessMediator = BatchLinkProcessMediatorFactory.create(BatchLinkParam.ChooseTypeEnum.getByIndex(batchLinkParam.getChooseType()));
        batchLinkDealerProcessMediator.setContext(context);
        batchLinkDealerProcessMediator.execute();
        saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE,context.getOperateLog().toJSONString());
        return feeRuleVo;
    }

    /**
     * @description:查询当前门店的绑定的规则
     * @Date : 2018/6/7 18:05
     * @Author : lihaitao
     */
    @LogMonitor("查询当前门店的绑定的规则")
    public Result<PageVO> queryBindRulesForDealer(PageForm<DealerRuleSetForm> form) {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(form.getForm());
        if (CollectionsTools.isNotEmpty(violations)) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), violations.get(0).getMessage());
        }

        BuzTypeEnum buzTypeEnum = BuzTypeEnum.getByIndex(form.getForm().getBuzType());
        // 状态为可用
        form.getForm().setStatus(1);
        switch (buzTypeEnum) {
            // 利率规则
            case RATE_RULE:
                List<RateRule> rateRules = rateRuleMapper.queryRateRulesForDealer(form);
                if (CollectionsTools.isEmpty(rateRules)) {
                    return Result.failInEmptyRecord(null);
                }
                pdRuleProductService.formatRateRule(rateRules);
                int count = rateRuleMapper.queryRateRulesForDealerCount(form);
                List<RateRuleVo> voList = new RateRuleVoConvertor().convertList(rateRules);
                commonComponent.bindTags(TwoTuple.newInstance(TagConstant.BuzTypeEnum.RATE_RULE.getIndex(),voList),
                        rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
                return Result.suc(new PageVO<>(form.getDraw(), count, voList));
            // gps规则
            case GPS_RULE:
                List<GpsRule> gpsRules = gpsRuleMapper.queryGpsRulesForDealer(form);
                if (CollectionsTools.isEmpty(gpsRules)) {
                    return Result.failInEmptyRecord(null);
                }
                int gpsCount = gpsRuleMapper.queryGpsRulesForDealerCount(form);
                List<GpsRuleVo> gpsRuleVos = new GpsRuleVoConvertor().convertList(gpsRules);
                gpsRuleFacade.formatGpsRuleVo(gpsRuleVos);
                commonComponent.bindTags(TwoTuple.newInstance(BuzTypeEnum.GPS_RULE.getIndex(),gpsRuleVos),
                        rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
                return Result.suc(new PageVO<>(form.getDraw(), gpsCount, gpsRuleVos));
            // 平台费规则
            case SER_FIN_RULE:
                List<SerFinRule> serFinRules = serFinRuleMapper.querySerRulesForDealer(form);
                if (CollectionsTools.isEmpty(serFinRules)) {
                    return Result.failInEmptyRecord(null);
                }
                int serCount = serFinRuleMapper.querySerRulesForDealerCount(form);
                List<SerFinRuleVo> serFinRuleVos = new SerFinRuleVoConvertor().convertList(serFinRules);
                serFinRuleFacade.formatSerFinRule(serFinRules, serFinRuleVos);
                commonComponent.bindTags(TwoTuple.newInstance(BuzTypeEnum.SER_FIN_RULE.getIndex(),serFinRuleVos),
                        rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
                return Result.suc(new PageVO<>(form.getDraw(), serCount, serFinRuleVos));
            // 账户管理费
            case ACCOUNT_RULE:
                List<AccountRule> accountRules = accountRuleMapper.queryAccountRulesForDealer(form);
                if (CollectionsTools.isEmpty(accountRules)) {
                    return Result.failInEmptyRecord(null);
                }
                int accCount = accountRuleMapper.queryAccountRulesForDealerCount(form);
                List<AccountRuleVo> accountRuleVos = new AccountRuleVoConvertor().convertList(accountRules);
                commonComponent.bindTags(TwoTuple.newInstance(BuzTypeEnum.ACCOUNT_RULE.getIndex(),accountRuleVos),
                        rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
                return Result.suc(new PageVO<>(form.getDraw(), accCount, accountRuleVos));
            // 第二年保险
            case INSURANSE_SECOND_YEAR:
                form.getForm().setClassify(2);
                List<YanbaoRule> yanbaoRules = yanbaoRuleMapper.queryYanbaoRulesForDealer(form);
                if (CollectionsTools.isEmpty(yanbaoRules)) {
                    return Result.failInEmptyRecord(null);
                }
                int yanbaoCount = yanbaoRuleMapper.queryYanbaoRulesForDealerCount(form);
                List<YanbaoRuleVo> yanbaoRuleVos = new YanbaoRuleVoConvertor().convertList(yanbaoRules);
                yanbaoRuleFacade.formatYanbaoRules(yanbaoRules, yanbaoRuleVos);
                commonComponent.bindTags(TwoTuple.newInstance(BuzTypeEnum.INSURANSE_SECOND_YEAR.getIndex(),yanbaoRuleVos),
                        rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
                return Result.suc(new PageVO<>(form.getDraw(), yanbaoCount, yanbaoRuleVos));

            // 第三年保险
            case INSURANSE_THIRD_YEAR:
                form.getForm().setClassify(3);
                List<YanbaoRule> yanbaoRules3 = yanbaoRuleMapper.queryYanbaoRulesForDealer(form);
                if (CollectionsTools.isEmpty(yanbaoRules3)) {
                    return Result.failInEmptyRecord(null);
                }
                int yanbaoCount3 = yanbaoRuleMapper.queryYanbaoRulesForDealerCount(form);
                List<YanbaoRuleVo> yanbaoRuleVos3 = new YanbaoRuleVoConvertor().convertList(yanbaoRules3);
                yanbaoRuleFacade.formatYanbaoRules(yanbaoRules3, yanbaoRuleVos3);
                commonComponent.bindTags(TwoTuple.newInstance(BuzTypeEnum.INSURANSE_THIRD_YEAR.getIndex(),yanbaoRuleVos3),
                        rule -> Integer.valueOf(rule.getRuleSeq()),(rule, tags) -> rule.setTags(tags));
                return Result.suc(new PageVO<>(form.getDraw(), yanbaoCount3, yanbaoRuleVos3));
            // 车贷产品
            case PRODUCT:
                List<Product> products = productMapper.queryProductsForDealer(form);
                if (CollectionsTools.isEmpty(products)) {
                    return Result.failInEmptyRecord(null);
                }
                int productCount = productMapper.queryProductsForDealerCount(form);
                List<ProductVo> productVos = new ProductVoConvertor().convertList(products);
                commonComponent.bindProductProps(productVos);
                commonComponent.bindTags(TwoTuple.newInstance(BuzTypeEnum.PRODUCT.getIndex(),productVos),
                        rule -> Integer.valueOf(rule.getPId()),(rule, tags) -> rule.setTags(tags));
                return Result.suc(new PageVO<>(form.getDraw(), productCount, productVos));

            case PD_FEE:
                List<PdFeeRule> insuranceRules = pdFeeRuleMapper.queryPdFeeRulesForDealer(form);
                if (CollectionsTools.isEmpty(insuranceRules)) {
                    return Result.failInEmptyRecord(null);
                }
                int insuranceCount = pdFeeRuleMapper.queryPdFeeRulesForDealerCount(form);
                List<PdFeeRuleVo> insuranceRuleVos = new PdFeeRuleVoConvertor().convertList(insuranceRules);
                TagConstant.BuzTypeEnum tagEnum = BuzTypeEnum.getTagTypeByClassify(form.getForm().getClassify());
                ValidateUtils.notNull(tagEnum, HeilCode.E_500, "classify错误,请核实后操作");
                commonComponent.bindTags(TwoTuple.newInstance(tagEnum.getIndex(),insuranceRuleVos),
                        rule -> Integer.valueOf(rule.getId()),(rule, tags) -> rule.setTags(tags));
                return Result.suc(new PageVO<>(form.getDraw(), insuranceCount, insuranceRuleVos));

            default:
                return Result.failInEmptyRecord(null);
        }
    }

    /**
     * @description:查询当前门店的未绑定的规则
     * @Date : 2018/6/7 18:05
     * @Author : lihaitao
     */
    @LogMonitor("查询当前门店的未绑定的规则")
    public Result<PageVO> queryNoBindRulesForDealer(PageForm<DealerRuleSetForm> form) {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(form.getForm());
        if (CollectionsTools.isNotEmpty(violations)) {
            return Result.fail(RemoteEnum.ERROR_WITH_EMPTY_PARAM.getIndex(), violations.get(0).getMessage());
        }
        form.getForm().setBindFlag(false);

        return queryBindRulesForDealer(form);
    }

    @LogMonitor("为门店添加规则配置")
    @Transactional
    @OvalValidator
    public Result<String> addRulesForDealer(DealerRuleSetForm dealerRuleSetForm) {
        ValidateUtils.notNull(dealerRuleSetForm.getDealerTableIndex(), HeilCode.E_400, "dealerTableIndex不能为空");
        ValidateUtils.notEmpty(dealerRuleSetForm.getRuleIds(), HeilCode.E_400, "ruleIds不能为空");
        // 获取table
        ApplyDealerTableEnum tableName = ApplyDealerTableEnum.getByIndex(dealerRuleSetForm.getDealerTableIndex());

        dealerRuleSetForm.setTableName(tableName.getName());
        int count = syDealerService.batchInsert(dealerRuleSetForm);
        JSONObject jsonObject = new JSONObject(2);
        jsonObject.put("dealerCode", dealerRuleSetForm.getDealerCode());
        jsonObject.put("ruleIds", dealerRuleSetForm.getRuleIds());
        jsonObject.put("ruleType", tableName.getDesc());
        saveLog(UserOperateLogConstant.AuthTypeEnum.CREATE, JSON.toJSONString(jsonObject));
        return Result.suc(String.valueOf(count));
    }
    @LogMonitor("为门店删除规则配置")
    @Transactional
    @OvalValidator
    public Result<String> delRulesForDealer(DealerRuleSetForm dealerRuleSetForm) {
        ValidateUtils.notNull(dealerRuleSetForm.getDealerTableIndex(), HeilCode.E_400, "dealerTableIndex不能为空");
        ValidateUtils.notEmpty(dealerRuleSetForm.getRuleIds(), HeilCode.E_400, "ruleIds不能为空");
        // 获取table
        ApplyDealerTableEnum tableName = ApplyDealerTableEnum.getByIndex(dealerRuleSetForm.getDealerTableIndex());

        DealerQueryForm form = new DealerQueryForm();
        form.setRuleIds(dealerRuleSetForm.getRuleIds());
        form.setDealerScopes(Arrays.asList(dealerRuleSetForm.getDealerCode()));
        form.setTableName(tableName.getName());

        int count = syDealerService.batchDelete(form);
        JSONObject jsonObject = new JSONObject(2);
        jsonObject.put("dealerCode", dealerRuleSetForm.getDealerCode());
        jsonObject.put("ruleIds", dealerRuleSetForm.getRuleIds());
        jsonObject.put("ruleType", tableName.getDesc());
        saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, JSON.toJSONString(jsonObject));
        return Result.suc(String.valueOf(count));
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

    enum BuzType{
        INSERT,DELETE
    }
}
