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

## 前端

0. 在`frontend`目录下`npm install`安装前端依赖

1. `npm run start`启动前端项目

2. 前端技术：

项目结构: 
```
|--api       # 后端api接口
|--common    # 公共样式
|--component # 公共业务组件
|--layout    # 页面布局组件
  |-- directory   # 根据业务功能再细化布局
  |-- directory
|--module    # redux
|--ui        # 公共组件(业务无关)
|--util      # 前端工具类
```

* [x] react: https://reactjs.org/docs/getting-started.html
  - [x] React组件：https://reactjs.org/docs/react-component.html
  - [x] 高阶组件：https://reactjs.org/docs/higher-order-components.html
  - [x] React官方例子：https://reactjs.org/community/examples.html
  - [ ] React Hook: https://reactjs.org/docs/hooks-intro.html
  - [ ] React Top Api: https://reactjs.org/docs/react-api.html
* [ ] react-router: https://reactrouter.com/web/example/basic
* [ ] react-redux: https://react-redux.js.org/introduction/getting-started
* [x] ant-design: https://ant.design/components/overview-cn/
* [x] lodash: https://lodash.com/docs/4.17.15
* [x] axios: https://axios-http.com/docs/intro

  > - [X] 是需要重点学习的
  > - [ ] 是选学的，项目基本不用怎么改，改到的时候再去学一下也没问题
  > * React的重点章节学习一下
  > * ant-design的组件大致浏览一遍，做到心底有数，什么时候要用再去看文档
  > * 能不用lodash的地方可以不用lodash: https://github.com/you-dont-need/You-Dont-Need-Lodash-Underscore
  > * axios已经在`util/ajax.js`做了封装，比原生用起来更方便的链式调用

## 开发规范

* [HTTP-Restful](./.doc/HTTP-RESTful.md)

# 如何单元测试

* Hamcrest: https://dzone.com/articles/hamcrest-vs-assertj-assertion-frameworks-which-one
* Mocktio: https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html

写完单元测试后，可以跑一遍，看一看测试覆盖率
```sh
mvn clean test
```
`apollo-test`模块的`target/site/jacoco-aggregate/index.html`有相应的测试报告
