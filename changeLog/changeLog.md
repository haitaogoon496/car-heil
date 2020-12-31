[TOC]
### 2019.04.09
https://tower.im/teams/593927/todos/15619/
+ 涉及数据字典、经销商门店、经销商单位等统一从car-transfer获取。
+ 前端涉及数据字典调用transfer-web接口。
+ 废弃本工程相关代码。

### 2019.03.14
https://tower.im/teams/593927/todos/15147/
+ 前端开发路由，运维nginx反向代理配置。
+ 登录后支持渠道和直销切换。

### 2019.02.18
https://tower.im/teams/593927/todos/14151/
+ “门店批量关联”改名为“批量关联设置”
+ 支持以产品维度配置规则
+ 前端选项卡调整，两个选项卡【门店关联设置】，【产品关联设置】，默认【门店关联设置】，当选中产品关联设置时，下面【车贷产品管理】隐藏。
+ POST请求增加chooseType(1-门店批量关联、2-产品批量关联)字段。

### 2019.02.14
https://tower.im/teams/593927/todos/13939/
+ 盗抢险、保险、贴息费用项新建规则时，车龄默认值为999，里程默认值为999999；
+ 门店关联管理（车贷产品管理）——配置费用规则（费用设置）——盗抢险规则，显示金额和返佣；
+ 车贷产品管理，放开【编辑】控制，不用根据产品状态了。
+ 费用规则名称与标签两行，即：名称后换行再显示标签**

### 2019.01.24
https://tower.im/teams/593927/todos/13199/
+ 1、平台费取消线下收取方式
https://tower.im/teams/593927/todos/13868/
+ 1、【门店关联设置】中平台费规则中，列样式修改。
+ 2、菜单【资金方规则准入】修改成【准入规则设置】。
+ 3、GPS费用规则保存 还款期限一个bug修改。

### 2019.01.10
https://tower.im/teams/593927/todos/13410/
金融产品规则项标签功能优化
+ 1、mapper层封装标签List<String>，在xml层迭代，通过使用find_in_set函数 使用or 逻辑判断。
+ 2、涉及功能菜单：
+ 2.1、第二、三保险费、账号管理费、续保押金、盗抢险、车辆保险、车辆贴息 增加按多个标签查询条件。
+ 2.2、其他相关菜单支持按多个标签查询条件。
+ 2.3、门店关联管理、车贷产品管理=》费用设置 相关查询页面 支持多个。

### 2019.01.08
https://tower.im/teams/593927/todos/13198/
公式编码关联
+ 1、公式编码关联。
+ 2、查询列表通过pd_calc_formula_params，以formula_code维度查询，vo中，返回params集合，前端以tag展示。
+ 3、去掉菜单【灰度控制总开关】，【灰度控制策略】菜单移到【计算引擎中心】第一位。

### 2018.12.21
tower:https://tower.im/teams/593927/todos/12384/
后置规则支持平台配置化
+ 1、涉及到的表包括：ca_postposition_rule、ca_postposition_rule_detail。
+ 2、新增菜单【后置规则配置】，UI跟前置规则保持一致即可。@王聪慧 
+ 3、信审对接平台化配置，以拆分另外一个任务，金融产品配置化先上线。

### 2018.12.13
tower:https://tower.im/teams/593927/todos/12742/
全局参数管理
+ 1、新增或编辑，去掉【状态】数据项，增加【参数类型】，下拉框。
枚举：
```
com.lyqc.product.enums.ConstEnum.ParamTypeEnum
SWITCH(1,"开关"),JSON(2,"JSON"),DECIMAL(3,"数值"),STRING(4,"字符"),
```
+ 2、新增时，状态默认启用。
+ 3、参数类型包含三种：开关、JSON，数值，字符串，
（1）、开关时，参数值默认就是1，该文本域不可用只读状态。
（2)、JSON时，参数值显示为JSON，参数值需要验证JSON格式。
（3）、数值类，包含小数的数字，前端正则控制。
（4）、字符串，随便输入。
+ 4、查询列表，新增【参数类型】查询项，新增栏位，操作中，增加，对于参数类型是
开关类，【开启】或【关闭】，操作会更新相应值，JSON如下，后台按照id更新相应值即可。
+ 5、支持标签设置，以及标签查询。
```
{id:11,paramValue:1}
```

