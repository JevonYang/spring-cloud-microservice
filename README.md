# spring-cloud-microservice


随着业务需求增加，众多企业面临代码耦合严重、效率低下的问题。在Netflix开源了一套自己的微服务架构后，Spring随即也基于此推出SpringCloud。
目前来说，SpringCloud的门槛相对较低，在了解大致SpringCloud后即可上手，更多的是配置，或者是套路上的东西。当然我这么说只是对于希望快速
上手的同学而言，源码博大精深，有兴趣可以多多研究。自己做微服务的也做了半年了，现在写一个demo，总结一下springcloud的基本用法，让我们的
微服务的小车先开起来。

## 项目构成
项目采用
- [x] Eureka注册中心
- [x] Config配置中心
- [ ] Oauth2认证中心
- [ ] Zuul网关
- [ ] api-admin微服务本身

## 注册中心Eureka

### 简介
Eureka作为服务的注册中心，服务间的互相调用都是通过Eureka来完成，所有的服务都将自己注册到eureka中。当A服务希望调用B服务时，
A只需使用B的instanceId，而不是ip，即可完成调用。在分布式应用中，服务随机部署在各个服务器中，根据ip去调用服务极其低效，你再写代码。
当服务启动多个实例时候，一般使用ribbon和feign，则会自动负载均衡，无需干预。

### 启动Eureka
想启动一个Eureka服务注册中心，配置上也十分简单。首先在pom.xml中加入`spring-cloud-starter-eureka-server`依赖，再在入口类中加入
注解`@EnableEurekaServer`即可。

```pom.xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka-server</artifactId>
</dependency>
```

```EurekaApplication.java
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
```


### 高可用Eureka
如果Eureka只希望standalone模式（只启动一个实例），事实上在配置文件application.yml无需配置内容，配置上基本信息以及端口即可。
如果希望启动HighAvailability模式(即启动多个实例),则可以参考一下配置。

在部署的时候，使用编译后同一个jar包，在启动后输入不同参数。例如使用命令`java -jar -Dspring.profiles.active=master your_jar_name.jar`，
则启动配置spring.profiles为配置master下的内容。而最上面一块的内容为公共配置，启动master配置时，实际的配置时公共配置+master配置；如果有些配置
两边都有，则master配置会覆盖公共配置内容。

```application.yml
#公共配置
spring:
  application:
    name: eureka-server
eureka:
  instance:
    lease-renewal-interval-in-seconds: 5
    lease-expiration-duration-in-seconds: 5
    prefer-ip-address: true
  client:
    register-with-eureka: true
    fetch-registry: true

---
# master节点配置
server:
  port: 8080
spring:
  profiles: master
eureka:
  instance:
    hostname: master
  client:
    service-url:
      defaultZone: http://master:8080/eureka/,http://backup1:8080/eureka/,http://backup2:8080/eureka/


---
# backup1节点配置
server:
  port: 8080
spring:
  profiles: backup1
eureka:
  instance:
    hostname: backup1
  client:
    service-url:
      defaultZone: http://master:8080/eureka/,http://backup1:8080/eureka/,http://backup2:8080/eureka/

---
# backup2节点配置
server:
  port: 8080
spring:
  profiles: backup2
eureka:
  instance:
    hostname: backup2
    instance-id: ${eureka.instance.hostname}
  client:
    service-url:
      defaultZone: http://master:8080/eureka/,http://backup1:8080/eureka/,http://backup2:8080/eureka/

```

### Eureka重点配置说明
下面来说一个几个重点配置

`eureka.client.registry-fetch-interval-seconds`表示服务间隔多久去Eureka中获取注册信息，默认为30s。

`eureka.instance.lease-renewal-interval-in-seconds`表示服务给Eureka发送心跳间隔，默认为30s。如果该instance实现了HealthCheckCallback，并决定让自己unavailable的话，则该instance也不会接收到流量。

`eureka.instance.lease-expiration-duration-in-seconds`表示Eureka上次收到服务的心跳后，等待下一次心跳的时间，如果超时则移除实例，默认为90s。

`eureka.server.enable-self-preservation`表示是否开启自我保护模式，默认为true。默认情况下，如果Eureka Server在一定时间内没有接收到某个微服务实例的心跳，Eureka Server将会注销该实例（默认90秒）。但是当网络分区故障发生时，微服务与Eureka Server之间无法正常通信，以上行为可能变得非常危险了——因为微服务本身其实是健康的，此时本不应该注销这个微服务。
Eureka通过“自我保护模式”来解决这个问题——当Eureka Server节点在短时间内丢失过多客户端时（可能发生了网络分区故障），那么这个节点就会进入自我保护模式。一旦进入该模式，Eureka Server就会保护服务注册表中的信息，不再删除服务注册表中的数据（也就是不会注销任何微服务）。当网络故障恢复后，该Eureka Server节点会自动退出自我保护模式。
综上，自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留），也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮、稳定。

`eureka.server.eviction-interval-timer-in-ms`表示Eureka清理无效节点的时间间隔，默认为60,000ms。

`eureka.client.register-with-eureka`表示是否将Eureka注册到自身，多实例中一边选择true。

`eureka.client.fetch-registry`表示是否拉去注册的服务。假设，服务A只注册到master节点的Eureka，但是开启该选项，所有的Eureka节点都会注册该服务。

`eureka.client.defaultZone`表示希望注册到Eureka的地址，格式为`http://ip:port/eureka/`,如果部署环境有dns，也可以将ip换成域名，如果有是ha模式，配置多个地址用逗号隔开。

以上为Eureka配置相对重要的配置。

## 配置中心Config

