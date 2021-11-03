# fileserver
文件服务器

## 技术栈
- Spring Boot
- MySQL

## 项目架构

### 服务器端：
Springboot搭建服务器端，Tomcat启动（127.0.0.1）端口8080

- 上传文件接口：/api/file  method：POST
- 下载文件接口：/api/file  method：GET
- 获取文件元数据接口：/api/file/metadata  method：GET

### 客户端：
使用Java的Http连接工具类为客户端封装http请求，访问服务器接口。

ClientFileBase：
- UploadFile方法：上传文件
- DownloadFile方法：根据uuid下载服务器端文件
- FileData方法：获取文件元数据

ClientMain：
- 客户端的启动类，对服务端的三个方法进行测试





