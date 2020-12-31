package com.mljr.heil.controller.common;

import com.lyqc.base.common.Result;
import com.lyqc.base.page.PageForm;
import com.lyqc.base.page.PageVO;
import com.mljr.heil.component.MongodbComponent;
import com.mljr.heil.document.UserLogDoc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: mongodb 快捷操作
 * @Date : 2019/3/28 下午1:02
 * @Author : 石冬冬-Seig Heil
 */
@RestController
@RequestMapping("/mongoQuick")
@Api(description = "【公共接口模块】相关api",tags = "公共接口模块")
public class MongoQuickController {
    @Autowired
    private MongodbComponent mongodbComponent;
    /**
     * 新增用户操作记录
     * @param userLogDoc
     * @return Result<Boolean>
     */
    @ApiOperation("新增用户操作记录")
    @PostMapping("/insertUserLog")
    public Result<Boolean> insertUserLog(@RequestBody UserLogDoc userLogDoc){
        mongodbComponent.insert(userLogDoc);
        return Result.suc(Boolean.TRUE);
    }

    /**
     * 删除用户操作记录
     * @param userLogDoc
     * @return Result<Boolean>
     */
    @ApiOperation("删除用户操作记录")
    @PostMapping("/removeUserLog")
    public Result<Boolean> removeUserLog(@RequestBody UserLogDoc userLogDoc){
        return Result.suc(mongodbComponent.remove(userLogDoc));
    }

    /**
     * 查询用户操作记录
     * @param userLogDoc
     * @return Result<Boolean>
     */
    @ApiOperation("查询用户操作记录")
    @PostMapping("/findUserLog")
    public Result<List<UserLogDoc>> findUserLog(@RequestBody UserLogDoc userLogDoc){
        return Result.suc(mongodbComponent.find(userLogDoc,UserLogDoc.class));
    }

    /**
     * 分页查询用户操作记录
     * @param pageForm
     * @return PageVO<List<UserLogDoc>>
     */
    @ApiOperation("分页查询用户操作记录")
    @PostMapping("/findUserLogByPaging")
    public PageVO<List<UserLogDoc>> findUserLogByPaging(@RequestBody PageForm<UserLogDoc> pageForm){
        return mongodbComponent.findByPaging((PageForm)pageForm,UserLogDoc.class);
    }

}
