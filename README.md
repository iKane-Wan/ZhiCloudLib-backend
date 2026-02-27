# 智图云（ZhiCloudLib）- AI 智能图书馆系统

基于 Spring Boot 3.x + Spring Cloud 微服务架构，融合 AI 能力与 Redis/RabbitMQ 等中间件，打造一套**企业级、高可用、智能化**的图书馆管理系统。

## 🎯 项目定位

解决传统图书馆"检索效率低、管理流程繁琐、服务体验差"的核心痛点，兼具业务完整性与技术前瞻性。

## ✨ 核心目标

- **业务目标**：实现图书检索、借阅、管理、智能咨询全流程数字化，AI 赋能提升服务效率与用户体验
- **技术目标**：落地微服务架构最佳实践，整合缓存、消息队列、AI 工程化等核心技术，构建高可用、高性能的分布式系统

## 🏗️ 技术架构

| 技术分层 | 核心技术 | 核心作用 |
|---------|---------|---------|
| 基础框架 | Spring Boot 3.3.4 | 微服务应用基础，简化配置、依赖管理 |
| 微服务治理 | Spring Cloud 2023.0.3 | Gateway（API 网关）、OpenFeign（服务调用） |
| 服务注册与配置 | Nacos 2.x | 服务注册发现、统一配置管理 |
| 数据存储 | MySQL 8.0.30 | 存储用户、图书、借阅记录等核心业务数据 |
| 数据持久化 | MyBatis-Plus 3.5.9 | 简化数据库操作，提供通用 CRUD |
| 缓存 | Redis 7.x | 缓存热门图书，提升接口响应速度 |
| 异步解耦 | RabbitMQ 3.x | 异步处理 AI 摘要生成、通知等耗时任务，避免接口阻塞 |
| 服务通信 | OpenFeign | 微服务间同步远程调用 |
| 权限认证 | JWT (java-jwt 4.4.0) | 统一用户认证、接口权限管控 |
| API 文档 | Knife4j 4.5.0 | 生成美观的接口文档，支持在线调试 |
| AI 能力 | 通义千问 (DashScope) | 生成图书摘要、智能推荐 |

## 📦 核心微服务模块

### 1. 网关服务（lib-gateway）
- 统一 API 入口，路由转发请求到对应微服务
- 全局 JWT Token 校验，实现统一鉴权
- 处理跨域、请求日志等通用逻辑

**核心技术**：Spring Cloud Gateway + JWT

**配置文件**：
- `application.yml`：基础配置
- `application-route.yml`：路由配置

### 2. 用户服务（service-user）
- 用户注册、登录（支持账号密码登录、手机验证码登录）
- 个人信息管理
- 基于 Redis 缓存用户登录态和验证码
- 通过 RabbitMQ 发送短信通知

**数据库表**：t_user（用户信息）

**核心接口**：
- `/api/user/auth/accountLogin`：账号密码登录
- `/api/user/auth/phoneLogin/{phone}`：手机验证码登录
- `/api/user/auth/phone/code`：获取手机验证码
- `/api/user/register`：用户注册

**核心技术**：JWT + Redis + RabbitMQ + MD5 加密（加盐）

### 3. 图书服务（service-book）
- 图书基础信息（书名、作者、ISBN、出版社等）CRUD
- 图书分类管理（支持多级分类）
- 图书借阅记录管理（借阅、归还、续借）
- 新增图书时，发送 RabbitMQ 消息触发 AI 摘要生成
- 基于 Redis 缓存热门图书列表、图书详情，减轻数据库压力

**数据库表**：t_book（图书信息）、t_book_category（图书分类）、t_borrow_record（借阅记录）

**核心接口**：
- `/api/book`：图书增删改查、分页查询
- `/api/book/category`：图书分类管理
- `/api/book/borrowRecord`：借阅记录管理（借阅、归还、续借）
- `/api/book/introduction`：更新图书简介（内部接口）

**核心技术**：Redis 缓存 + RabbitMQ 异步消息 + MyBatis-Plus 分页

### 4. AI 智能服务（service-ai）⭐ 项目核心亮点
- 消费 RabbitMQ 消息，调用通义千问大模型生成图书摘要
- 通过 Feign 客户端调用图书服务更新图书简介
- 支持自定义 Prompt 模板，生成符合要求的图书描述

**核心技术**：RabbitMQ 消息队列 + OpenFeign + 通义千问 API

**核心接口**：
- `/api/ai`：生成图书描述

### 5. 通知服务（service-notify）
- 集成短信发送服务
- 集成邮件发送服务
- 支持责任链模式处理不同类型的通知
- 消费 RabbitMQ 消息异步发送通知

**核心技术**：RabbitMQ + 责任链模式

## 🔧 核心技术方案

### JWT 双 Token 认证机制

| Token 类型 | 过期时间 | 用途 |
|-----------|---------|------|
| Access Token | 30 分钟 | 访问受保护的资源接口 |
| Refresh Token | 7 天 | 刷新 Access Token，避免用户频繁登录 |

