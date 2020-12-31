package com.mljr.heil.facade.common;

import com.alibaba.fastjson.JSON;
import com.lyqc.base.common.Result;
import com.lyqc.base.enums.RemoteEnum;
import com.lyqc.base.enums.UserOperateLogConstant;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.heil.enums.TagConstant;
import com.mljr.annotation.Action;
import com.mljr.annotation.LogMonitor;
import com.mljr.common.TwoTuple;
import com.mljr.enums.LogTitleEnum;
import com.mljr.heil.base.service.BaseFacade;
import com.mljr.heil.common.annotation.RedisCache;
import com.mljr.redis.service.RedisUtil;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.enums.DictionaryConstant;
import com.mljr.heil.common.exception.BizException;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.component.CommonComponent;
import com.mljr.heil.component.UserOperateLogComponent;
import com.mljr.heil.entity.ConfigParams;
import com.mljr.heil.form.ConfigParamsForm;
import com.mljr.heil.service.common.ConfigParamsService;
import com.mljr.heil.vo.config.ConfigParamsVo;
import com.mljr.heil.voconvertor.config.ConfigParamsVoConvertor;
import com.mljr.redis.enums.BuzType;
import com.mljr.redis.enums.FlushType;
import com.mljr.util.CollectionsTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description: 系统参数配置
 * @Date : 2018/4/12 18:22
 * @Author : lihaitao
 */
@Component
public class ConfigParamsFacade implements BaseFacade<ConfigParamsVo,ConfigParams,Integer,ConfigParamsForm> {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = LogTitleEnum.CONFIG_PARAMS.getName();
    private final UserOperateLogConstant.AuthModelEnumForProduct authModelEnum = UserOperateLogConstant.AuthModelEnumForProduct.PD_CONFIG_PARAM;
    /**
     * 默认全局参数的存活时间是1小时（60*60）
     */
    private final long ALIVE_TIME = 3600;
    /**
     * 每组成员100个元素
     */
    private final int EACH_MEMBER = 100;
    @Autowired
    private ConfigParamsService configParamsService;
    @Autowired
    private UserOperateLogComponent userOperateLogComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private RedisUtil redisUtil;

    @LogMonitor("全局配置参数")
    @Override
    public Result<PageVO<ConfigParamsVo>> loadRecords(PageForm<ConfigParamsForm> form) {
        List<ConfigParamsVo> voList = Collections.emptyList();
        int count = 0;
        try {
            List<ConfigParams> queryList = this.configParamsService.queryByPage(form);
            if (CollectionUtils.isEmpty(queryList)) {
                return Result.failInEmptyRecord(null);
            }
            count = this.configParamsService.queryCount(form);
            voList = new ConfigParamsVoConvertor().convertList(queryList);
            commonComponent.bindTags(TwoTuple.newInstance(TagConstant.BuzTypeEnum.PD_CONFIG_PARAM.getIndex(),voList),
                    paramVo -> Integer.valueOf(paramVo.getId()),(paramVo, tags) -> paramVo.setTags(tags));
        } catch (Exception e) {
            LOGGER.error("{}loadRecords加载数据异常,form={}",LOG_TITLE, JSON.toJSON(form),e);
            return Result.failInServer(new PageVO<>(form.getDraw(),count,voList));
        }
        return Result.suc(new PageVO<>(form.getDraw(),count,voList));
    }

    @LogMonitor("全局配置参数")
    @Override
    public Result<ConfigParamsVo> queryRecord(Integer primaryKey) {
        ConfigParamsVo vo = null;
        try {
            ConfigParams record = this.configParamsService.queryRecord(primaryKey);
            if(null == record ){
                return Result.failInEmptyRecord(null);
            }
            vo = new ConfigParamsVoConvertor().convert(record);
        } catch (Exception e) {
            LOGGER.error("{}查询异常,id={}",LOG_TITLE,primaryKey,e);
            return Result.failInServer(vo);
        }
        return Result.suc(vo);
    }

    @Override
    @Transactional(rollbackFor = {BizException.class,Exception.class})
    @RedisCache(buzType = BuzType.PD_CONFIG_PARAMS,flushType = FlushType.SET,aliveTime = 3600)
    public Result<String> saveRecord(ConfigParams record) {
        return commonComponent.saveRecord(record,authModelEnum,LOG_TITLE,configParamsService,entity -> {});
    }

