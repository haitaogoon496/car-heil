package com.mljr.heil.mapper;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.CalcFormulaParams;
import com.mljr.heil.form.CalcFormulaParamsForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CalcFormulaParamsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CalcFormulaParams record);

    int insertSelective(CalcFormulaParams record);

    CalcFormulaParams selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CalcFormulaParams record);

    int updateByPrimaryKey(CalcFormulaParams record);

    /**
     * 分页加载数据
     * @param form
     * @return
     */
    List<CalcFormulaParams> queryByPage(PageForm<CalcFormulaParamsForm> form);

    /**
     * 分页查询条数
     * @param form
     * @return
     */
    int queryCount(PageForm<CalcFormulaParamsForm> form);

    /**
     * 根据 formulaCode 关联的 param ID 列表
     * @param formulaCode
     * @return
     */
    List<Integer> queryParamIdListByFormulaCode(@Param("formulaCode") String formulaCode);

    /**
     * 删除所有符合 formulaCode 记录
     * @param formulaCode
     * @return
     */
    int deleteByFormulaCode(String formulaCode);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(List<CalcFormulaParams> list);
}