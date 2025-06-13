# MindCapture 前端项目

## 项目介绍

MindCapture 是一个思维导图和笔记管理系统，帮助用户组织思路、记录灵感和管理知识。本仓库包含 MindCapture 的前端代码。

## 技术栈

- Vue 3
- Vue Router
- Axios
- Vite

## 项目结构

```
web-base/
├── public/             # 静态资源
├── src/                # 源代码
│   ├── assets/         # 资源文件
│   ├── components/     # 公共组件
│   ├── router/         # 路由配置
│   ├── utils/          # 工具函数
│   ├── views/          # 页面组件
│   ├── App.vue         # 根组件
│   └── main.js         # 入口文件
└── package.json        # 项目配置
```

## 功能模块

- 用户认证：注册、登录
- 验证码：图形验证码集成
- 用户信息管理

## 开发环境设置

### 推荐的 IDE 设置

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (禁用 Vetur)。

### 自定义配置

参见 [Vite 配置参考](https://vite.dev/config/)。

## 快速开始

### 环境要求

- Node.js 16+
- npm 8+ 或 yarn 1.22+

### 安装依赖

```sh
npm install
```

### 启动开发服务器

```sh
npm run dev
```

### 构建生产版本

```sh
npm run build
```

## 后端项目

后端项目位于 `../MindCapture-Backend` 目录，基于 Spring Boot 开发。

## 浏览器支持

- Chrome
- Firefox
- Safari
- Edge
