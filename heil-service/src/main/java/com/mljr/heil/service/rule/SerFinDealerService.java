package com.mljr.heil.service.rule;


import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.service.BaseService;
import com.mljr.heil.form.SerFinDealerForm;

import java.util.List;

/**
 * @description:
 * @Date : 2018/2/8 14:31
 * @Author : lihaitao
 */
public interface SerFinDealerService extends BaseService<BaseDealerRes,BaseDealerRes,DealerQueryForm> {


    /**
     * 根据条件删除
     * @param form
     * @return
     */
    int deleteByParams(SerFinDealerForm form);

}
