package com.mljr.heil.document;

import com.lyqc.base.enums.UserOperateLogConstant;
import lombok.Data;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @description: 用户操作记录
 * @Date : 2019/3/22 下午3:55
 * @Author : 石冬冬-Seig Heil
 */
@Document(collection = "userLog")
@CompoundIndexes({
        @CompoundIndex(def = "{'authModel':1}")
})
@Data
public class UserLogDoc extends BaseDocument{

    public UserLogDoc() {
        super();
        super.setCollectionName("userLog");
        super.setIdPrefix("UL");
    }

    /**
     * 主键id
     */
    private Long flowId;
    /**
     * 操作人
     */
    private String userName;
    /**
     * 操作人
     */
    private Integer userId;
    /**
     * 业务类型
     * {@link UserOperateLogConstant.AuthModelEnumForProduct#getName()}
     */
    private Integer authModel;
    /**
     * 操作日期
     */
    private String authDate;
    /**
     * 操作类型
     * {@link UserOperateLogConstant.AuthTypeEnum#getName()}
     */
    private String authType;
    /**
     * 日志详细
     */
    private String authDetail;
}
