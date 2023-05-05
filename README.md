### 帮助文档

#### 一.模块简介

> 1.  starter-ichong-restrictor
      >
      >    ```
>    基于redission，接口限流starter模块
>    ```
>
> 2.  starter-ichong-jobregister
      >
      >    ```
>    基于xxl-job，自动任务自动注册到管理平台starter模块
>    ```
>
> 3.  xxl-job-admin
      >
      >    ```
>    xxl-job,自动任务管理平台
>    ```
>
> 4.  ichong-starter-testdemo
      >
      >    ```
>    接口限流starter，自动任务注册starter测试模块
>    ```
>
>

#### 二.starter-ichong-restrictor模块的使用

>1. 相关服务依赖导入
    >
    >   ```xml
>           <dependency>
>               <groupId>com.ichong</groupId>
>               <artifactId>starter-ichong-restrictor</artifactId>
>               <version>0.0.1-SNAPSHOT</version>
>           </dependency>
>   ```
>
>
>
>2. 配置文件添加配置(redission相关的配置)
    >
    >   ```yaml
>   singleServerConfig:
>     idleConnectionTimeout: 10000
>     connectTimeout: 10000
>     timeout: 3000
>     retryAttempts: 3
>     retryInterval: 1500
>     # 如果Redis服务端配置有密码需要替换password的值
>     password: null
>     subscriptionsPerConnection: 5
>     clientName: null
>     # 替换为自己真实Redis服务端连接
>     address: "redis://127.0.0.1:6379"
>     subscriptionConnectionMinimumIdleSize: 1
>     subscriptionConnectionPoolSize: 50
>     connectionMinimumIdleSize: 24
>     connectionPoolSize: 64
>     database: 0
>     dnsMonitoringInterval: 5000
>   threads: 16
>   nettyThreads: 32
>   codec: "!<org.redisson.codec.MarshallingCodec> {}"
>   transportMode: "NIO"
>   ```
>
>
>
>3.  使用方式
>
>   ```
>   在对应的接口上添加@RedissonRateLimit(key = "testLimit",timeout = 10000,count = 3,
>               interfaceName = "/hello/test",isEnable = true)注解
>   该注解拥有五个参数
>   key:           限流时存放在redis中key值
>   timeout:       限流的时间，单位毫秒
>   count:         在限流的时间内，允许通过的次数
>   interfaceName: 限流接口名
>   ```
>
>
>
>4. 全局异常处理
    >
    >   ```java
>   @ControllerAdvice
>   public class CustomExceptionHandler {
>   
>       @ExceptionHandler(RedissonRateLimitException.class)
>       public void redissonRateLimitException(RedissonRateLimitException exception, HttpServletResponse resp) throws IOException {
>           resp.setContentType("application/json;charset=utf-8");
>           PrintWriter out = resp.getWriter();
>           out.write(exception.getMessage());
>           out.flush();
>           out.close();
>       }
>   }
>   ```
>
>5.
>
>

#### 三.starter-ichong-jobregister模块的使用

>1. 相关服务添加依赖
    >
    >   ```xml
>          <dependency>
>               <groupId>com.ichong</groupId>
>               <artifactId>starter-ichong-jobregister</artifactId>
>               <version>0.0.1-SNAPSHOT</version>
>           </dependency>
>   ```
>
>
>
>2. 添加配置（redission的配置，xxl-job配置）
    >
    >   ```yaml
>   xxl-job:
>    #xxl-job管理平台的账号
>    username: admin
>    #xxl-job管理平台的密码
>    password: 123456
>    #xxl-job管理平台(改成你公司的对应地址)
>    url: http://127.0.0.1:8080/xxl-job-admin
>   
>   #如果项目已经包含redission的配置，则不需要导入
>   singleServerConfig:
>     idleConnectionTimeout: 10000
>     connectTimeout: 10000
>     timeout: 3000
>     retryAttempts: 3
>     retryInterval: 1500
>     # 如果Redis服务端配置有密码需要替换password的值
>     password: null
>     subscriptionsPerConnection: 5
>     clientName: null
>     # 替换为自己真实Redis服务端连接
>     address: "redis://127.0.0.1:6379"
>     subscriptionConnectionMinimumIdleSize: 1
>     subscriptionConnectionPoolSize: 50
>     connectionMinimumIdleSize: 24
>     connectionPoolSize: 64
>     database: 0
>     dnsMonitoringInterval: 5000
>   threads: 16
>   nettyThreads: 32
>   codec: "!<org.redisson.codec.MarshallingCodec> {}"
>   transportMode: "NIO"
>   ```
>
>3. 使用方式(@XxlRegister)
    >
    >   ```
>   在相应的服务的自动任务方法上加上@XxlRegister注解，该注解定义xxljob，自动任务需要配置的所有参数，相关开发人员更具需要自己调整
>   ```
>
>4.
>
>

