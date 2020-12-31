package com.mljr.heil.component;

import com.mljr.heil.base.form.DealerQueryForm;
import com.mljr.heil.service.common.SyDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 相关费用规则配置管理门店组件
 * @Date : 下午6:20 2018/2/7
 * @Author : 石冬冬-Heil Hitler(dongdong.shi@mljr.com)
 */
@Component
public class ApplyDealerComponent {

    @Autowired
    private SyDealerService syDealerService;

    /**
     * 批量删除
     * 业务场景：根据ruleId、dealerScopes、tableName 处理
     * @param form
     * @return
     */
    public int batchDelete(DealerQueryForm form){
        return syDealerService.batchDelete(form);
    }
}
