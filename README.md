#### 简称
Jfp
====== 

#### 项目全称
java free project tools & framework
基于JAVA的开发工具框架
------- 

#### 软件架构
1. 基于Spring 3.x、MyBatis 3.x 基础框架进行二次封装，
2. 提供业务开发自动化输出工具

#### 使用说明
1. CN-Framework-Core-1100       核心实现，包括各种工具、常量和接口
2. CN-Framework-Config-1200     系统配置文件
3. CN-Biz-Common-2000           扩展功能，包括常用工具

#### 版本日志
2.6.3.1  2020/01/20 正式发布 <br>
&nbsp;&nbsp;1). 优化持久层XML<br>
&nbsp;&nbsp;2). 优化数据库管理界面工具为JSP模型自动化输出<br>
&nbsp;&nbsp;3). 命名修正，开源协议修正，同步框架版本号<br>
&nbsp;&nbsp;4). 其他相关优化，代替注册中心、配置中心<br>
2.6.2.5  2019/10/28 正式发布 <br>
&nbsp;&nbsp;1). 日志远程输出模式优化，基于Redis-DB-15<br>
&nbsp;&nbsp;2). 修正心跳、短信、邮件框架，队列模式强制使用Redis-DB-15<br>
&nbsp;&nbsp;3). 修正组件之间服务获取基于Redis-DB-15，满足中小业务系统<br>
&nbsp;&nbsp;4). 其他相关优化，代替注册中心、配置中心<br>
2.6.2.3  2019/10/12 正式发布 <br>
&nbsp;&nbsp;1). 开源协议修正为【The Clear BSD License】<br>
&nbsp;&nbsp;2). 修正版权声明<br>
&nbsp;&nbsp;3). 统一日志输出模块优化（LogDataRemoteSupport、RemoteLogConfigService）<br>
&nbsp;&nbsp;4). 参数配置基类优化，去掉TYPE字段过滤（AConfigSupport）<br>
&nbsp;&nbsp;5). 优化启动模式和模块加载<br>
&nbsp;&nbsp;6). 其他各种细节优化<br>
&nbsp;&nbsp;7). 不再兼容2.3.x以下版本<br>
2.5.2  2019/8/17 正式发布 <br>
&nbsp;&nbsp;1). 整合历史版本功能<br>
&nbsp;&nbsp;2). 优化配置（基于数据库）<br>
&nbsp;&nbsp;3). 集成Cloud，基于Http通信<br>
2.2.2  2018/11/05 正式发布 <br>
&nbsp;&nbsp;1). 基于SpringBoot<br>
1.2.3  2018/8/20 正式发布 <br>
&nbsp;&nbsp;1). 功能优化<br>
1.2.2  2018/7/28 正式发布 <br>
&nbsp;&nbsp;1). HttpServiceHelper增加JSON方式通信<br>
&nbsp;&nbsp;2). RESTResultBean增加XML输出格式<br>
&nbsp;&nbsp;3). ISSystemDataCache优化缓存集成关系<br>
&nbsp;&nbsp;&nbsp;&nbsp;a). 业务标签 org.zmsoft.jfp.common.tag<br>
&nbsp;&nbsp;&nbsp;&nbsp;b). 通用标签 org.zmsoft.jfp.framework.tag<br>
1.2.1  2018/7/18 正式发布 <br>
&nbsp;&nbsp;1). 优化爬虫框架<br>
&nbsp;&nbsp;2). 增加缓存管理器<br>
&nbsp;&nbsp;3). 优化公共标签实现<br>
&nbsp;&nbsp;&nbsp;&nbsp;a). 业务标签 org.zmsoft.jfp.common.tag<br>
&nbsp;&nbsp;&nbsp;&nbsp;b). 通用标签 org.zmsoft.jfp.framework.tag<br>
1.1.1  2018/6/25 正式发布 <br>
&nbsp;&nbsp;1). 优化代码结构<br>
&nbsp;&nbsp;2). 优化缓存模块（系统配置、参数定义、业务分类）<br>
&nbsp;&nbsp;3). 优化公共业务生产者端实现<br>
1.0.1  2018/2/8 正式发布 <br>
0.x.x  2018/2/2 原有代码整理迁移 

#### 参与贡献
1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request
