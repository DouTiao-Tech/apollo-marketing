[![codecov](https://codecov.io/gh/DouTiao-Tech/apollo-marketing/branch/master/graph/badge.svg?token=UNKZNBbqng)](https://codecov.io/gh/DouTiao-Tech/apollo-marketing)

# architecture

![architecture](./.doc/apollo.svg)

# 怎么贡献代码

![image](https://user-images.githubusercontent.com/19494806/125757641-542fd26b-c18e-4985-a810-af9c0f12054b.png)

原理： https://www.zhihu.com/question/39721968/answer/801943406

# fork仓库落后于原仓库怎么办

![image](https://user-images.githubusercontent.com/19494806/125757733-38d3a414-48e4-4c97-9f7d-156702d79e71.png)

原理： https://www.zhihu.com/question/28676261

# 如何开发

## 后端

0. 先安装docker和Java环境

1. 启动依赖的存储：mysql、clickhouse、kafka
    ```sh
    docker-compose up
    ```
   > 如果修改了`.init-sql`中的ddl表结构，注意重启docker容器
2. 启动应用，不同环境下添加`-Dspring.profiles.active=dev`参数，目前支持`dev`、`prod`两个环境
3. 接口文档地址，http://localhost:8888/swagger-ui.html

### 开发规范

* [HTTP-Restful](./.doc/HTTP-RESTful.md)

### 如何单元测试
- 单元测试框架
  * https://junit.org/junit4/
- 断言
  * Hamcrest: http://hamcrest.org/JavaHamcrest/tutorial
  * AssertJ: https://assertj.github.io/doc/
- 模拟数据
  * Mocktio: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html

写完单元测试后，可以跑一遍，看一看测试覆盖率
```sh
mvn clean test
```
`apollo-test`模块的`target/site/jacoco-aggregate/index.html`有相应的测试报告

## 前端

[apollo-marketing-frontend](https://github.com/DouTiao-Tech/apollo-marketing-frontend)

## 前后端交互api 
前后端交互API使用[apollo-marketing-api](https://github.com/DouTiao-Tech/apollo-marketing-api)，这个项目是自动生成的代码。

后端接口变更后，只需要在github actions点一下重新发布web api即可，代码会自动生成到[apollo-marketing-api](https://github.com/DouTiao-Tech/apollo-marketing-api)项目中，更新后前端项目`npm install`即可拉取最新的api。

![image](https://user-images.githubusercontent.com/19494806/127437193-391bba44-156e-4849-9d8e-d3158fa92780.png)

