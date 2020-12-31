package com.mljr.heil.voconvertor;

import java.util.ArrayList;
import java.util.List;

/**
 * 值对像转化抽像类，目的将源对像转化为值对像。
 * 
 *
 * @param <V> 目标值对像
 * @param <B> 源对像
 */
public abstract class VoConvertor<V, B> {
	/**
	 * 勾子方法，由了子类实现具体的转换规则。
	 * @param bo 源对像
	 * @return V 目标值对像
	 */
	public abstract V convert(B bo);
	
	/**
	 * List转化方法
	 * @param boList 源对像列表
	 * @return List<V>目标值对像列表
	 */
	public List<V> convertList(List<B> boList) {
		List<V> voList = new ArrayList<V>();
		for(B bo : boList) {
			V vo = this.convert(bo);
			voList.add(vo);
		}
		return voList;
	}
}