**认证流程**：
1. 用户登录成功后，服务端生成 Access Token 和 Refresh Token
2. 客户端将两个 Token 存储在本地，后续请求携带在请求头
3. Access Token 过期后，服务端自动使用 Refresh Token 生成新的 Access Token
4. Refresh Token 也过期后，用户需要重新登录

### Redis 核心应用场景

| 应用场景 | Key 格式 | 实现方案 | 过期时间 |
|---------|---------|---------|---------|
| 手机验证码 | `library:phone:code:{phone}` | 存储验证码，登录时校验 | 5 分钟 |
| 热门图书缓存 | `library:book:{page}` | 缓存分页查询结果 | 默认不过期 |
| 用户登录态 | 无（JWT 自包含） | JWT Token 存储用户信息 | 30 分钟/7 天 |

### RabbitMQ 核心应用场景

| 队列名称 | 交换机 | 路由键 | 消息内容 | 消费服务 | 核心作用 |
|---------|-------|-------|---------|---------|---------|
| library.queue | library.exchange | library.routing | BookBO（图书信息） | ai-service | 异步生成图书摘要，避免接口阻塞 |
| library.queue | library.exchange | library.routing | MessageBO（通知消息） | notify-service | 异步发送短信验证码、借阅通知 |

**消息队列配置**：
- 队列持久化
- 交换机持久化
- JSON 消息序列化
- 自定义 RabbitTemplate

### 权限控制方案

**@Authorization 注解**：
- `value`：是否需要认证（默认 true）
- `role`：角色等级（0-普通用户，1-管理员）

**权限校验流程**：
1. AOP 切面拦截带有@Authorization 注解的方法
2. 从请求头获取 Access Token 和 Refresh Token
3. 校验 Token 有效性，过期则自动刷新
4. 解析 Token 获取用户角色，校验权限

## 🚀 快速开始

### 环境要求

- JDK 21+
- Maven 3.8+
- MySQL 8.0
- Redis 7.x
- RabbitMQ 3.x
- Nacos 2.x

### 配置说明

#### 1. Nacos 配置
在 Nacos 控制台创建以下配置：
- `service-book.yaml`：图书服务配置
- `service-user.yaml`：用户服务配置
- `service-ai.yaml`：AI 服务配置
- `service-notify.yaml`：通知服务配置

#### 2. 数据库配置
修改各服务的 `application.yml` 文件，配置 MySQL 连接信息：
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/library
    username: root
    password: your_password
```

#### 3. Redis 配置
确保 Redis 服务已启动，默认端口 6379

#### 4. RabbitMQ 配置
修改各服务的 `application.yml` 文件，配置 RabbitMQ 连接信息：
```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    virtual-host: /library
```

#### 5. AI 配置
在 `service-ai` 的 `application.yml` 中配置通义千问 API Key：
```yaml
alibaba:
  bigModel:
    api-key: sk-xxx
```

### 启动步骤

1. **启动 Nacos**
```bash
cd nacos/bin
startup.cmd -m standalone
```

2. **启动 MySQL、Redis、RabbitMQ**
确保所有中间件服务正常运行

3. **启动网关服务**
```bash
cd lib-gateway
mvn spring-boot:run
```

4. **启动各微服务**
```bash
# 启动用户服务
cd lib-services/service-user
mvn spring-boot:run

# 启动图书服务
cd lib-services/service-book
mvn spring-boot:run

# 启动 AI 服务
cd lib-services/service-ai
mvn spring-boot:run

# 启动通知服务
cd lib-services/service-notify
mvn spring-boot:run
```

### 访问接口文档

启动服务后，访问以下地址查看 API 文档：
- 图书服务：http://localhost:7020/doc.html
- 用户服务：http://localhost:7030/doc.html
- AI 服务：http://localhost:7010/doc.html

## 📁 项目结构

```
ZhiCloudLib-backend/
├── lib-common/                          # 公共模块
│   ├── annotation/                      # 自定义注解（@Authorization）
│   ├── aspect/                          # AOP 切面（权限校验）
│   ├── config/                          # 配置类（Knife4j、Redis 等）
│   ├── entity/                          # 实体类
│   │   ├── bo/                          # 业务对象
│   │   ├── dto/                         # 数据传输对象
│   │   ├── po/                          # 持久化对象
│   │   └── vo/                          # 视图对象
│   ├── enums/                           # 枚举类
│   ├── exception/                       # 异常处理
│   ├── utils/                           # 工具类（JWT、Redis、Bean 复制等）
│   └── R.java                           # 统一返回结果
├── lib-gateway/                         # 网关服务
│   └── src/main/
│       ├── java/com/kane/
│       │   └── GatewayApplication.java
│       └── resources/
│           ├── application.yml
│           └── application-route.yml
└── lib-services/                        # 微服务模块
    ├── service-book/                    # 图书服务
    │   ├── controller/                  # 控制器
    │   ├── service/                     # 服务层
    │   ├── mapper/                      # 数据访问层
    │   └── config/                      # 配置类（RabbitMQ）
    ├── service-user/                    # 用户服务
    │   ├── controller/                  # 控制器
    │   ├── service/                     # 服务层
    │   ├── mapper/                      # 数据访问层
    │   └── config/                      # 配置类（RabbitMQ、Redis）
    ├── service-ai/                      # AI 服务
    │   ├── controller/                  # 控制器
    │   ├── service/                     # 服务层
    │   ├── consumer/                    # 消息消费者
    │   ├── feign/                       # Feign 客户端
    │   └── utils/                       # AI 工具类
    └── service-notify/                  # 通知服务
        ├── consumer/                    # 消息消费者
        ├── feign/                       # Feign 客户端
        └── pattern/chain/               # 责任链模式
