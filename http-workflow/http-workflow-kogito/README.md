# Kogito Serverless Workflow

## 前置条件

* Java 11
* Maven 3.8.1

## 启动命令

```shell
mvn clean quarkus:dev
```



## 项目初始化

```shell
# java 17 环境
mvn io.quarkus.platform:quarkus-maven-plugin:3.7.3:create \
    -DprojectGroupId=cn.sliew \
    -DprojectArtifactId=serverless-workflow-hello-world \
    -Dextensions="quarkus-resteasy-jackson,quarkus-smallrye-openapi" \
    -DnoCode
```

官网文档里面的命令不可用