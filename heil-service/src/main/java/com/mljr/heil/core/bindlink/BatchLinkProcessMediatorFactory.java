package com.mljr.heil.core.bindlink;

import com.mljr.heil.param.config.BatchLinkParam;

/**
 * @description: 批量关联设置处理中介者工厂
 * @Date : 2019/2/12 下午5:39
 * @Author : 石冬冬-Seig Heil
 */
public final class BatchLinkProcessMediatorFactory {
    /**
     * 返回具体处理中介者类
     * @param buzType
     * @return
     */
    public static AbstractBatchLinkProcessMediator create(BatchLinkParam.ChooseTypeEnum buzType){
        switch (buzType){
            case dealerScope:
                return new BatchLinkDealerProcessMediator();
            case productScope:
                return new BatchLinkProductProcessMediator();
        }
        return null;
    }
}