```

## 🔐 安全设计

### 1. 密码加密
- 使用 MD5 加密算法
- 每个用户独立的盐值（6 位随机字符串）
- 加密公式：`MD5(password + salt)`

### 2. 接口鉴权
- 基于 JWT 的无状态认证
- AOP 切面统一拦截
- 支持自动刷新 Token
- 角色权限控制

### 3. 验证码安全
- 5 分钟有效期
- 一次性使用（验证后立即删除）
- 手机号格式校验

## 📊 数据库设计

### t_user（用户表）
| 字段名 | 类型 | 说明 |
|-------|------|------|
| user_id | BIGINT | 用户 ID（主键） |
| phone | VARCHAR | 手机号 |
| password | VARCHAR | 加密后的密码 |
| salt | VARCHAR | 盐值 |
| user_type | INT | 用户类型（0-普通用户，1-管理员） |

### t_book（图书表）
| 字段名 | 类型 | 说明 |
|-------|------|------|
| book_id | BIGINT | 图书 ID（主键） |
| book_name | VARCHAR | 书名 |
| author | VARCHAR | 作者 |
| book_isbn | VARCHAR | ISBN |
| publisher | VARCHAR | 出版社 |
| publish_date | DATE | 出版日期 |
| category_id | BIGINT | 分类 ID |
| introduction | TEXT | 图书简介（AI 生成） |

### t_book_category（图书分类表）
| 字段名 | 类型 | 说明 |
|-------|------|------|
| category_id | BIGINT | 分类 ID（主键） |
| category_name | VARCHAR | 分类名称 |
| parent_id | BIGINT | 父分类 ID |
| sort | INT | 排序字段 |

### t_borrow_record（借阅记录表）
| 字段名 | 类型 | 说明 |
|-------|------|------|
| record_id | BIGINT | 记录 ID（主键） |
| user_id | BIGINT | 用户 ID |
| book_id | BIGINT | 图书 ID |
| borrow_time | DATETIME | 借阅时间 |
| due_time | DATETIME | 应归还时间 |
| return_time | DATETIME | 实际归还时间 |
| borrow_status | INT | 借阅状态（1-借阅中，2-已归还） |

## 🎯 核心功能流程图

### 1. 用户登录流程
```
用户登录 → 校验手机号和密码 → 生成 JWT Token → 返回 Token
                          ↓
                    存储到 Redis（可选）
```

### 2. 图书借阅流程
```
用户借阅 → 创建借阅记录 → 设置借阅时间 → 设置应归还时间（30 天后）
                                              ↓
                                        状态：借阅中
```

### 3. AI 生成图书摘要流程
```
新增图书 → 发送 RabbitMQ 消息 → AI 服务消费消息 → 调用通义千问 API
                                                      ↓
                                              生成图书简介
                                                      ↓
                                          调用 Feign 客户端更新
```

## 💡 项目亮点

1. **微服务架构**：采用 Spring Cloud 微服务架构，服务间松耦合，易于扩展
2. **AI 赋能**：集成通义千问大模型，自动生成图书摘要，提升智能化水平
3. **异步解耦**：使用 RabbitMQ 实现异步消息处理，提升系统性能和用户体验
4. **缓存优化**：基于 Redis 实现多级缓存，显著提升接口响应速度
5. **双 Token 机制**：JWT 双 Token 认证，平衡安全性和用户体验
6. **AOP 权限控制**：基于注解和 AOP 的统一权限校验，代码简洁优雅
7. **责任链模式**：通知服务采用责任链模式，易于扩展新的通知渠道
8. **完整的 API 文档**：集成 Knife4j，提供美观的接口文档和在线调试功能

## 📝 开发规范

### 1. 代码规范
- 遵循阿里巴巴 Java 开发手册
- 使用 Lombok 简化代码
- 统一使用 R 类封装返回结果
- 使用@Tag、@Operation 等注解完善 API 文档

### 2. 命名规范
- Controller 层：XXXController
- Service 层：XXXService（接口）、XXXServiceImpl（实现）
- Mapper 层：XXXMapper
- 实体类：PO（持久化对象）、DTO（数据传输对象）、VO（视图对象）、BO（业务对象）

### 3. 异常处理
- 使用 BusinessException 处理业务异常
- 全局异常处理器统一捕获并返回友好提示

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request 来帮助改进项目！

## 📄 许可证

本项目采用 MIT 许可证

## 👥 联系方式

如有问题或建议，请联系项目维护者。
