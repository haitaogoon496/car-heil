package com.mljr.heil.core.querylink;

import com.lyqc.heil.enums.TagConstant.BuzTypeEnum;
import com.mljr.heil.common.exception.BizException;
import com.mljr.heil.core.querylink.handler.AbstractQueryLinkHandler;
import com.mljr.heil.core.querylink.handler.AccountQueryLinkHandler;
import com.mljr.heil.core.querylink.handler.GpsQueryLinkHandler;
import com.mljr.heil.core.querylink.handler.InsuranceQueryLinkHandler;
import com.mljr.heil.core.querylink.handler.RateQueryLinkHandler;
import com.mljr.heil.core.querylink.handler.SerFinQueryLinkHandler;

/**
 * @description: 查询关联产品处理器工厂
 * @Date : 2018/11/20 下午6:03
 * @Author : 石冬冬-Seig Heil(dongdong.shi@mljr.com)
 */
public final class QueryLinkHandlerFactory {
    final static int SECOND_YEAR = 2;
    final static int THIRD_YEAR = 3;
    /**
     * 创建处理器
     * @param buzTypeEnum
     * @return
     */
    public static final AbstractQueryLinkHandler create(BuzTypeEnum buzTypeEnum){
        switch (buzTypeEnum){
            case SER_FIN_RULE:
                return new SerFinQueryLinkHandler();
            case RATE_RULE:
                return new RateQueryLinkHandler();
            case GPS_RULE:
                return new GpsQueryLinkHandler();
            case INSURANSE_SECOND_YEAR:
                return new InsuranceQueryLinkHandler(SECOND_YEAR);
            case INSURANSE_THIRD_YEAR:
                return new InsuranceQueryLinkHandler(THIRD_YEAR);
            case ACCOUNT_RULE:
                return new AccountQueryLinkHandler();
            default:
                throw new BizException("illegal buzTypeEnum index = "+ buzTypeEnum.getIndex());
        }
    }
}