### 2018.12.06
tower:https://tower.im/teams/593927/todos/12513/
优化前置规则属性配置模块，降低配置错误率。
优化车贷产品管理新增产品，屏蔽无需关注字段。
+ 1、归属名称 ==》规则类型。@王聪慧 
+ 2、去掉 新属性栏位。@王聪慧 
+ 3、lyqc-seig相关使用枚举统一更换成lyqc-base   AutoApprConstant类中的，枚举。@赵欣 
+ 4、car-heil相关使用枚举统一更换成lyqc-base   AutoApprConstant类中的，枚举。@尚凌宇 
+ 5、前置规则表统一更新列备注信息。@赵欣 
+ 6、car-heil 属性配置新增、修改时，propValue与propValueBak保持一致；其中classify=1。@尚凌宇 
+ 7、前置规则查询时，默认查询classify=1，通过Form类传过去。@尚凌宇 
+ 8、属性编码选择时，如果有属性类型，则属性类型只读，不可编辑。@王聪慧 
+ 9、属性类型选择时，控制操作符的联动范围。@王聪慧 
(1)、字符串时：只能为 in、not in、=、<>。
(2)、整型或浮点型： in、not in、>、>=、<、<=、<>、=。
+ 10、前置规则查询列表，增加查询参数 classify=1 @王聪慧 @尚凌宇 
+ 11、页面去掉 产品编码、产品年份。产品编码由系统自动生成（生成规则：Z/Q+分类编码+系统日期yyMMdd+3位自增），产品年份即当前年份。@王聪慧 @尚凌宇 
+ 12、产品描述，非必填。@王聪慧 @尚凌宇 

### 2018.12.06
tower:https://tower.im/teams/593927/todos/11960/
+ 1、车辆保险和车辆贴息支持关联配置产品和门店

### 2018.11.29
tower:https://tower.im/teams/593927/todos/11952/
数据字典功能模块优化
+ 1、增加设置标签功能，以及根据标签查询数据。@王聪慧 
+ 2、增加设置标签功能，以及根据标签查询数据。@李海涛 
+ 3、如果查询记录的conArgType=0，则显示【查看子项】@王聪慧 @李海涛  
+ 4、【查看子项】打开弹出窗口，显示数据跟查询列表一致即可。@王聪慧 @李海涛  

### 2018.11.29
tower:https://tower.im/teams/593927/todos/11971/
+ 1、相关页面去掉【是否提供房产资料字段】，car-heil并去掉该字段。

### 2018.11.22
tower:https://tower.im/teams/593927/todos/11740/
针对前置规则管理模块，为了进一步严格约束并控制非法输入，做技术优化。
+ 一、属性类型为【整型】时
(1)、操作符为比较运算时包括(>、>=、<、<=、=、<>)时，属性值及新属性值必须为整数。
(2)、操作符为范围运算包括(in、not in)时，支持以,分割的整数。
^[1-9]\\d*|0$
+ 二、属性类型为【浮点型】时
(1)、操作符为比较运算时包括(>、>=、<、<=、=、<>)时，属性值及新属性值必须为支持小数。
(2)、操作符为范围运算包括(in、not in)时，支持以,分割的小数。
^([+-]?)\\d*\\.\\d+$

### 2018.11.22
tower:https://tower.im/teams/593927/todos/11762/
+ 1、车贷产品管理>组件配置>平台费规则>配置关联 增加相关查询条件：
+ 2、融费率、返佣费率、贷款期限、是否二手车、车类。
+ 3、查询列表保持跟【平台费规则配置】表格一样。
+ 4、门店关联设置，支持车辆保险、车辆贴息、盗抢险 查看详情。
+ 5、其中盗抢险选项卡，还款期限栏位，宽度设小一些。

### 2018.11.06
tower:https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/7d0f73e925ab47e8b13f79a5f7434784/
+ 1、新增盗抢险规则模块。
+ 2、【车贷产品管理】组件配置支持该规则关联设置。
+ 3、【门店关联管理】支持该规则关联设置。

### 2018.11.06
tower:https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/2a702854cc744d408232e503f661c83e/
+ 1、车辆保险规则和车辆贴息规则独立出管理模块。
+ 2、【车贷产品管理】组件配置支持车辆保险规则和车辆贴息规则。
+ 3、【门店关联管理】支持配置车辆保险规则和车辆贴息规则。


