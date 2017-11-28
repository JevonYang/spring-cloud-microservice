# spring-boot-demo
A Spring boot demo

## 微服务信息展示需求文档
### 总体说明：
微服务信息展示需求是为了条理化展示微服务信息、API信息。该需求包含服务名、功能描述、API信息、更新日期、使用方法、开发者信息、服务分组等增删改查等基本。
其设计模型如表一表二：

表一：微服务列表

|服务ID|服务名称|功能描述|更新日期|创建者|
|:---:|:---:|:---:|:---:|:---:|
|Long|String|Text|Date|String|

表二：API列表

|API_ID|功能描述|参数配置|服务ID|版本号|当前状态|更新日期|开发者|
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
|Long|String|Text|Service|String|String|Date|String|

暂定模型分为微服务表和API表：
微服务表包含服务ID、服务名称、功能描述、更新日期、创建者、URL等字段。
API列表包含API_ID、功能描述、参数配置、服务、版本号、当前状态、更新日期、开发者、URL等字段。，其中服务字段与微服务表具有一对多关系。

### 具体接口：
该需求应具备接口如下表：

|接口名称|Http请求|URL|Request Body|
|:---:|:---:|:---:|:---:|
|服务列表展示|POST|/microservice/list|Empty|
|服务具体展示|POST|/microservice/{id}|Empty|
|添加服务|POST|/microservice/add|microserviceEntity|
|修改微服务|PUT|/microservice/{id}|microserviceEntity|
|删除微服务|DELETE|/microservice/{id}|Empty|
|模糊查询服务|POST|/microservice/search|String|
|API列表展示|POST|/api/list|Empty|
|API具体展示|POST|/api/{id}|Empty|
|添加API|POST|/api/add|apiEntity|
|修改API|PUT|/api/{id}|apiEntity|
|删除API|DELETE|/api/{id}|Empty|
|模糊查询API|POST|/api/search|String|
