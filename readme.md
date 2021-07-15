# architecture

![architecture](./.doc/apollo.svg)

# 怎么贡献代码

https://www.zhihu.com/question/39721968/answer/801943406

# fork仓库落后于原仓库怎么办

https://www.zhihu.com/question/28676261

# 如何开发

0. 先安装docker和Java环境

1. 启动依赖的存储：mysql、clickhouse、kafka
    ```sh
    docker-compose up
    ```
   > 如果修改了`.init-sql`中的ddl表结构，注意重启docker容器
2. 启动应用，不同环境下添加`-Dspring.profiles.active=dev`参数，目前支持`dev`、`prod`两个环境