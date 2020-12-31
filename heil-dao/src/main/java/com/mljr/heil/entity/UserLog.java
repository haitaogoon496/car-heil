package com.mljr.heil.entity;

import com.mljr.heil.base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用户操作日志Entity
 * @Date : 2018/3/2 下午6:26
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLog extends BaseEntity{
    private static final long serialVersionUID = 6206971177620180353L;
    /**
     * 主键id
     */
    private Long flowId;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 操作人
     */
    private Integer userId;
    /**
     * 业务类型
     */
    private Integer authModel;
    /**
     * 操作日期
     */
    private String authDate;
    /**
     * 操作类型
     */
    private String authType;
    /**
     * 日志详细
     */
    private String authDetail;
}