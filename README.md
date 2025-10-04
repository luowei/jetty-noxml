# Jetty-NoXML

## 项目简介

这是一个基于嵌入式 Jetty 8 服务器的轻量级 Java Web 应用示例项目，整合了 Spring MVC 和 JSP/JSTL 支持。该项目的最大特点是**完全不使用 XML 配置文件**，而是采用 Java 注解和编程式配置的方式来构建 Web 应用，体现了现代化的 Java Web 开发方式。

项目演示了如何通过纯 Java 代码方式启动一个内嵌的 Jetty 服务器，并配置 Spring MVC 框架，无需传统的 web.xml 和 Spring XML 配置文件。

## 技术栈

### 核心框架
- **Jetty 8.1.8** - 轻量级嵌入式 Web 服务器
- **Spring Framework 3.1.2** - 企业级应用框架
  - Spring Context - 核心容器
  - Spring WebMVC - MVC 框架
- **CGLIB 2.2.2** - 字节码生成库，用于 Spring AOP

### Web 技术
- **Servlet 3.0+** - 支持注解驱动配置
- **JSP 2.2** - 页面模板引擎
- **JSTL 1.2** - JSP 标准标签库

### 构建工具
- **Maven** - 项目管理和构建
- **Java 1.6** - 编译目标版本

## 项目结构

```
jetty-noxml/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── com/rootls/
│   │       │   ├── Main.java                    # 应用程序主入口
│   │       │   ├── WebServer.java                # Jetty 服务器核心配置类
│   │       │   ├── WebServerConfig.java          # 服务器配置接口及工厂类
│   │       │   ├── config/
│   │       │   │   ├── WebAppInitializer.java    # Web 应用初始化器（替代 web.xml）
│   │       │   │   ├── WebModule.java            # Spring MVC 配置模块
│   │       │   │   └── ApplicationModule.java    # 应用层配置模块
│   │       │   └── web/
│   │       │       └── Home.java                 # 示例 Controller
│   │       └── META-INF/
│   │           └── webapp/
│   │               └── WEB-INF/
│   │                   └── view/
│   │                       ├── index.jsp         # 首页视图
│   │                       ├── test.jsp          # 测试页面
│   │                       ├── css/              # 样式文件目录
│   │                       └── images/           # 图片资源目录
│   └── test/
│       └── java/
│           └── com/rootls/
│               └── IDE.java                      # IDE 环境标识类
├── pom.xml                                       # Maven 项目配置
└── README.md                                     # 项目说明文档
```

## 主要功能

### 1. 嵌入式 Jetty 服务器
- 通过编程方式启动和配置 Jetty 服务器
- 支持开发模式和生产模式配置
- 自定义线程池管理（开发模式：5-15 线程，生产模式：可配置）
- 集成 NCSA 格式的访问日志记录

### 2. 无 XML 配置
- 使用 `WebApplicationInitializer` 接口替代传统的 `web.xml`
- 通过 Java Config 方式配置 Spring 容器
- 利用注解驱动的 MVC 配置（`@Configuration`、`@EnableWebMvc`）

### 3. Spring MVC 集成
- 自动组件扫描（`@ComponentScan`）
- 视图解析器配置（支持 JSP/JSTL）
- 静态资源映射（CSS、JavaScript、图片等）
- MVC 路由和控制器支持

### 4. 注解驱动的开发
- 支持 Servlet 3.0 注解（`@WebServlet`、`@WebFilter`、`@WebListener`）
- Spring MVC 注解（`@Controller`、`@RequestMapping`）
- 自动扫描和注册 ServletContainerInitializer

### 5. 示例 Web 应用
- 提供简单的 Home Controller 示例
- 支持 JSP 模板渲染
- 演示 Model 数据传递到视图

## 使用方法

### 环境要求
- JDK 1.6 或更高版本
- Maven 3.x

### 编译项目
```bash
mvn clean package
```

该命令会生成一个包含所有依赖的可执行 JAR 包（使用 maven-shade-plugin 打包）。

### 运行应用

#### 方式一：通过 IDE 运行
直接运行 `com.rootls.Main` 类的 `main` 方法。

#### 方式二：通过命令行运行
```bash
java -jar target/jetty-noxml-1.0.jar
```

### 访问应用
启动成功后，在浏览器中访问：
```
http://localhost:8000/
```

您将看到 "It works!" 的欢迎页面，并显示 "Hi luowei!" 的个性化问候。

### 其他访问路径
- `http://localhost:8000/index` - 首页（同上）
- `http://localhost:8000/test` - 测试页面

### 配置说明

#### 开发模式配置
在 `Main.java` 中使用：
```java
WebServerConfig.Factory.newDevelopmentConfig("服务器名称", 端口, "主机接口")
```

默认配置：
- 服务器名称：happy
- 端口：8000
- 主机：localhost
- 最小线程数：5
- 最大线程数：15

#### 生产模式配置
```java
WebServerConfig.Factory.newProductionConfig("服务器名称", 端口, "主机接口", 最小线程数, 最大线程数)
```

## 依赖说明

### Spring 依赖
- `spring-context`：Spring 核心容器，提供依赖注入和应用上下文
- `spring-webmvc`：Spring MVC 框架，提供 Web 层支持

### Jetty 依赖
- `jetty-server`：Jetty 核心服务器
- `jetty-servlet`：Servlet 支持
- `jetty-webapp`：Web 应用上下文支持
- `jetty-servlets`：额外的 Servlet 工具
- `jetty-annotations`：注解处理支持
- `jetty-jsp`：JSP 引擎支持

### 其他依赖
- `cglib`：用于 Spring AOP 代理
- `jstl`：JSP 标准标签库

## 关键技术点

### 1. WebApplicationInitializer
实现了 Servlet 3.0 规范的 `WebApplicationInitializer` 接口，在应用启动时自动被检测并执行：
- 注册 Spring ContextLoaderListener
- 配置 DispatcherServlet
- 配置 JspServlet

### 2. 自定义注解配置扫描
`WebServer.java` 中实现了自定义的 `AnnotationConfiguration`，用于：
- 扫描 classpath 中的注解
- 处理 `@WebServlet`、`@WebFilter`、`@WebListener` 等注解
- 支持 ServletContainerInitializer 机制

### 3. 资源加载
静态资源和 JSP 文件从 classpath 的 `META-INF/webapp` 路径加载，这是嵌入式应用的常见做法。

### 4. Maven Shade 插件
使用 maven-shade-plugin 将所有依赖打包到一个可执行 JAR 中，方便部署：
- 合并 META-INF/spring.handlers 和 spring.schemas
- 过滤签名文件（.DSA、.RSA、.SF）
- 指定主类为 `com.rootls.Main`

## 常见问题

### 资源文件找不到
如果运行时提示找不到配置文件或资源文件，请确保 `META-INF` 文件夹已正确拷贝到 classpath 下（即 `target/classes` 目录）。

### 端口冲突
如果 8000 端口被占用，可以在 `Main.java` 中修改端口号。

## 扩展建议

1. **添加数据库支持**：集成 Spring Data JPA 或 MyBatis
2. **添加安全认证**：集成 Spring Security
3. **添加 RESTful API**：扩展 Controller 支持 JSON 响应
4. **配置外部化**：使用 properties 或 YAML 文件管理配置
5. **日志增强**：集成 SLF4J + Logback

## 参考资料

**视频讲解：**
[集成Spring-MVC内嵌Jetty的web应用视频](http://www.tudou.com/programs/view/IJaTf3bJrVc/)

## 许可证

本项目为学习示例项目，可自由使用和修改。

## 作者

作者：luowei
GroupId：com.rootls
