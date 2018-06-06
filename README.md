# spring-cloud-microservice


随着业务需求增加，众多企业面临代码耦合严重、效率低下的问题。在Netflix开源了一套自己的微服务架构后，Spring随即也基于此推出SpringCloud。
目前来说，SpringCloud的门槛相对较低，在了解大致SpringCloud后即可上手，更多的是配置，或者是套路上的东西。当然我这么说只是对于希望快速
上手的同学而言，源码博大精深，有兴趣可以多多研究。自己做微服务的也做了半年了，现在写一个demo，总结一下springcloud的基本用法，让我们的
微服务的小车先开起来。

## 项目构成
- []Eureka注册中心
- []Config配置中心
- []Oauth2认证中心
- []Zuul网关
- []api-admin微服务本身

## 注册中心Eureka
Eureka作为服务的注册中心，服务间的互相调用都是通过Eureka来完成，所有的服务都将自己注册到eureka中。当A服务希望调用B服务时，
A只需使用B的instanceId，而不是ip，即可完成调用。在分布式应用中，服务随机部署在各个服务器中，根据ip去调用服务极其低效，你再写代码。
当服务启动多个实例时候

`
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
`

## 配置中心Config

## 网关Zuul

## 微服务webapp

## 认证中心Oauth2