### 2018.11.06
tower:https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/d417c1ba66c1420e927faeb9a4129380/
+ 1、利率规则增加“资方成本利率” @王聪慧 @李海涛 。
+ 2、门店关联设置 和 车贷产品管理，组件配置，新增该栏位 @王聪慧 @李海涛 。

### 2018.10.15
tower:https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/3cc8ab276bf9402a9f4b1ba4cd7bfd4e/
前置规则配置模块相关优化
+ 1、前置规则新增或修改，分类由数据字典选择，而非填写，规则ID由redis生成。
+ 2、前端相关文案统一修改，二级类型==>分类；规则ID==>规则编号
+ 3、分类编码，通过枚举dictionary/options/68 下拉选择，分类编码：code；分类名称：name；分类名称，是根据分类编码联动选择，只读。

### 2018.10.15
```sql
update pd_fee_rule set is_all_product =0 where is_all_product is null;
update pd_fee_rule set is_all_dealer =0 where is_all_dealer is null;

-- 调整字段约束
alter table pd_fee_rule modify column is_all_dealer tinyint(4) not null default 0 comment '是否适用所有经销商(1:是 0:否)',
modify column is_all_product tinyint(4) not null default 0 comment '是否适用所有产品(1:是 0:否)',
modify column classify tinyint(4) not null comment '分类 1-人身保险费、2-续保押金、3-车辆保险、4-车辆贴息';

-- 新增字段（保险 和 贴息）
alter table pd_fee_rule 
add column sale_price_min decimal(20,0) not null default 0 comment '实际销售价下限',
add column sale_price_max decimal(20,0) not null default 10000000 comment '实际销售价上限',
add column car_loan_amount_min decimal(20,0) not null default 0 comment '车辆贷款金额下限',
add column car_loan_amount_max decimal(20,0) not null default 10000000 comment '车辆贷款金额上限',
add column payment_scale_min decimal(5,0) not null default 0 comment '首付比下限',
add column payment_scale_max decimal(5,0) not null default 100 comment '首付比上限',
add column vehicle_miles_min decimal(8,0) not null default 0 comment '二手车里程(KM)下限',
add column vehicle_miles_max decimal(8,0) not null default 150000 comment '二手车里程(KM)上限',
add column vehicle_age_min decimal(5,0) not null default 0 comment '车龄(月)下限',
add column vehicle_age_max decimal(5,0) not null default 120 comment '车龄(月)上限',
add column is_old varchar(8) not null default '0,1' comment '是否二手车(0-新车、1-二手车)',
add column is_lcv varchar(8) not null default '0,1,2,3' comment '车类(0-乘用车、1-LCV、2-MMPV、3-商用车)',
add column extend_props varchar(512) default null comment '扩展字段(结构化数据，如配置公式，JSON字符串格式类型)';
```
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/2a702854cc744d408232e503f661c83e/
#### 功能特性
+ 【延保费配置】模块隐藏。
+ 相关费用规则管理所属门店，查询已配置门店数据，展示【当前已选中的门店数量为xx】。
+ 菜单“门店关联设置”改为“门店批量关联”。
+ 利率规则编辑界面，利率和高风险利率的文本框放在同一行。
+ 门店关联管理-配置费用规则-利率规则-显示利率值（新增栏位：基础利率/高风险利率，新增查询条件：基础利率、高风险利率）。
+ 门店关联管理-配置费用规则-平台费规则-显示 （增加栏位：基础费率，高风险费率）。
+ 标签管理：查询条件：业务类型，关联ID；查询列表：主键ID、业务类型，关联ID、操作 【修改】。
+ 已下线经销商门店元数据检查：数据过滤（无放款前申请单且有关联费用规则）统计维度，经销商门店。支持按照费用规则类型批量删除。
+ 车辆保险规则 和 车辆贴息规则 独立出新单元模块。
+ 车贷产品管理=》组件配置=》平台费规则配置优化。
#### BUG修复
+ 前置规则，默认每页显示30条，实际并未显示30条


### 2018.09.29
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/e0c3c21f63cc4db3bdd8ae30720170d0/
#### 功能特性
+ 元数据健康检查V1.0版本功能开发。

### 2018.09.21
【tower】：https://tower.im/projects/ef39a8f0a7ae4ff48d71f40b6de86bdf/todos/ec22f66849214ceba11f6e095c08457c/
#### 功能特性
+ 资金方准入规则配置新增 征信方式字段。
+ 针对车贷帝新增数据字典，卖方类型（公司，个人）两种类型。

