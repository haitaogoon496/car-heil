package com.mljr.heil.controller.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.lyqc.transfer.dto.DealerDTO;
import com.lyqc.transfer.re.DealerRe;
import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.component.DealerComponent;
import com.mljr.heil.vo.common.SyDealerVo;
import com.mljr.heil.voconvertor.common.DealerReVoConvertor;
import com.mljr.util.CollectionsTools;
import com.mljr.util.StringTools;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 运营门店管理
 * @Date : 2018/3/21$ 17:08$
 * @Author : liht
 */
@Api(description = "【运营门店管理】相关api", tags = "运营门店管理")
@RestController
@RequestMapping("/syDealer")
public class SyDealerController {

    @Autowired
    private DealerComponent dealerComponent;

    /**
     * 分页加载数据
     * @param form
     * @return Result<PageVO<SyDealerVo>>
     */
    @PostMapping("/loadRecords")
    public Result<PageVO<SyDealerVo>> loadRecords(@RequestBody PageForm<DealerQueryForm> form){
        // 配合前端现有出参 & 入参
        String jsonStr = JSONObject.toJSONString(form);
        PageForm<DealerDTO> dto = JSONObject.parseObject(jsonStr, new TypeReference<PageForm<DealerDTO>>() {
        });
        if(form != null && form.getForm() != null && dto != null && dto.getForm() != null) {
            dto.getForm().setDealerCode(StringTools.isNotEmpty(form.getForm().getCode()) ? Integer.valueOf(form.getForm().getCode()) : null);
            dto.getForm().setDealerName(form.getForm().getName());
        }
        Result<PageVO<DealerRe>> pageVOResult = dealerComponent.loadRecords(dto);
        if(pageVOResult != null && pageVOResult.isSuccess() && pageVOResult.getData() != null) {
            return Result.suc(new PageVO<>(form.getDraw(), pageVOResult.getData().getRecordsTotal(), new DealerReVoConvertor().convertList(pageVOResult.getData().getData() != null ? pageVOResult.getData().getData() : CollectionsTools.emptyList())));
        }
        return Result.failInEmptyRecord(null);
    }
}
