package com.mljr.heil.core.bindlink;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @description: 数据关联处理接口
 * @Date : 2018/10/17 下午6:34
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public interface LinkProcess {
    /**
     * 真正要处理的规则ID或产品ID
     * 该接口，需要子类实现查询库，根据页面输入规则ID条件，并返回查询对象集合获取对应主键ID集合
     * @return
     */
    List<Integer> dataList();
    /**
     * 处理数据
     * @param c1 把处理完毕合法的规则ID回调给消费发使用
     * @param c2 把处理完毕不合法的规则ID及操作日志对象回调给消费方使用
     */
    void handleData(Consumer<List<Integer>> c1, BiConsumer<List<Integer>,JSONObject> c2);
    /**
     * 以门店维度-数据绑定关联
     * @param dealerCodes 要关联的经销商门店编码
     * @param linkList 要关联的费用规则ID或产品ID
     */
    void bindDealer(List<Integer> dealerCodes, List<Integer> linkList);
    /**
     * 以产品维度-数据绑定关联
     * @param productIds 关联的产品ID集合
     * @param linkList 要关联的费用规则ID或产品ID
     */
    void bindProduct(List<Integer> productIds, List<Integer> linkList);
    /**
     * 是否执行跳跃
     * @return
     */
    default boolean skip(){return false;}
}
