package com.mljr.heil.service.calc;

import com.lyqc.base.page.PageForm;
import com.mljr.heil.entity.CalcFormulaParams;
import com.mljr.heil.form.CalcFormulaParamsForm;

import java.util.List;

/**
 * 公式参数关联 Service
 * @author lingyu.shang
 */
public interface CalcFormulaParamsService {

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
    List<Integer> queryParamIdListByFormulaCode(String formulaCode);

    /**
     * 删除公式所有合法参数
     * @param formulaCode
     * @return
     */
    int deleteByFormulaCode(String formulaCode);

    /**
     * 批量插入数据
     * @param list
     * @return
     */
    int batchInsert(List<CalcFormulaParams> list);
}
