package com.mljr.heil.facade.calc;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.annotation.Action;
import com.mljr.annotation.LogMonitor;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.CalcFormulaParams;
import com.mljr.heil.entity.CalcModelParams;
import com.mljr.heil.form.CalcFormulaParamsForm;
import com.mljr.heil.form.CalcModelParamsForm;
import com.mljr.heil.service.calc.CalcFormulaParamsService;
import com.mljr.heil.service.calc.CalcModelParamsService;
import com.mljr.heil.vo.calc.CalcFormulaParamsVo;
import com.mljr.heil.vo.calc.CalcModelParamsVo;
import com.mljr.heil.voconvertor.calc.CalcModelParamsVoConvertor;
import com.mljr.util.CollectionsTools;
import com.mljr.util.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 公式参数关联 Facade
 * @author lingyu.shang
 */
@Component
public class CalcFormulaParamsFacade {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.CALC_FORMULA_PARAMS.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.PD_CALC_FORMULA_PARAMS;

    @Autowired
    private CalcFormulaParamsService calcFormulaParamsService;

    @Autowired
    private CalcModelParamsService calcModelParamsService;

    @Autowired
    private UserOperateLogComponent userOperateLogComponent;

    @LogMonitor(value = "【公式参数关联】", action = @Action("分页加载数据"))
    public Result<PageVO<CalcFormulaParamsVo>> loadRecords(PageForm<CalcFormulaParamsForm> form) {
        int count = 0;
        try {
            // 1. 获取所有关联关系
            List<CalcFormulaParams> queryList = this.calcFormulaParamsService.queryByPage(form);
            if (CollectionUtils.isEmpty(queryList)) {
                return Result.failInEmptyRecord(null);
            }
            List<CalcFormulaParamsVo> voList = new ArrayList<>(queryList.size());
            // 2. 获取所有参数详细信息
            PageForm<CalcModelParamsForm> modelParam = new PageForm<>(false);
            List<CalcModelParams> calcModelParamsList = calcModelParamsService.queryByPage(modelParam);
            if(CollectionUtils.isEmpty(calcModelParamsList)) {
                return Result.fail(RemoteEnum.ERROR_IN_SERVER, "没有查到有效参数，请联系运营人员");
            }
            Map<Integer, List<CalcModelParams>> calcModelParamsMap = calcModelParamsList.stream().collect(Collectors.groupingBy(CalcModelParams::getId));
            // 3. 添加参数详细信息到关联关系列表，之所以分开获取，是为了保证 1 查询分页数量的准确性
            queryList.stream().forEach(param -> {
                String paramIds = param.getParamIds();
                List<String> paramList = Arrays.asList(paramIds.split(","));
                CalcFormulaParamsVo calcFormulaParamsVo = new CalcFormulaParamsVo();
                calcFormulaParamsVo.setFormulaCode(param.getFormulaCode());
                List<CalcModelParamsVo> paramsVoList = new ArrayList<>();
                for(String p : paramList) {
                    CalcModelParams calcModelParams = calcModelParamsMap.get(Integer.valueOf(p)).stream().findFirst().orElse(new CalcModelParams());
                    paramsVoList.add(new CalcModelParamsVoConvertor().convert(calcModelParams));
                }
                calcFormulaParamsVo.setParams(paramsVoList);
                voList.add(calcFormulaParamsVo);
            });
            count = this.calcFormulaParamsService.queryCount(form);
            return Result.suc(new PageVO<>(form.getDraw(),count,voList));
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,Collections.emptyList()));
        }
    }

    @LogMonitor(value = "【公式参数关联】", action = @Action("查询公式关联/未关联参数列表"))
    public Result<Map<String,List<CalcModelParamsVo>>> queryFormulaCodeParamsList(String formulaCode) {
        try {
            if(StringTools.isEmpty(formulaCode)) {
                return Result.fail(RemoteEnum.ILLEGAL_ARGUMENTS);
            }
            Map<String,List<CalcModelParamsVo>> map = new HashMap<>();
            PageForm<CalcModelParamsForm> modelParam = new PageForm<>(false);
            List<CalcModelParams> calcModelParamsList = calcModelParamsService.queryByPage(modelParam);
            List<CalcModelParamsVo> calcModelParamsVosList = new CalcModelParamsVoConvertor().convertList(calcModelParamsList);
            List<Integer> paramIds = calcFormulaParamsService.queryParamIdListByFormulaCode(formulaCode);
            List<CalcModelParamsVo> paramsList = Collections.emptyList();
            if(CollectionsTools.isNotEmpty(paramIds)) {
                paramsList = calcModelParamsVosList.stream().filter(param -> paramIds.contains(Integer.valueOf(param.getId()))).collect(Collectors.toList());
            }
            map.put("relParamsList", paramsList);
            map.put("allParamsList", calcModelParamsVosList);
            return Result.suc(map);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("{}查询公式关联/未关联参数列表,form={}",LOG_TITLE, formulaCode, e);
            return Result.fail(RemoteEnum.ERROR_IN_SERVER);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @LogMonitor(value = "【公式参数关联】", action = @Action("保存公式合法参数配置"))
    public Result<String> saveRecord(CalcFormulaParamsForm form) {
        if(form == null || StringTools.isEmpty(form.getFormulaCode())) {
            return Result.fail(RemoteEnum.ILLEGAL_ARGUMENTS);
        }
        String formulaCode = form.getFormulaCode();
        calcFormulaParamsService.deleteByFormulaCode(formulaCode);
        List<Integer> paramIds = form.getParamIds();
        if(CollectionsTools.isNotEmpty(paramIds)) {
            List<CalcFormulaParams> list = new ArrayList<>(paramIds.size());
            paramIds.forEach(paramId -> {
                CalcFormulaParams calcFormulaParams = new CalcFormulaParams();
                calcFormulaParams.setFormulaCode(formulaCode);
                calcFormulaParams.setParamId(paramId);
                list.add(calcFormulaParams);
            });
            calcFormulaParamsService.batchInsert(list);
        }
        this.saveLog(UserOperateLogConstant.AuthTypeEnum.CREATE, MessageFormat.format("公式合法参数关联param={0}", JSON.toJSONString(form)));
        return Result.suc();
    }

    /**
     * 保存操作日志
     * @param authTypeEnum
     * @param authDetail
     */
    public void saveLog(UserOperateLogConstant.AuthTypeEnum authTypeEnum,String authDetail){
        this.userOperateLogComponent.log(log -> {
            log.setAuthDetail(authDetail);
            log.setAuthModel(authModelEnum);
            log.setAuthType(authTypeEnum);
        });
    }

}
