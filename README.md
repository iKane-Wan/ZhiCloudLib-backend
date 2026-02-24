# 智图云（ZhiCloudLib）- AI 智能图书馆系统

基于 Spring Boot 3.x + Spring Cloud Alibaba 微服务架构，融合 AI 能力与 Redis/RabbitMQ 等中间件，打造一套**企业级、高可用、智能化**的图书馆管理系统。

## 🎯 项目定位

解决传统图书馆 "检索效率低、管理流程繁琐、服务体验差" 的核心痛点，兼具业务完整性与技术前瞻性，是 Java 后端开发者校招 / 实习的高分亮点项目。

## ✨ 核心目标

- **业务目标**：实现图书检索、借阅、管理、智能咨询全流程数字化，AI 赋能提升服务效率与用户体验
- **技术目标**：落地微服务架构最佳实践，整合缓存、消息队列、AI 工程化等核心技术，构建高可用、高性能的分布式系统

## 🏗️ 技术架构

| 技术分层 | 核心技术 | 核心作用 |
|---------|---------|---------|
| 基础框架 | Spring Boot 3.x | 微服务单体应用基础，简化配置、依赖管理 |
| 微服务治理 | Spring Cloud Alibaba | Nacos（服务注册/配置中心）、Sentinel（限流/熔断）、Gateway（API 网关） |
| 数据存储 | MySQL 8.0 | 存储用户、图书、借阅记录等核心业务数据 |
| 缓存 | Redis 7.x | 缓存热门图书、用户登录态、AI 生成结果，提升接口响应速度 |
| 异步解耦 | RabbitMQ | 异步处理 AI 摘要生成、借阅通知、推荐计算等耗时任务，避免接口阻塞 |
| 服务通信 | OpenFeign | 微服务间同步远程调用 |
| 权限认证 | Shiro + JWT | 统一用户认证、接口权限管控 |

## 📦 核心微服务模块

### 1. 网关服务（cloud-gateway）
- 统一 API 入口，路由转发请求到对应微服务
- 全局 JWT Token 校验，实现统一鉴权
- 集成 Sentinel 实现接口限流、熔断
- 处理跨域、请求日志、接口签名校验等通用逻辑

**核心技术**：Spring Cloud Gateway + Sentinel + JWT

### 2. 用户服务（user-service）
- 用户注册、登录、个人信息管理
- 借阅证办理、挂失、注销
- 借阅记录、归还提醒管理
- 基于 Redis 缓存用户登录态和常用借阅信息

**数据库表**：t_user（用户信息）、t_borrow_record（借阅记录）、t_card（借阅证）

### 3. 图书服务（book-service）
- 图书基础信息（书名、作者、ISBN 等）CRUD
- 图书分类、库存、上下架管理
- 新增图书时，发送 RabbitMQ 消息触发 AI 摘要/标签生成
- 基于 Redis 缓存热门图书列表、图书详情，减轻数据库压力

**数据库表**：t_book（图书信息）、t_category（图书分类）、t_inventory（库存）

### 4. AI 智能服务（ai-service）⭐ 项目核心亮点
- 消费 RabbitMQ 消息，调用大模型生成图书摘要、标签
- 基于 Redis 缓存 AI 生成结果，避免重复调用大模型

### 5. 推荐服务（recommend-service）
- 基于用户借阅历史，调用 ai-service 获取个性化推荐书单
- 异步计算用户阅读偏好，结果存入 Redis，提升推荐接口响应速度
- 提供热门图书、分类图书推荐能力

### 6. 系统服务（system-service）
- 管理员账号、角色、权限管理
- 系统参数配置、操作日志管理
- 借阅规则、通知模板管理

### 7. 通知服务（notify-service）
- 集成第三方短信服务
- 集成邮件发送服务
- 站内信

## 🔧 核心技术方案

### Redis 核心应用场景

| 应用场景 | 实现方案 |
|---------|---------|
| 用户登录态缓存 | 用户登录生成 JWT，将 JWT 与用户信息绑定存入 Redis，设置过期时间，鉴权时直接读取 |
| 热门图书缓存 | 定时任务统计图书借阅量，将 Top20 热门图书存入 Redis Hash，过期时间 1 小时 |
| AI 生成结果缓存 | 将图书 ID 作为 Key，AI 生成的摘要/标签作为 Value 存入 Redis，过期时间 7 天 |
| 接口限流缓存 | 基于 Redis + Sentinel 实现分布式限流，避免单机限流失效 |

### RabbitMQ 核心应用场景

| 队列名称 | 消息内容 | 消费服务 | 核心作用 |
|---------|---------|---------|---------|
| book.summary.queue | 新增图书 ID | ai-service | 异步生成图书摘要，避免接口阻塞 |
| borrow.remind.queue | 即将到期的借阅记录 | user-service | 异步发送归还提醒（短信/站内信） |
| recommend.generate.queue | 用户 ID + 借阅记录 | recommend-service | 异步生成个性化推荐书单 |

## 🚀 快速开始

### 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 8.0
- Redis 7.x
- RabbitMQ 3.x
- Nacos 2.x

### 启动步骤

1. 克隆项目
```bash
git clone https://github.com/yourusername/ZhiCloudLib.git
```

2. 初始化数据库
```bash
# 执行 sql/init.sql 创建数据库和表
```

3. 启动 Nacos 服务
```bash
# 启动 Nacos 作为服务注册中心和配置中心
```

4. 启动微服务
```bash
# 按顺序启动：
# 1. cloud-gateway
# 2. user-service
# 3. book-service
# 4. ai-service
# 5. recommend-service
# 6. system-service
# 7. notify-service
```

## 📄 许可证

本项目基于 MIT 许可证开源。

## 🤝 贡献

欢迎提交 Issue 和 Pull Request 来帮助改进项目！
