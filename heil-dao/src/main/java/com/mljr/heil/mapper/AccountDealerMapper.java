package com.mljr.heil.mapper;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.form.AccountDealerForm;

public interface AccountDealerMapper extends BaseMapper<BaseDealerRes, BaseDealerRes,AccountDealerForm>{

    /**
     *
     * @Title: delByParams
     * @Description: 根据条件删除
     * @param @param form
     * @param @return    参数
     * @return int    返回类型
     * @throws
     */
    int delByParams(AccountDealerForm form);
}