# spring-boot-film后端代码
请在运行前启动redis和rabbitMQ,并在application.yml中更改配置
在服务启动后可以通过 http://localhost:8080/swagger-ui/index.html 查看和测试接口<br>
另外部分接口需要token,请在Header的Authorization参数中填入Bearer +token进行测试<br>
### 项目结构树
```
.
├── main
│   ├── java
│   │   └── cn
│   │       └── scnu
│   │           └── edu
│   │               ├── config//配置包
│   │               │   ├── RabbitConfiguration.java
│   │               │   ├── SecurityConfiguration.java
│   │               │   ├── SwaggerConfiguration.java
│   │               │   └── WebConfiguration.java
│   │               ├── controller//控制器
│   │               │   ├── AuthorizeController.java
│   │               │   ├── exception
│   │               │   │   └── ValidationController.java
│   │               │   └── TestController.java
│   │               ├── entity//实体
│   │               │   ├── BaseData.java
│   │               │   ├── dto//后端数据
│   │               │   │   └── Account.java
│   │               │   ├── RestBean.java
│   │               │   └── vo//后端数据转化为前端视图
│   │               │       ├── request//http请求数据
│   │               │       │   ├── ConfirmResetVO.java
│   │               │       │   ├── EmailRegisterVO.java
│   │               │       │   └── EmailResetVO.java
│   │               │       └── response//http响应数据
│   │               │           └── AuthrizeVO.java
│   │               ├── filter//过滤器
│   │               │   ├── CorsFilter.java
│   │               │   ├── FlowLimitingFilter.java
│   │               │   └── JwtAuthorizeFilter.java
│   │               ├── listener//rabbitMQ监听器，消费队列消息
│   │               │   └── MailQueueListener.java
│   │               ├── mapper
│   │               │   └── AccountMapper.java
│   │               ├── service
│   │               │   ├── AccountService.java
│   │               │   └── impl
│   │               │       └── AccountServiceImpl.java
│   │               ├── SpringBootFilmBackendApplication.java
│   │               └── utils//工具包
│   │                   ├── Const.java
│   │                   ├── FlowUtils.java
│   │                   └── JwtUtils.java
│   └── resources
│       └── application.yml
└── test//测试包
    └── java
        └── cn
            └── scnu
                └── edu
                    └── SpringBootFilmBackendApplicationTests.java


```