package com.mljr.heil.vo.common;

import com.mljr.heil.base.vo.BaseVo;
import lombok.Data;

/**
 * @description: 用户操作日志
 * @Date : 2018/6/10 下午9:00
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
@Data
public class UserLogVo extends BaseVo {
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
    private String authModelDesc;
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