### 2018.09.18
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/2ecbe5ed3112497391cf0c7b11f1e7a7/
#### 功能特性
+ 数据字典，查询列表，默认选中“参数类型”。
+ 前置规则，增加参数列表，及操作说明。

### 2018.09.12
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/b40eefdaae6a456a88a3b8fe820d75f9/
#### 功能特性
+ 资金方管理添加编辑时，资金方代码通过枚举下拉选择，摒弃随便命名，导致无效无法使用。
+ 资金方准入规则 添加时，规则编码 由系统生成，生成规则：资金方代码+年月日+增长种子（每个资金方都有增长种子，增长种子四位，通过Redis生成），比如：WZ201809120001。


### 2018.09.11
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/6e505912bdb543e8b2777f1b73b76d6a/
#### 功能特性
+ 前置规则，属性code新增 expression类型，当选择expression时，操作符为表达式；属性类型为=。
+ 保存时，需要先计算，以验证公式是否正确。
+ lyqc-seig相关方法，添加对expression处理，通过car-common封装Aviator，提供AviatorExecutor.execute一系列静态方法。
+ car-common扩展对aviator函数，添加round、ceil、floor、max、min、sum、in、notin、nvl函数.



### 2018.08.28
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/4a6d817a8c65482483f411425beeb8dd/
#### 功能特性
+ 应产品运营刘骁欢要求，为支持gps_count作为前置规则条件需要扩展程序支持。


### 2018.08.21
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/e0f0b44848db4d0ea9cbd827da377218/
#### 功能特性
+ 功能特性菜单【门店关联管理】，支持以经销商运营门店维度配置管理GPS规则、利率规则、平台费规则。
+ 门店列表，支持透传费用规则，以 已关联 和 未关联 两种维度管理，支持 保存关联和取消关联，查询栏位与查询条件与相关费用规则保持一致即可。


### 2018.08.13
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/72f2166a9972401f9110145779205cdc/
#### 功能特性
+ 查询列表优化，涉及：计算引擎中心 下属三个菜单，以及 灰度控制策略、全局参数配置、前置规则配置、产品分类管理、数据字典管理 
+ 查询条件去掉 label，输入框增加placeHolder，分页默认显示30条，支持30/60/90。


### 2018.08.07
【tower】：https://tower.im/projects/ef39a8f0a7ae4ff48d71f40b6de86bdf/todos/1ff001c1846646b5bd8e910c74e2f4a5/
#### 功能特性
+ 车贷产品管理，组件配置支持【GPS规则】、【利率规则】配置关联。

### 2018.08.01
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/3a55df486e094e418dda7598a4005a5c/
#### 功能特性
+ 产品暂停时，支持 组件 和 规则（保险、贴息）编辑、删除 操作；产品支持标签设置功能。
+ 相关页面以弹出窗口页面展示，以告别原来返回上一个页面时，参数丢失问题（从用户角度又需要重新输入条件查询）。
+ 配置保险和贴息规则时，相关规则条件输入框新增默认值。


### 2018.07.31
【tower】：
#### 功能特性
+ 资金方功能管理，以及配置所属产品。
+ 资金方准入规则配置。

### 2018.07.25
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/b73de106787f457a95733084b2251ee4/
#### 功能特性
+ 利率规则、gps规则、平台费规则、第二/三年延保费、账户管理费、延保费 规则模块 的 新增、详情，编辑，统一 调整  为【 车辆贷款金额】。
(1)、查询列表：【贷款金额区间】调整为【 车辆贷款金额区间】
(2)、新增、编辑、详情 统一 调整为 【车辆贷款金额最小值】、【车辆贷款金额最大值】。
+【车贷产品管理】“新增”默认值，费用进位方式 ===》向上取整，费用进位精度===》精确到百位。
+ 相关费用规则页面，【是否提供房产】  新增时，默认全部选中；
+ 是否适用所有经销商，默认“否”；去除生效日期，失效日期；
+ 平台费规则配置，金额区间与优先级中添加“首付比”，放在【贷款金额】 栏位 之后。


### 2018.07.19
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/ddb38b1c31074e368aa8dcadc76acd10/
#### 功能特性
+ 功能特性菜单【门店关联设置】，支持 车贷产品，GPS、利率、平台费批量新增关联和取消关联经销商门店。


