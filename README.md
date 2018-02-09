#HYDRA-SSO单点登录系统
##组织结构<BR>
      ├── smart-sso -- 单点登录权限系统
      ├───── smart-sso-client -- 客户端依赖包，提供登录认证、授权管理
      ├───── smart-sso-demo -- 简单的demo示例
      ├───── smart-sso-server -- 服务端
##技术<br>JDK：1.8（支持1.6+）
        数据库：Mysql
        项目构建工具：Maven 3.3.3
        MVC框架：SpringMVC 4.2.1.RELEASE
        核心框架：Spring 4.2.1.RELEASE
        ORM框架：MyBatis 3.3.0
        分布式协调服务：Zookeeper 3.4.7
        分布式RPC服务：Dubbo 2.5.3（默认Hessian 4.0.38）
        分布式缓存服务：Redis 2.8.12
        分布式消息服务：ActiveMQ 5.13.3
        NIO框架：Netty 4.0.23.Final
        JSON工具：Fastjson 1.2.29
        定时任务：Quartz 2.2.1
        数据库连接池：Druid 1.0.15
        日志管理：SLF4J 1.7.21、Logback 1.1.7
        模板引擎：Freemarker 2.3.23
        单点登录：极简基于Cookie实现
##请求协议:
        默认http，可选配为Https，减少配置，降低门槛
##配置优选:
        将serverName定义在properties文件中，通过Spring的<context:property-placeholder />标签注入，简化配置。(毕竟当今大部分Java项目都会用到Spring框架)
##耦合度:
        将权限集中管理在权限系统，需要的时候配置Filter，几乎无耦合
##权限变动服务重启:
        权限的修改通过MQ广播（可选配），服务无需重启。
##分布式部署:
        通过大家更为常用的Spring，覆盖HttpSession注入RedisSession。
##架构图