    /**
     * 状态更新
     * @param record
     * @return
     */
    @RedisCache(buzType = BuzType.PD_CONFIG_PARAMS,flushType = FlushType.SET)
    public Result<String> modifyStatus(ConfigParams record) {
        LOGGER.info("{} - 状态更新 - 参数:{}", LOG_TITLE, JSON.toJSON(record));
        ValidateUtils.notNull(record.getId(), HeilCode.E_400,"参数主键不能为空");
        ValidateUtils.notEquals(record.getId(), 0, HeilCode.E_400, "参数主键错误");

        this.configParamsService.updateRecord(record);
        this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("状态更新param={0}",JSON.toJSON(record)));
        return Result.suc();
    }

    @Override
    public Result<String> deleteRecord(Integer primaryKey) {
        return commonComponent.deleteRecord(primaryKey,LOG_TITLE,configParamsService,(PK) ->
                saveLog(UserOperateLogConstant.AuthTypeEnum.DELETE, MessageFormat.format("删除primaryKey={0}",PK))
        );
    }

    /**
     * 全量配置参数刷新到Redis 缓存中
     * @return
     */
    public Result<String> flushRedis(){
        ExecutorService executorService  = Executors.newFixedThreadPool(5);
        try {
            List<ConfigParams> list = configParamsService.queryListForFlushRedis();
            if(CollectionsTools.isNotEmpty(list)){
                LOGGER.info("{}size={}",LOG_TITLE,list.size());
                List<List<ConfigParams>> groupList = CollectionsTools.group(list,EACH_MEMBER);
                groupList.forEach(group -> executorService.submit(() -> group.forEach(param -> {
                    String paramKey = param.getParamKey();
                    redisUtil.setWithPrefix(paramKey,param,ALIVE_TIME);
                    ConfigParams fetch = redisUtil.getWithPrefix(paramKey,ConfigParams.class);
                    LOGGER.info("{}load redis,key={},value={}",LOG_TITLE,paramKey, JSON.toJSON(fetch));
                })));
            }
        } catch (Exception e) {
            LOGGER.error("{}刷新redis异常",LOG_TITLE,e);
        }finally {
            if(null !=executorService && !executorService.isShutdown()){
                executorService.shutdown();
            }
        }
        return Result.suc("刷新成功");
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


    /**
     * 根据 paramKey 查询 参数对象
     * @param paramKey
     * @param clazz 泛型类
     * @param <T> 具体泛型类
     * @return
     */
    public <T> T getValueByParamKey(@NotNull String paramKey, Class<T> clazz) {
        T value = null;
        String classSimpleName = clazz.getSimpleName();
        ConfigParams pdConfigParams = configParamsService.queryByParamKey(paramKey);
        if(null != pdConfigParams){
            String paramValue = pdConfigParams.getParamValue();
            switch (classSimpleName){
                case "String":
                    value = (T)paramValue;
                    break;
                case "Integer":
                    value = (T)Integer.valueOf(paramValue);
                    break;
                case "Long":
                    value = (T)Long.valueOf(paramValue);
                    break;
                case "Double":
                    value = (T)Double.valueOf(paramValue);
                    break;
                case "Short":
                    value = (T)Short.valueOf(paramValue);
                    break;
                case "BigDecimal":
                    value = (T) new BigDecimal(paramValue);
                    break;
                case "Boolean":
                    if("true".equalsIgnoreCase(paramValue) || "1".equalsIgnoreCase(paramValue) || "yes".equalsIgnoreCase(paramValue)){
                        value = (T)Boolean.TRUE;
                    }
                    if("false".equalsIgnoreCase(paramValue) || "0".equalsIgnoreCase(paramValue) || "no".equalsIgnoreCase(paramValue)){
                        value = (T)Boolean.FALSE;
                    }
                    break;
                default:
                    break;
            }
        }
        return value;
    }

    /**
     * 开关控制（开启/关闭）
     * @param record
     * @return
     */
    @LogMonitor(value = "全局配置参数", action = @Action("开关控制"))
    public Result<String> modifySwitch(ConfigParams record) {
        try {
            Assert.notNull(record.getId(), "配置ID不能为空");
            Assert.notNull(record.getParamValue(), "配置值不能为空");
            Assert.notNull(DictionaryConstant.YesOrNoEnum.getByIndex(Integer.valueOf(record.getParamValue())), "配置值不符合要求");
            this.configParamsService.updateRecord(record);
            this.saveLog(UserOperateLogConstant.AuthTypeEnum.UPDATE, MessageFormat.format("开关控制param={0}",JSON.toJSON(record)));
            return Result.suc();
        } catch (NumberFormatException e) {
            LOGGER.error("{}, 开关控制参数值有误",LOG_TITLE,e);
            return Result.fail(RemoteEnum.ILLEGAL_ARGUMENTS);
        }
    }
}
