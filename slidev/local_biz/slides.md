---
theme: seriph
background: https://source.unsplash.com/collection/94734566/1920x1080
class: text-center
highlighter: shiki
lineNumbers: false
info: |
  ## Slidev Starter Template
  Presentation slides for developers.

  Learn more at [Sli.dev](https://sli.dev)
drawings:
  persist: false
transition: slide-left
title: Local Biz 大作业
---

# Local Biz

Spring Boot 大作业

<div class="abs-br m-6 flex gap-2">
    成员: <br>
    汪子健 
    吴征世 <br>
    邹富强 
    叶宏程 <br>
</div>

<!--
The last comment block of each slide will be treated as slide notes. It will be visible and editable in Presenter Mode along with the slide. [Read more in the docs](https://sli.dev/guide/syntax.html#notes)
-->

---
transition: fade-out
---

# 项目概述

本项目旨在将线下的附近的商业搬运到线上, Local Biz 就是 Local Business 的简写.  
实现用户足不出户浏览周围的商业

<Toc></Toc>

<!--
You can have `style` tag in markdown to override the style for the current page.
Learn more: https://sli.dev/guide/syntax#embedded-styles
-->

<style>
h1 {
  background-color: #2B90B6;
  background-image: linear-gradient(45deg, #4EC5D4 10%, #146b8c 20%);
  background-size: 100%;
  -webkit-background-clip: text;
  -moz-background-clip: text;
  -webkit-text-fill-color: transparent;
  -moz-text-fill-color: transparent;
}
</style>

<!--
Here is another comment.
-->

---
layout: image-right
image: https://source.unsplash.com/collection/94734566/1920x1080
---

# 后端技术选型

基于 ruoyi cloud 开发

* **Spring Boot&Spring Colud**
* **ORM 框架**: Mybatis Plus
* **ORM 测试框架**: Database Rider![^1]
* **数据库**: Mysql8
* **缓存**: Redis
* **数据库版本管理**: Flyway
* **配置中心**: Nacos
* **服务注册中心**: Nacos
* **网关**: Spring Gateway
* **服务间调用**: OpenFeign

[^1]: [Database Rider](https://github.com/database-rider/database-rider)

---
layout: two-cols
---
# 后端架构

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

::right::
<img class="h-90" src="/lb-architecture.svg"/>

---
layout: default
---
# Er图
<img class="h-90" src="lb-er.svg">

---
layout: two-cols
---

# 客户端概述

客户端使用 **Flutter**[^1] 实现跨平台支持.
使用如下库:
* dio: 网络请求库
* json_serializable: json序列化和反序列化
* shared_preferences: 数据持久化
* go_router: 页面路由
* animations: 动画库
* logger: 日志库

支持如下平台:  
    Android IOS Windows MacOs Linux Web

::right::
<div class="w-140 h-200 mx-auto">
  <iframe class="w-full h-full transform-scale-50 transform-origin-top-left" src="http://localhost:8089" />
</div>

[^1]: [Flutter 官网](https://flutter.dev/)

<style>
.footnotes-sep {
  @apply mt-20 opacity-10;
}
.footnotes {
  @apply text-sm opacity-75;
}
.footnote-backref {
  display: none;
}
</style>


---
layout: two-thirds-iframe-right
url: http://localhost:3200/login
scale: 0.6
class: right
---
# 商家后台管理

使用 **Soybean Admin** 后台管理模版

使用的技术有

- Vue3
- Typescript
- Vite
- Naive-ui
- UnoCSS

---
layout: two-thirds-iframe-right
url: http://localhost
scale: 0.6
class: right
---
# LocalBiz 后台管理

使用 **Ruoyi** 自带的后台管理 Vue3 版本

使用的技术有

- Vue3
- Typescript
- Vite
- ElementUi

---
layout: center
class: text-center
---

# End

<style>
h1 {
  background-color: #2B90B6;
  background-image: linear-gradient(45deg, #4EC5D4 10%, #146b8c 20%);
  background-size: 100%;
  -webkit-background-clip: text;
  -moz-background-clip: text;
  -webkit-text-fill-color: transparent;
  -moz-text-fill-color: transparent;
}
</style>