### 简介
在服务中经常会遇到一些易变的参数，例如数据库地址、超时时间等等。这些参数与代码关系耦合度低，但是每改一次就去修改代码中的参数，再去编译部署
显得很蠢，于是就有了配置中心得个实现。目前用的比较多的配置中心有SpringCloudConfig和携程的[Apollo](https://github.com/ctripcorp/apollo)。
SpringCloudConfig的好处是和SpringCloud绑定，全家桶（有好处有坏处，你懂的），部署简单；而Apollo部署比较麻烦，它首先要把部署地址写死在apollo-client中编译出来，
然后在配置项目中引用apollo-client……（既然是配置中心，为什么配置中心本身不能做到代码和参数的解耦？！）当然，这都是我一些私货，
事实上apollo功能十分强大，权限功能完整，并且支持多语言，大家都知道携程的技术栈主要是.net。两种各有长短，有兴趣的同学可以去github上看看，
文档说明十分详细。书归正传，接下来主要讲Spring Cloud Config。


### 启动Config
Config的配置同样简单:加入依赖`spring-cloud-config-server`，入口类加入注解`@EnableConfigServer`和`@EnableDiscoveryClient`，
前一个注解是使能Config注册中心，后一个则是注册到Eureka上，让其他服务找到该服务。

```pom.xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```

```ConfigApplication.java
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }
}
```
### Config Server配置
首先说注册中心Eureka相关内容，服务注册地址`eureka.client.serviceUrl.defaultZone`以及自己是谁`spring.application.name`即可。
我在这里加上`prefer-ip-address`的配置，后面会相信说明。

```yml
spring:
  application:
    name: config-server
eureka:
  instance:
    prefer-ip-address: true
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```
然后是就是配置中心相关内容。配置中心一般采用git或svn才作为配置存储端，官方文档上还有以jdbc数据库来存储的，本文以git来说明。
其中`spring.cloud.config.server.uri`是配置文件存放的git地址，为了更直观演示，我在gitee上配置相应的内容。
而`search-paths`则是在该库下的搜索路径，我们这里配置成了`'{application}'`代表不同服务会去git项目下找与项目名（`spring.application.name`）对应的文件。
其规则为`{application}-{profile}.yml`

例如，服务A中`spring.application.name: fuwu1`,则服务A在启动的时，配置中心则会找`git.uri`下的`fuwu1.yml`文件交给服务A作为配置。
服务A启动时，带有`-Dspring.profiles.active=master`参数时,则会配置中心则会找`git.uri`下的`fuwu1-master.yml`文件交给服务A作为配置。

配置同样需要有权限管理，而配置中心的权限逻辑和git相同（如果你使用git作为存储后端）。
我们可以配置`username`和`password`，如果需要有更高的要求，还可以和git一样，配置公钥私钥。在git上放上公钥，
在`private-key`这里直接填写私钥内容，你甚至可以代码上去掉权限信息，而在部署的服务器中配上私钥。总之，git是怎么配它就是怎么配置。
（[git相关内容点击这里](https://my.oschina.net/yangzijing/blog/1476531)）

还有一种配置路径的逻辑大概是这样：`uri: git@your-git-address:your-config-repo/{application}.git`，不同的项目配置放在不同的仓库，
这样可以配置不同仓库的权限。如果是这样配置，仓库里的文件命规则是`application-{profile}.yml`。

```yml
spring:
  cloud:
    config:
      server:
        git:
          uri: git@gitee.com:yangzijing/config.git
          search-paths: '{application}'
          #uri: git@your-git-address:your-config-repo/{application}.git
          #private-key:
          #username: yourusername
          #password: yourpassword
```

### Config客户端配置
客户端配置同样也是两类，eureka的配置和config的配置，要注意这些配置要写在bootstrap.yml中。简单来讲bootstrap和application的区别，
bootstrap.yml中的配置先启动，application.yml中的配置后启动，而需要动态配置的配置项则写在application.yml中。

Eureka相关的配置不再赘述，主要关心一下config的配置。配置可以有两种，1）指定config的ip，直接在`spring.cloud.config.uri`配上地址即可。
2）通过Eureka找到Config的地址,配置`spring.cloud.config.discovery.enabled=true`和`discovery.service-id`(这里的service-id和config项目的`spring.application.name`名字一致，其默认值是configserver)。

```yml
spring:
  application:
    name: api-admin
  cloud:
    config:
      #uri: http://ip:port
      discovery:
        enabled: true
        service-id: config-server
```

在api-admin项目中，增加了一个`from`配置，在`application.yml`可写可不写，如果写，还可以添加默认值`from: ${from:hello}`;如果不写，也可以，
同样也可以在java文件中直接引用,例如：
```java
@Value("${from}")
private String from;
```

但是要注意的是，使用了配置中心功能后，如果占位符（${xxx}）没有被正确替换，整个程序是会报错了，不管你用了没用默认值，这个是不科学的。

### 遇到的一个prefer-ip-address问题
在调试过程中发现一个问题，如果在config服务端`prefer-ip-address`没有打开，客户端则会找不到config服务端。在网上找了一下关于该配置的解释，
希望对大家有用[prefer-ip-address机制解释](http://www.itmuch.com/spring-cloud-code-read/spring-cloud-code-read-eureka-registry-ip/)

config的大致内容就说完了，可能还差配置内容加密，稍微有些繁杂，有机会再补充，急需可以查一下
[官方文档](https://cloud.spring.io/spring-cloud-static/Edgware.SR3/multi/multi__spring_cloud_config_server.html#_security)，或者别的中文博客

## 网关Zuul

## 认证中心Oauth2
Oauth2认证模块主要参考 https://github.com/wiselyman/uaa-zuul

