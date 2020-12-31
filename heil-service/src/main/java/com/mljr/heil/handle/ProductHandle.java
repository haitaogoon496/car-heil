package com.mljr.heil.handle;

import com.lyqc.base.common.Result;
import com.mljr.heil.vo.common.ModifyStatusVo;

/**
 * @description: 产品模块相关接口
 * @Date : 下午6:35 2018/3/1
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
public interface ProductHandle {
    /**
     * 产品状态更新
     * @param param
     * @return
     */
    Result<String> modifyStatus(ModifyStatusVo param);
}
