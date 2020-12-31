package com.mljr.heil.core.bindlink;

import com.mljr.heil.core.bindlink.feerule.CarDiscountRuleLinkProcessor;
import com.mljr.heil.core.bindlink.feerule.CarInsuranceRuleLinkProcessor;
import com.mljr.heil.core.bindlink.feerule.EnjoyPackRuleLinkProcessor;
import com.mljr.heil.core.bindlink.feerule.LifeInsuranceRuleLinkProcessor;
import com.mljr.heil.core.bindlink.feerule.RentalCommissionRuleLinkProcessor;
import com.mljr.heil.core.bindlink.feerule.TheftProtectionRuleLinkProcessor;
import com.mljr.heil.core.bindlink.otherrule.AccountRuleLinkProcessor;
import com.mljr.heil.core.bindlink.otherrule.DealerLinkProcessor;
import com.mljr.heil.core.bindlink.otherrule.GpsRuleLinkProcessor;
import com.mljr.heil.core.bindlink.otherrule.ProductLinkProcessor;
import com.mljr.heil.core.bindlink.otherrule.RateRuleLinkProcessor;
import com.mljr.heil.core.bindlink.otherrule.SecondYearPremiumRuleLinkProcessor;
import com.mljr.heil.core.bindlink.otherrule.SerFinRuleLinkProcessor;
import com.mljr.heil.core.bindlink.otherrule.ThirdYearPremiumRuleLinkProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 抽象批量关联设置处理中介者
 * @Date : 2018/11/12 下午3:44
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Slf4j
public abstract class AbstractBatchLinkProcessMediator {
    protected final String LOG_TITLE = "批量关联设置";
    /**
     * 上下文对象
     */
    protected LinkProcessContext context;
    /**
     * 处理器集合
     */
    protected List<AbstractLinkProcessor> processorList;

    public AbstractBatchLinkProcessMediator() {
    }

    /**
     * 构造函数
     * @param context
     */
    public AbstractBatchLinkProcessMediator(LinkProcessContext context) {
        this.context = context;
    }

    /**
     * 初始化
     */
    protected void init(){
        processorList = new ArrayList<AbstractLinkProcessor>(){{
            add(new ProductLinkProcessor());
            add(new DealerLinkProcessor());
            add(new RateRuleLinkProcessor());
            add(new GpsRuleLinkProcessor());
            add(new SerFinRuleLinkProcessor());
            add(new AccountRuleLinkProcessor());
            add(new SecondYearPremiumRuleLinkProcessor());
            add(new ThirdYearPremiumRuleLinkProcessor());
            add(new LifeInsuranceRuleLinkProcessor());
            add(new RentalCommissionRuleLinkProcessor());
            add(new CarInsuranceRuleLinkProcessor());
            add(new CarDiscountRuleLinkProcessor());
            add(new TheftProtectionRuleLinkProcessor());
            add(new EnjoyPackRuleLinkProcessor());
        }};
    }

    /**
     * 处理门店或者产品ID
     */
    abstract void handleBatchSources();

    /**
     * 执行
     */
    public final void execute(){
        init();
        handleBatchSources();
        processorList.forEach(each -> each.execute(context));
    }

    public void setContext(LinkProcessContext context) {
        this.context = context;
    }
}
