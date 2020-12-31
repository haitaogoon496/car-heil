package com.mljr.heil.document;

import lombok.Data;

/**
 * @description: Document基类
 * @Date : 2019/3/25 下午7:32
 * @Author : 石冬冬-Seig Heil
 */
@Data
public class BaseDocument {
    /**
     * 集合名称
     */
    private String collectionName;
    /**
     * ID生成前缀
     */
    private String idPrefix;
    /**
     * mongodb 主键id
     */
    private String id;
    /**
     * 创建时间
     */
    private String createTime;
}
