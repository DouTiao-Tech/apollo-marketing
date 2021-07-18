# architecture

![architecture](./.doc/apollo.svg)

# 怎么贡献代码

![image](https://user-images.githubusercontent.com/19494806/125757641-542fd26b-c18e-4985-a810-af9c0f12054b.png)

原理： https://www.zhihu.com/question/39721968/answer/801943406

# fork仓库落后于原仓库怎么办

![image](https://user-images.githubusercontent.com/19494806/125757733-38d3a414-48e4-4c97-9f7d-156702d79e71.png)

原理： https://www.zhihu.com/question/28676261

# 如何开发

0. 先安装docker和Java环境

1. 启动依赖的存储：mysql、clickhouse、kafka
    ```sh
    docker-compose up
    ```
   > 如果修改了`.init-sql`中的ddl表结构，注意重启docker容器
2. 启动应用，不同环境下添加`-Dspring.profiles.active=dev`参数，目前支持`dev`、`prod`两个环境

# 如何单元测试

Hamcrest: https://dzone.com/articles/hamcrest-vs-assertj-assertion-frameworks-which-one
Mocktio: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html

写完单元测试后，可以跑一遍，看一看测试覆盖率
```sh
mvn clean test
```
每个项目模块的`target/site/jacoco/index.html`有响应的测试报告