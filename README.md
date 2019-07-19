# 迷你天猫商城
**问题交流群：763819871**
**前台演示地址（服务器2核2G内存，请温和测试 ^ ^）：<http://47.112.23.129:8005/tmall/>**

### 介绍
迷你天猫商城是一个基于SSM框架的综合性B2C电商平台，需求设计主要参考天猫商城的购物流程：用户从注册开始，到完成登录，浏览商品，加入购物车，进行下单，确认收货，评价等一系列操作。
作为模拟天猫商城系统的核心组成部分之一，采用SSM框架的天猫数据管理后台包含商品管理，订单管理，类别管理，用户管理和交易额统计等模块，实现了对整个商城的一站式管理和维护。

所有页面均兼容IE10及以上现代浏览器。

### 开发/部署方式
1. 项目使用IntelliJ IDEA开发，请使用IntelliJ IDEA的版本控制检出功能，输入“<https://gitee.com/project_team/Tmall_demo.git>”拉取项目即可。
2. 项目数据库为MySQL 5.7版本，请在码云附件上下载SQL文件并导入到数据库中。
3. 使用IDEA打开项目后，在maven面板刷新项目，下载依赖包。
4. 在IDEA中配置tomcat服务器，关于评论中反馈的404问题，请确认idea中服务配置的地址如下图，配置完毕后即可启动服务
![IDEA项目配置](https://images.gitee.com/uploads/images/2019/0711/130625_a491485f_1616166.png "TIM图片20190711130526.png")

### Tomcat直接部署方式
+ 链接: <https://pan.baidu.com/s/1bzzaYdHC43Mo6f3fvMhJ-g> 提取码: xr2a

1. 在上述地址中下载项目war包，并放入tomcat8.0及以上版本的webapps文件夹中。
2. 项目数据库为MySQL 5.7版本，请在码云附件上下载SQL文件并导入到数据库中。
3. 使用winrar等工具打开war包，将WEB-INF/classes中的jdbc.properties修改为你的数据库信息。
4. 启动项目，使用浏览器打开下列地址。

### 项目运行地址
+ 前台地址：<http://localhost:端口/tmall>
+ 后台地址：<http://localhost:端口/tmall/admin>

### 注意事项：
1. 后台管理界面的订单图表没有数据为正常现象，该图表显示的为近7天的交易额。
2. 该项目同时兼容eclipse，但如有自行扩展代码的意愿，建议使用IDEA。
3. 该项目是我们几个学生在校合作完成的一个练习项目，目的是让编程初学者和应届毕业生可以参考一下用较少的代码实现一个完整MVC模式，SSM框架体系的电商项目，相关领域大神们可以给我们建议，让我们做得更好。

### 项目界面
+ ##### 后台界面(部分)
![登录界面](https://gitee.com/uploads/images/2018/0526/222324_71d64249_1616166.png "2018-05-26_221417.png")
![首页](https://gitee.com/uploads/images/2018/0526/222349_00d5df29_1616166.png "2018-05-26_221445.png")
![产品列表](https://gitee.com/uploads/images/2018/0526/222414_c3a74f51_1616166.png "2018-05-26_221454.png")
![添加产品](https://gitee.com/uploads/images/2018/0526/222440_813cf8d7_1616166.png "2018-05-26_221504.png")
![产品详情](https://gitee.com/uploads/images/2018/0526/222457_7727da44_1616166.png "2018-05-26_221513.png")
![产品类别列表](https://gitee.com/uploads/images/2018/0526/222515_0f605a1a_1616166.png "2018-05-26_221522.png")
![用户列表](https://gitee.com/uploads/images/2018/0526/222531_2ddbba60_1616166.png "2018-05-26_221530.png")
![用户详情](https://images.gitee.com/uploads/images/2019/0718/170709_b7c1ca99_1616166.jpeg "222628_e539faf6_1616166.jpg")
![订单列表](https://gitee.com/uploads/images/2018/0526/222601_ac370928_1616166.png "2018-05-26_221547.png")
![订单详情](https://gitee.com/uploads/images/2018/0526/222628_e539faf6_1616166.png "2018-05-26_221554.png")
![管理员详情](https://gitee.com/uploads/images/2018/0526/222839_911d4e0d_1616166.png "2018-05-26_221607.png")

+ ##### 前台界面(部分)---
![登陆界面](https://gitee.com/uploads/images/2018/0526/223030_17b28619_1616166.png "2018-05-26_221715.png")
![首页](https://gitee.com/uploads/images/2018/0526/223018_14e999f1_1616166.png "2018-05-26_221703.png")
![产品详情](https://gitee.com/uploads/images/2018/0526/223044_e481ec5f_1616166.png "2018-05-26_221725.png")
![下单界面](https://gitee.com/uploads/images/2018/0526/223100_ef6e9612_1616166.png "2018-05-26_221837.png")
![订单列表](https://gitee.com/uploads/images/2018/0526/223117_dfd64b43_1616166.png "2018-05-26_221901.png")
![确认收货](https://gitee.com/uploads/images/2018/0526/223220_71e2ee3d_1616166.png "2018-05-26_221911.png")
![产品列表](https://gitee.com/uploads/images/2018/0526/223233_18e131a5_1616166.png "2018-05-26_222006.png")
![购物车](https://gitee.com/uploads/images/2018/0526/223245_3f80d8f4_1616166.png "2018-05-26_223157.png")