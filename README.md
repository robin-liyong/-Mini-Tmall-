# 迷你天猫商城
+ **问题交流群：763819871**
+ **前台演示地址（服务器2核2G内存，请温和测试 ^ ^，如果打不开，则正在维护中）：<http://47.112.23.129:8005/tmall/>**

### 介绍
迷你天猫商城是一个基于Spring Boot的综合性B2C电商平台，需求设计主要参考天猫商城的购物流程：用户从注册开始，到完成登录，浏览商品，加入购物车，进行下单，确认收货，评价等一系列操作。
作为迷你天猫商城的核心组成部分之一，天猫数据管理后台包含商品管理，订单管理，类别管理，用户管理和交易额统计等模块，实现了对整个商城的一站式管理和维护。

所有页面均兼容IE10及以上现代浏览器。

### 部署方式
1. 项目使用IntelliJ IDEA开发，请使用IntelliJ IDEA的版本控制检出功能，输入“<https://gitee.com/project_team/Tmall_demo.git>”拉取项目即可。
2. 项目数据库为MySQL 5.7版本，请在码云附件上下载SQL文件并导入到数据库中。
3. 使用IDEA打开项目后，在maven面板刷新项目，下载依赖包。
4. 配置数据库连接并启动SpringBootApplication即可。

### 项目默认运行地址
+ 前台地址：<http://localhost:8080/tmall>
+ 后台地址：<http://localhost:8080/tmall/admin>

### 注意事项：
1. 后台管理界面的订单图表没有数据为正常现象，该图表显示的为近7天的交易额。
2. 该项目同时兼容eclipse，但如有自行扩展代码的意愿，建议使用IDEA。
3. 该项目是我们几个学生在校合作完成的一个练习项目，目的是让编程初学者和应届毕业生可以参考一下用较少的代码实现一个完整MVC模式，Spring Boot体系的电商项目，相关领域大神们可以给我们建议，让我们做得更好。

### 项目界面
+ ##### 后台界面(部分)
![主页](https://images.gitee.com/uploads/images/2019/0720/132736_629d409d_1616166.png "主页.png")
![所有产品](https://images.gitee.com/uploads/images/2019/0720/132752_a9065bdc_1616166.png "所有产品.png")
![产品详情](https://images.gitee.com/uploads/images/2019/0720/132804_07364d8e_1616166.png "产品详情.png")
![产品分类](https://images.gitee.com/uploads/images/2019/0720/132815_4fa23e1c_1616166.png "产品分类.png")
![分类详情](https://images.gitee.com/uploads/images/2019/0720/132824_0392314c_1616166.png "分类详情.png")
![用户管理](https://images.gitee.com/uploads/images/2019/0720/132840_582530ca_1616166.png "用户管理.png")
![用户详情](https://images.gitee.com/uploads/images/2019/0720/132849_481238d6_1616166.png "用户详情.png")
![订单列表](https://images.gitee.com/uploads/images/2019/0720/132912_190142c1_1616166.png "订单详情.png")
![订单详情](https://images.gitee.com/uploads/images/2019/0720/132926_0393d549_1616166.png "订单详情2.png")
![我的账户](https://images.gitee.com/uploads/images/2019/0720/132934_e0132cc9_1616166.png "我的账户.png")
+ ##### 前台界面(部分)---
![登陆界面](https://gitee.com/uploads/images/2018/0526/223030_17b28619_1616166.png "2018-05-26_221715.png")
![首页](https://gitee.com/uploads/images/2018/0526/223018_14e999f1_1616166.png "2018-05-26_221703.png")
![产品详情](https://gitee.com/uploads/images/2018/0526/223044_e481ec5f_1616166.png "2018-05-26_221725.png")
![下单界面](https://gitee.com/uploads/images/2018/0526/223100_ef6e9612_1616166.png "2018-05-26_221837.png")
![订单列表](https://gitee.com/uploads/images/2018/0526/223117_dfd64b43_1616166.png "2018-05-26_221901.png")
![确认收货](https://gitee.com/uploads/images/2018/0526/223220_71e2ee3d_1616166.png "2018-05-26_221911.png")
![产品列表](https://gitee.com/uploads/images/2018/0526/223233_18e131a5_1616166.png "2018-05-26_222006.png")
![购物车](https://gitee.com/uploads/images/2018/0526/223245_3f80d8f4_1616166.png "2018-05-26_223157.png")