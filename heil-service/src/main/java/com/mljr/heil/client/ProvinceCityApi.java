package com.mljr.heil.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.mljr.ding.auth.DingAuth;
import com.mljr.ding.client.DingRobotService;
import com.mljr.ding.dto.req.MarkdownDingRobotReq;
import com.mljr.heil.common.consts.HeilCode;
import com.mljr.heil.common.enums.ClientUrlApiEnum;
import com.mljr.heil.common.exception.AlertException;
import com.mljr.heil.common.util.HttpUtils;
import com.mljr.heil.common.util.ValidateUtils;
import com.mljr.heil.entity.SyDictionaryReg;
import com.mljr.heil.param.CityParam;
import com.mljr.heil.param.ProvinceParam;
import com.mljr.heil.param.RespDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 省份城市api 调用彩祥接口
 * @Date : 2018/6/28$ 14:12$
 * @Author : liht
 */
@Component
public class ProvinceCityApi {


    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String LOG_TITLE = "获取户籍省份城市接口";

    @Value("${car.staff.url}")
    private String carStaffUrl;
    @Autowired
    private DingRobotService dingRobotService;
    @Value("${spring.profiles.active}")
    private String enviroment;
    private String serviceName = "car-heil";
    /**
     * 获取省份
     * @return
     */
    public List<ProvinceParam> getProvinces() {
        String url = carStaffUrl + ClientUrlApiEnum.getProvinceApi.getName();
        RespDTO<List<SyDictionaryReg>> respDTO = null;

        try {
            logger.info("{} - 获取户籍省份,url={}", LOG_TITLE,url);
            String response = HttpUtils.doPost(url, new HashMap<String, String>(), "utf-8");
            logger.info("{} - 获取户籍省份结果->:{}", LOG_TITLE, JSON.toJSON(response));
            ValidateUtils.notEmpty(response, HeilCode.E_400, "获取省份失败,请稍后重试");
            respDTO = JSONObject.parseObject(response, new TypeReference<RespDTO<List<SyDictionaryReg>>>() {
            });
            ValidateUtils.notNull(respDTO, HeilCode.E_400, "获取户籍省份失败,请稍后重试");
            ValidateUtils.isEquals(respDTO.getStatus(), 0, HeilCode.E_400, respDTO.getMsg());
            List<SyDictionaryReg> dictionaryRegList = respDTO.getData();
            ValidateUtils.notEmpty(dictionaryRegList, HeilCode.E_400, "获取户籍省份为空");
            List<ProvinceParam> provinceParams = new ArrayList<>();
            dictionaryRegList.stream().forEach(entity -> {
                ProvinceParam provinceParam = new ProvinceParam();
                provinceParam.setProvinceCode(entity.getRegCode());
                provinceParam.setProvinceName(entity.getRegName());
                provinceParams.add(provinceParam);
                provinceParam = null;
            });
            return provinceParams;
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            sendMsg("获取省份数据", url, respDTO,e);
            throw new AlertException(HeilCode.E_500, e.getMessage());
        }
    }

    /**
     * 根据省份code获取城市
     * @param provinceCode
     * @return
     */
    public List<CityParam> getCities(String provinceCode) {
        String url = carStaffUrl + ClientUrlApiEnum.getCityApi.getName() + "/" + provinceCode;
        RespDTO<List<SyDictionaryReg>> respDTO = null;
        try {
            logger.info("{} - 获取户籍城市,省份provinceCode:{},url={}", LOG_TITLE, provinceCode,url);
            ValidateUtils.notEmpty(provinceCode, HeilCode.E_400, "省份code不能为空");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("provinceCode", provinceCode);
            String response = HttpUtils.doPost(url, paramMap, "utf-8");
            logger.info("{} - 获取省份对应城市结果->:{}", LOG_TITLE, JSON.toJSON(response));
            ValidateUtils.notEmpty(response, HeilCode.E_400, "获取城市失败,请稍后重试");
            respDTO = JSONObject.parseObject(response, new TypeReference<RespDTO<List<SyDictionaryReg>>>() {
            });
            ValidateUtils.notNull(respDTO, HeilCode.E_400, "获取城市失败,请稍后重试");
            ValidateUtils.isEquals(respDTO.getStatus(), 0, HeilCode.E_400, respDTO.getMsg());
            List<SyDictionaryReg> dictionaryRegList = respDTO.getData();
            ValidateUtils.notEmpty(dictionaryRegList, HeilCode.E_400, "获取城市为空");
            List<CityParam> cities = new ArrayList<>();
            dictionaryRegList.stream().forEach(entity -> {
                CityParam cityParam = new CityParam();
                cityParam.setCityCode(entity.getRegCode());
                cityParam.setCityName(entity.getRegName());
                cities.add(cityParam);
            });
            return cities;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            sendMsg("获取城市数据", url, respDTO,e);
            throw new AlertException(HeilCode.E_500, e.getMessage());
        }

    }

    private void sendMsg(String text,String url,Object object,Exception e) {
        MarkdownDingRobotReq robotReq = new MarkdownDingRobotReq();
        robotReq.setTitle(MessageFormat.format("调用{0}失败",LOG_TITLE));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("#### 调用获取户籍省份城市接口失败 \n >")
                .append("#### 项目工程:").append(serviceName).append("\n")
                .append("#### Env:").append(enviroment).append("\n")
                .append(text)
                .append("url->")
                .append(url)
                .append("<br /> ")
                .append("响应->")
                .append(JSON.toJSONString(object))
                .append("<br /> >")
                .append("exceptionMsg->")
                .append(e.getMessage());
        robotReq.setText(stringBuffer.toString());

        dingRobotService.sendMarkdown(DingAuth.TOKEN, robotReq);
    }
}
