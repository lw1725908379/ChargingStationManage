# ChargingStationManage

## 项目描述
ChargingStationManage 是一个基于 SpringBoot 和 Vue 框架开发的充电桩管理系统。该项目采用前后端分离的架构，代码简洁规范，注释说明详细，易于理解和学习。项目功能丰富，涵盖了一个充电桩管理系统所需的所有功能。

## 项目功能
项目主要分为两个角色：普通用户和管理员。

### 普通用户功能
- 登录注册
- 浏览个人数据统计信息
- 管理个人信息
- 浏览运营商信息
- 浏览电站信息
- 浏览充电桩信息
- 预约充电桩
- 报修充电桩
- 管理个人预约信息
- 开始充电
- 结束充电
- 查看个人订单信息
- 支付订单
- 查看个人报修信息
- 查看个人告警信息
- 查看留言信息

### 管理员功能
- 查看全部数据统计信息
- 管理所有用户信息
- 管理所有运营商信息
- 管理所有电站信息
- 管理所有充电桩信息
- 管理所有预约信息
- 管理所有订单信息
- 管理所有报修信息
- 管理所有告警信息
- 管理所有留言信息

## 应用技术
- **后端技术**: SpringBoot, MyBatis
- **前端技术**: Vue3.0, ElementUI-Plus, Vite
- **数据库**: MySQL
- **缓存**: Redis

## 运行环境
- **开发工具**: IntelliJ IDEA 2024
- **数据库**: MySQL 5.8
- **缓存**: Redis 5.0.5
- **Java 版本**: JDK 1.8
- **构建工具**: Maven 3.6.3
- **Node.js 版本**: Node 14.16.1

## 项目启动步骤
1. git clone https://github.com/lw1725908379/ChargingStationManage.git。
2. 使用 IntelliJ IDEA 打开项目根目录。
3. 配置 MySQL 和 Redis 环境，确保项目压缩包中自带的版本能够正常运行。
4. 在 IntelliJ IDEA 中启动后端 SpringBoot 项目。
5. 使用命令行工具在项目根目录运行以下命令以启动前端项目：
   ```bash
   cd web
   npm install
   npm run dev
   ```
6. 打开浏览器，访问 `http://localhost:8080`，即可使用充电桩管理系统。

## 目录结构
```
ChargingStationManage/
├── .idea/               IntelliJ IDEA 配置文件
├── db/                  数据库文件
├── docs/                项目文档
├── server/              后端代码
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── yjq/
│   │   │   │           └── programmer/
│   │   │   │               ├── annotation/
│   │   │   │               ├── bean/
│   │   │   │               ├── config/
│   │   │   │               ├── constant/
│   │   │   │               ├── controller/
│   │   │   │               ├── dao/
│   │   │   │               ├── domain/
│   │   │   │               ├── dto/
│   │   │   │               ├── enums/
│   │   │   │               ├── interceptor/
│   │   │   │               ├── service/
│   │   │   │               └── utils/
│   │   │   └── resources/
│   └── pom.xml           Maven 配置文件
├── target/              Maven 编译输出目录
├── web/                 前端代码
│   ├── .vscode/         VS Code 配置文件
│   ├── node_modules/    Node.js 依赖包
│   ├── public/          静态资源
│   ├── src/             源代码
│   │   ├── assets/      资源文件
│   │   ├── components/  组件
│   │   ├── directive/   指令
│   │   ├── router/      路由
│   │   ├── store/       状态管理
│   │   ├── styles/      样式文件
│   │   ├── util/        工具函数
│   │   └── views/       视图
│   ├── .env.development 开发环境配置
│   ├── .gitignore       Git 忽略文件
│   ├── index.html       入口 HTML 文件
│   ├── package.json     Node.js 项目配置
│   ├── package-lock.json Node.js 依赖锁定文件
│   ├── README.md        项目说明文件
│   └── vite.config.js   Vite 配置文件
└── README.md            项目说明文件
```



## 许可证
本项目采用 MIT 许可证。详细信息请参阅 LICENSE 文件。

---

感谢您使用 ChargingStationManage，希望这个项目对您的学习和工作有所帮助！