### 2018.06.20
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/e72295cffaed46948bbd2585e60e5b95/
#### 功能特性
+ 【车类】，默认全选中（必填项）。
+ 【贷款最小金额 ~ 贷款最大金额 0~10000000
+ 【最小首付比例~最大首付比例 0~100%
+ 【贷款期限】，默认全选中。
+ 【是否提供房产材料】修改成多选框，是、否，默认全选中（必填项）。特殊修改
+ GPS规则：GPS属性，选中时，联动带入【GPS数量】（只读）的值。
+ 所有页面，默认分页大小为30条。


### 2018.06.20
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/769d3bad376b49f483e0bb3ddd1e0b21/
#### 功能特性
+ 去掉菜单【车贷产品启用、暂停、撤销】，其中产品新增后，默认状态为【暂停】状态。
+ 【车贷产品管理】操作栏中，功能特性按钮【 启用】【暂停】，产品状态为暂停时，显示 启用；否则，显示暂停。
+ 车贷产品要求中，关于默认值如下：其中，【收入比】默认：0~100%；【是否二手车】：默认全选中；【车类】：默认全选中。
+ 所有页面，默认分页大小为30条。

### 2018.06.19
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/83dd80a0ad31457781f4ed3260c523a6/
#### 功能特性
+ 调整相关费用规则查询列表页面优化

### 2018.06.12
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/99cebfbd25ea4eb4867631078ab73468/
#### 功能特性
+ 车贷产品-》组件配置-》平台费规则配置 功能体验优化。
+ 支持通过两种维度【已关联】和【未关联】两种维度批量配置管理，并支持【标签】功能快速检索。

### 2018.06.08
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/e9fb2f90593b40a190fc8a7a21c39441/
#### 功能特性
+ GPS、利率、平台费 三个规则页面，支持批量设置规则添加标签。
+ GPS、利率、平台费 三个规则页面，支持按照每条记录编辑标签。
+ GPS、利率、平台费 三个规则页面，查询列表页面，显示设置的标签并支持检索查询。

### 2018.06.08
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/70202b5c56ac45508719d1ecabf04aa8/
#### 功能特性
+ 产品分类管理、查询条件新增”分类id“，另外查询条件，去掉“产品分类”前缀。
+ 产品分类管理，查询列表，新增栏位列“分类id”。
+ 车贷产品管理，新增查询条件“产品分类”，下拉框，支持搜索查询。
+ 车贷产品管理，查询列表，新增栏位列“产品分类”。

### 2018.06.05
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/f3a77d502828408f8a8f5491216459ec/
#### 功能特性
+ 平台费规则配置，融费率支持输入框，摒弃之前多选框体验。
+ 平台费规则配置，优化费率输入框体验。


### 2018.06.05
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/bdd4d6ef9b2b4798b1f9dc60a9691ceb/
#### 功能特性
+ 车贷产品管理，管理所属门店配置页面大优化（支持更多条件筛选，如：大区、省份、城市等维度），由原先列表显示展示未多选框，支持批量设置。

### 2018.05.18
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/5820fff1c66146199eeb6f70196f9209/
#### 功能特性
+ 车贷产品管理=》组件管理=》平台费规则配置保存bug修复。

### 2018.05.18
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/98b491181e20470eb7e44fe7c571468e/
#### 功能特性
+ 人身保险费规则，费率精确小数点后两位，保存添加校验。

### 2018.05.18
【tower】：https://tower.im/projects/9e42f70906304949aff0707b92298a42/todos/73ff6aa16c454fdca91d9f9257365a67/
#### 功能特性
+ 新增功能计算引擎中心（公式参数配置、计算公式配置、灰度控制策略、计算链路分析等）

### 2018.05.08
【tower】：https://tower.im/projects/2f017376923c48018c7c3924ac79a764/todos/1e5b86daf1e746c5be53ab50734a500d/
#### 功能特性
+ 新增前置规则管理配置

### 2018.04.18
【tower】：https://tower.im/projects/f988a4fbf2164c599c91b7aefae7d28c/todos/6603c7fc1b6341df8b689cfece4a7e33/
#### 功能特性
+ 新增产品分类管理功能

### 2018.03.27
【tower】：
#### 功能特性
+ 金融产品管理平台初版上线