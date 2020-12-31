package com.mljr.heil.mapper;

import com.mljr.heil.base.entity.BaseDealerRes;
import com.mljr.heil.base.mapper.BaseMapper;
import com.mljr.heil.entity.RateDealer;
import com.mljr.heil.form.RateDealerForm;

import java.util.List;

public interface RateDealerMapper extends BaseMapper<BaseDealerRes, BaseDealerRes,RateDealerForm> {
    
	/**
	 * 
		* @Title: delByParams  
		* @Description: 根据条件删除
		* @param @param form
		* @param @return    参数  
		* @return int    返回类型  
		* @throws
	 */
	int delByParams(RateDealerForm form);
	/**
	 * 批量插入(没有即插入，有的话不做处理)
	 * @param baseDealerResList
	 * @return
	 */
	int batchInsertIgnore(List<BaseDealerRes> baseDealerResList);
}