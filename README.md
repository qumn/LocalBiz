# LocalBiz
`LocalBiz` 的是 Local Business 的缩写, 含义为着附近的商业.  
此项目的目标为让用户可以足不出户的体检附件的商业服务  

## 项目结构
### 后端项目架构
后端基于[**Ruoyi Cloud**](https://gitee.com/y_project/RuoYi-Cloud), 
项目的架构图如下: 
![后端架构图](./img/lb-architecture.svg)
各个模块的作用: 
* **ruoyi-system**  
系统管理模块(user, dict, dept, permission)
* **ruoyi-auth**  
鉴权模块(JWT)
* **ruoyi-file**  
文件存储(本地文件存储/MinIO)  
* **lb-management**  
Lb的后台管理(商家, 商品, 订单管理...)
* **lb-client**  
客户端管理(购物车, 配送, 通知...)

### 后端技术选型
后端使用如下技术:
* **Spring Boot&Spring Colud**
* **ORM 框架**: Mybatis Plus
* **ORM 测试框架**: [**Database Rider**](https://github.com/database-rider/database-rider)
* **数据库**: Mysql8
* **缓存**: Redis
* **数据库版本管理**: Flyway
* **配置中心**: Nacos
* **服务注册中心**: Nacos
* **网关**: Spring Gateway
* **服务间调用**: OpenFeign

## 运行项目

### 初始化数据库
1. 创建一个名为 `ruoyi-cloud` 的数据库, 执行 `/sql/ruoyi-cloud.sql` sql 文件  
    此数据库包含Ruoyi的基础数据, 以及项目的测试数据
2. 创建一个名为 `ry-config` 的数据库, 执行 `./sql/ry-config.sql` sql 文件  
    此数据库包换Nacos的配置信息

### 启动Nacos
1. 设置 **Nacos** 使用上一步创建的`ry-config`作为配置数据库  
    `conf/application.properties` 中的 `db.url`, `db.user`,
    `db.password`设置为你的数据库地址,数据库用户名以及数据库密码.
2. 使用 `./bin/startup.sh -m standalone`(在Nacos安装目录) 命令以单机模式启动Nacos

### 启动项目
此项目最小需要启动如下模块
1. **ruoyi-system**
2. **ruoyi-auth**
3. **ruoyi-file**  
4. **RuoyiGateWay**
5. **lb-management**  
6. **lb-client**  

### 运行Flutter 客户端
参考客户端项目的[README](https://github.com/qumn/LocalBizFlutter)

### 运行商家管理前端
1. 进入商家管理前端目录 `cd lb-front`
2. 安装依赖 `npm install`
3. 运行项目 `npm run dev`

### 运行后台管理前端
1. 进入商家管理前端目录 `cd ruoyi-ui`
2. 安装依赖 `npm install`
3. 运行项目 `npm run dev`

