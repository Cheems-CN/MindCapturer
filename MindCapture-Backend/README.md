# MindCapture 后端项目

## 项目介绍

MindCapture 是一个思维导图和笔记管理系统，帮助用户组织思路、记录灵感和管理知识。本仓库包含 MindCapture 的后端服务代码。

## 技术栈

- Spring Boot 2.7.x
- MySQL 8.x
- MyBatis-Plus 3.5.x
- Spring Security
- JWT 认证
- Redis 缓存
- Kaptcha 验证码

## 项目结构

```
MindCapture-Backend/
├── MindCapture-Common/    # 通用工具类和配置
├── MindCapture-Pojo/      # 实体类、DTO和VO
└── MindCapture-Service/   # 业务逻辑和控制器
```

## 功能模块

- 用户认证：注册、登录、JWT 令牌管理
- 验证码：图形验证码生成和验证
- 用户管理：用户信息的增删改查

## 快速开始

### 环境要求

- JDK 1.8+
- Maven 3.6+
- MySQL 8.0+
- Redis 6.0+

### 数据库配置

1. 创建数据库和表结构

```sql
# 执行项目根目录下的 schema.sql 文件
```

2. 在 `application.yml` 中配置数据库连接信息

### 启动项目

```bash
# 编译项目
mvn clean package

# 运行项目
java -jar MindCapture-Service/target/MindCapture-Service-1.0.0.jar
```

## API 文档

启动项目后，访问 http://localhost:8080/swagger-ui.html 查看 API 文档。

## 前端项目

前端项目位于 `../web-base` 目录，基于 Vue 3 开发。