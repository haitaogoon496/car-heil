package com.mljr.heil.core.querylink.handler;

import com.lyqc.base.page.PageForm;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.common.TwoTuple;
import com.mljr.heil.base.entity.BaseRule;
import com.mljr.heil.base.vo.BaseRuleVo;
import com.mljr.heil.base.vo.BaseVo;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.core.querylink.QueryLink;
import com.mljr.heil.core.querylink.QueryLinkContext;
import com.mljr.heil.form.PdRuleProductForm;
import com.mljr.heil.service.product.PdRuleProductService;

import java.util.List;

/**
 * @description: 抽象查询关联产品处理器
 * @Date : 2018/11/20 下午2:14
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public abstract class AbstractQueryLinkHandler<E extends BaseRule> implements QueryLink {
    /**
     * 处理器名称
     */
    protected String handlerName;
    /**
     * 依赖查询service接口
     */
    protected PdRuleProductService pdRuleProductService;
    /**
     * 依赖通用设置接口
     */
    protected CommonComponent commonComponent;
    /**
     * 查询数量
     */
    protected int count;
    /**
     * 查询实体对象
     */
    protected List<E> entities;
    /**
     * VO实体集合对象
     */
    protected List<BaseVo> voList;
    /**
     * 业务类型
     */
    protected TagConstant.BuzTypeEnum buzTypeEnum;
    /**
     * 查询条件
     */
    protected PageForm<PdRuleProductForm> form;

    /**
     * 默认构造函数
     */
    public AbstractQueryLinkHandler() {
    }

    /**
     * 构造函数
     * @param handlerName 处理器名称
     * @param buzTypeEnum 业务类型 已关联或未关联
     */
    public AbstractQueryLinkHandler(String handlerName, TagConstant.BuzTypeEnum buzTypeEnum) {
        this.handlerName = handlerName;
        this.buzTypeEnum = buzTypeEnum;
    }

    /**
     * 初始化相关成员变量
     * @param context
     */
    protected void prepare(QueryLinkContext context){
        this.pdRuleProductService = context.getPdRuleProductService();
        this.commonComponent = context.getCommonComponent();
        this.form = context.getForm();
    }

    /**
     * 查询关联数据
     */
    protected void query(){
        entities = queryLink(form);
        count = queryLinkCount(form);
    }

    /**
     * 相关设置调用
     */
    protected void call(QueryLinkContext context){
        context.setCount(count);
        voList = convertVo();
        context.setVoList(voList);
    }

    /**
     * entities 转换 vo
     * @return
     */
    abstract List<BaseVo> convertVo();

    /**
     * 对查询的VO集合绑定标签字段
     */
    protected void bindTags(){
        if(null == voList || voList.isEmpty()){
            return;
        }
        commonComponent.bindTags(TwoTuple.newInstance(buzTypeEnum.getIndex(),voList),
                (rule) -> Integer.valueOf(((BaseRuleVo)rule).getRuleSeq()),(rule, tags) -> ((BaseRuleVo)rule).setTags(tags));
    }

    /**
     * 外部调用公共方法
     * @param context
     */
    public final void execute(QueryLinkContext context){
        prepare(context);
        query();
        call(context);
        bindTags();
    }
}
