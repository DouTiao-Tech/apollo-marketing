<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">

# HTTP RESTful 交互规范细则

## 所有提供的外部 API 建议使用版本号作为根路径

为了避免系统重构导致的 URL 冲突，建议使用统一的版本号作为根路径，例如：

<div class="alert alert-success">
GET /v1/employees
</div>

在系统重构的时候，则通过根路径的版本号进行区分，例如：

<div class="alert alert-success">
GET /v2/employees
</div>

## URL 必须全部为小写

在 URL 中的所有字符应当全部为小写，例如：

<div class="alert alert-success">
GET /big-data/employees
</div>

错误的使用方法：

<div class="alert alert-danger">
GET /big-data/EMPLOYEES
</div>

`@PathVariable` 中的参数不包含在此规则内。

## URL 禁止使用“/”结尾

URL 禁止使用“/”结尾，例如正确的作法：

<div class="alert alert-success">
GET /employees
</div>

错误的作法：

<div class="alert alert-danger">
GET /employees/
</div>

## URL 中不允许包含空格

URL 中不允许出现空格，但是当URL在描述资源的时候，资源本身的唯一标识就包含空格，则是允许的，例如下例通过文章名称（文章名唯一）获取文章内容是被允许的：

<div class="alert alert-success">
GET /articles/how-to-learn-java%20script
</div>

但是这仅限于旧有的资源，而我们在设计的过程中，应当尽量避免在唯一标识中出现空格，例如本例就应该在用户提交后将空格替换成“-”符号。

## URL 中禁止在非文件流的资源上加后缀

如果该 URL 表述的资源不是文件流，则禁止使用后缀，例如下例是不被允许的：

<div class="alert alert-danger">
GET /employees.jsp
</div>

## URL 文件流必须标明后缀以及 Content-Type

但是如果 URL 本身就是文件流，例如下载文件、展示图片，则必须使用后缀表明文件类型，例如下例：

<div class="alert alert-success">
GET /files/qr-code.jpg
</div>

<div class="alert alert-success">
GET /files/foo.mp4
</div>

同时一定要在 Content-Type 中标记正确的文件类型，标准文件类型参考[这里](https://www.runoob.com/http/http-content-type.html)

这里标记常见的文件 Content-Type：

|扩展名|Content-Type|
|-|-|
|.*|application/octet-stream|
|.jpeg|image/jpeg|
|.png|image/png|
|.gif|image/gif|
|.mp4|video/mpeg4|
|.pdf|application/pdf|
|.doc|application/msword|
|.docx|application/vnd.openxmlformats-officedocument.wordprocessingml.document|
|.xls|application/vnd.ms-excel|
|.xlsx|application/vnd.openxmlformats-officedocument.spreadsheetml.sheet|
|.ppt|application/vnd.ms-powerpoint|
|.pptx|application/vnd.openxmlformats-officedocument.presentationml.presentation|
|.png|application/x-png|

<div class="alert alert-info">
无法找到的文件类型，统一使用第一个，即application/octet-stream，用于标记是一个文件下载。
</div>

## 查询参数遵循后台语言的命名规范

查询参数需要遵循后台语言的命名规范，例如我们的后台使用的是 Java 语言开发的，那么我们就必须使用驼峰命名法来传递查询参数，例如：

<div class="alert alert-success">
GET /employees?englishName=Alex
</div>

当后台语言是 Java 的时候，错误的使用方法：

<div class="alert alert-danger">
GET /employees?english_name=Alex
</div>

> 如果请求的后台是其他语言，就必须按照其他语言的命名规范来传递参数

## 多个单词用 “-” 分割

在URL中两个单词之间使用 “-” 分割，`PathVariable`除外。例如：

<div class="alert alert-success">
GET /companies/{companyName}/big-data/employees
</div>

如果`companyName`在数据库中存放的就是`X_Box`那么最终的 URL 写成以下方式也是被允许的：

<div class="alert alert-success">
GET /companies/X_Box/big-data/employees
</div>

> 通常我们不会遇到上述情况，因为唯一标识通常是 ID

其他情况下不允许使用其他方式来表示两个单词，例如下面的错误示例：

<div class="alert alert-danger">
GET /bigData
</div>

<div class="alert alert-danger">
GET /big_data
</div>

> 单词的分隔目前在网上有两种形式，一种是 - 还有一种是下划线 _ 本质上没有对错，但是我们在这里统一使用 - 来区分两个单词。

## 有明显层级关系的逻辑在 URL 中体现，但是不要超过两层

假设我们需要查询某个部门下所有的员工，那么就应该写成：

<div class="alert alert-success">
GET /departments/{did}/employees
</div>

而不建议写成：

<div class="alert alert-warning">
GET /employees?departmentId=??
</div>

但是层级关系超过两层的，则应该通过查询参数查询，例如下例：

<div class="alert alert-warning">
/companies/{cid}/departments/{did}/teams/{tid}/employees
</div>

则建议使用参数进行查询，例如：

<div class="alert alert-success">
/employees?companyId=..&departmentId=..&teamId=..
</div>

> 通常情况下两级表示所属关系就足够了，本调规则不属于强制性要求，只是建议的做法。

## 遵守请求方法动词表示操作意图

严格使用 GET、POST、PUT、PATCH、DELETE 请求方式来表达想要进行的操作，例如：

- <div class="badge badge-primary">GET</div> 仅用于查询
- <div class="badge badge-success">POST</div> 仅用于新增
- <div class="badge badge-danger">DELETE</div> 仅用于删除（包括非物理删除）
- <div class="badge badge-warning">PUT</div> 仅用于修改
- <div class="badge badge-info">PATCH</div> 仅用于子项修改

> 导出文件也必须是GET请求，因为你是想要获取一个文件。

针对同一资源的操作必须放在同一个资源路径下，例如：

```
GET	/employees -- 表示查询一个员工集合
POST	/employees -- 表示向这个员工集合中添加一个员工
PUT	/employees/{eid} -- 表示修改一个员工，唯一标识必须要写
DELETE	/employees/{eid} -- 表示删除一个员工
PATCH	/employees/{eid}/profiles/name?value={newName} --表示修改一个员工的姓名
```

除非资源有特殊要求，例如不允许被删除或该资源是只读的，否则的话建议将所有操作都写全。调用方可以通过`OPTIONS` 请求来获取该资源被允许的操作。
<div class="card text-white bg-primary mb-3" style="max-width: 100%;">
  <div class="card-header">注意！</div>
  <div class="card-body">
    <p class="card-text">当查询条件非常多的时候，我们如果使用 <span class="badge badge-warning">GET</span> 方式进行资源获取就会非常难以处理查询参数！但是使用 <span class="badge badge-success">POST</span> 方法好像又不符合动词的意图。</p>
<div class="alert alert-primary">
因此我们应该将整个查询就视作一个资源，同时采用 POST 方式进行查询，使其变得更加和谐。
</div>
例如我们需要通过非常复杂的查询条件查询一个资源`/employees`，这个资源需要根据 N 个查询条件进行查询，因此我们可以将查询条件 `query` 当做一个子资源设计成 <span class="badge badge-success">POST</span>请求。例如好的作法：

<div class="alert alert-success">
<p><span class="badge badge-success">POST</span> /employees/search-queries</p>
<pre>
Reuqest body:
{
    "foo": "bar",
    ....
}
</pre>
</div>
<p class="card-text">这个 URL 设计表达的意思就是向 [employees] 这个资源的子资源 [queries] 中新增查询资源，目的就是将查询条件 [query] 也当作一个资源进行新增，同时返回新增查询条件后的结果，而这个结果就是搜索结果！</p>
<p class="card-text">同样出现这个问题的还包含批量删除，如果批量删除的内容过多，也可以通过上述方式进行设计，例如一个好的作法如下：</p>
<div class="alert alert-success">
<p><span class="badge badge-success">POST</span> /employees/delete-queries</p>
<pre>
Reuqest body:
{
    "foo": "bar",
    ....
}
</pre>
</div>
  </div>
</div>

这么做并不与本规范所介绍的其他规范冲突（例如严格遵守名词表示资源），因为我们将 `search-queries` 和 `delete-queries` 视作资源集合，我们所做的只是将 `query` 作为资源新增到这个资源集合中，而删除和查询结果则是新增的结果！

## 遵守名词表示资源

集合使用复数名词，单个资源或属性使用唯一标识进行层级划分，例如：

<div class="alert alert-success">
GET /employees/{eid}/profiles/username
</div>

<div class="alert alert-success">
GET /employees/{eid}/tasks
</div>

错误的示例：

<div class="alert alert-danger">
GET /find-employees-by-id/{eid}
</div>

如果查询的资源本身就是一个“动作”，则视作名词，例如风控系统中有通过场景查询场景下包含了哪些动作的API：

<div class="alert alert-success">
GET /scenes/{sid}/actions/lock-user
</div>

这样写是被允许的。

> 尽量使用可数名词来表述资源，在特殊情况下必须使用不可数名词或单复数同形的单词的时候，需要加`-list`来表达这个资源是一个集合。

本条规则仅仅是一个 URL 设计建议。

## 集合类必须返回空集合而不是 Null

后台返回数据的时候，集合类必须返回空集合，而不能为 Null

```java
@Data
public class Foo {

    private List<String> bars = new ArrayList<>(); // 必须初始化一个空集合

}
```

在 Spring boot 中，可以在`Application`启动类中添加以下代码来统一处理(仅供参考)：

```java
@Bean
public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper) {
    objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
        @Override
        public void serialize(Object param, JsonGenerator jsonGenerator,
                              SerializerProvider paramSerializerProvider) throws IOException {
            if (param instanceof Iterable) {
                jsonGenerator.writeString("[]"); // 集合类的处理
            } else if (param instanceof Number) {
                jsonGenerator.writeString("0"); // 包装类的处理
            } else if (param instanceof String) {
                jsonGenerator.writeString(""); // 字符串
            } else {
                jsonGenerator.writeString("{}"); // 其他类型
            }
        }
    });

    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
    return mappingJackson2HttpMessageConverter;
}
```

## 所有的数据返回必须是 JSON 格式的（文件流和4xx错误信息除外）

必须保证返回的数据 HTTP 头中的 `Content-Type` 字段为 `application/json`，消息正文也必须是标准的 JSON 格式。

标准 JSON：

```json
{
    "string":"value",
    "number": 0,
    "boolean": true
}
```

JSON 中不允许有注释。

## 不建议使用 Map 返回数据

Spring Boot 会将 Map 自动解析成一个 JSON 对象，但是 Map 的 K，V是不固定的因此很容易引发前端开发的异常。

因此，除非特殊情况，否则不允许使用 Map 返回数据，如果必须要使用 Map，则必须在给前端的文档中写清楚这是一个 Map，并且告知前端里面哪些属性可能有，哪些属性可能没有。

## 子属性为对象的时候，必须告知前端这是一个对象

当 POJO 中的子属性是一个对象的时候，例如：

```java
@Data
public class Foo {

    private Bar bar;

}
```

则必须在文档中告诉前端这是一个对象，并且清晰表述这个子属性是否有可能为 NULL。

## 分页查询查询参数必须统一

分页查询必须统一查询参数，并且必须给出默认值，参数定义如下：

- `page` 要查询第几页
- `size` 每页多少条

Controller 写法如下：

```java
@RestController
@RequestMapping("/users")
public class DemoController {
    
    @GetMapping
    public Page<User> page(@RequestParam(value = "page",required = false,defaultValue = "1") long page,
                           @RequestParam(value = "size",required = false,defaultValue = "50") int size) {
        
        // Code here
        
    }
}
```

或者使用`spring data`提供的`PageRequest`作为请求入参(参看PageableHandlerMethodArgumentResolverSupport怎么解析入参的)，使用`PageImpl`作为请求结果。

## 分页返回数据必须统一格式

分页查询的结果必须统一返回结果，标准的返回结果为：

```json
{
    "number": 0,
    "totalPages": 0,
    "totalElements": 0,
    "content": [],
    "size": 0
}
```

- `number` 数字类型，表示当前返回的数据集是第几页
- `totalPages` 数字类型，表示总共有多少页 <div class="badge badge-warning">非必须</div>
- `totalElements` 数字类型，表示总共有多少条数据 <div class="badge badge-warning">非必须</div>
- `content` 集合类型，表示返回的数据集合，不能为null
- `size` 数字类型，表示当前分页的大小 <div class="badge badge-warning">非必须</div>

非必须项需要根据前端的要求来决定是否返回，建议如没有必要就不要去浪费数据库资源去`count`总条数。

同时，可以根据实际的开发需求新增字段。

## 严格遵循 HTTP 状态码表述操作结果

- 2XX 表示前端用户操作成功
- 4XX 表示前端用户操作失败
- 5XX 表示后端代码运行异常

常见的 HTTP 状态码解释如下：

1XX:

- 100 继续
	- 常见于 Websocket 中。
- 101 协议发生切换

2XX:

- 200 请求成功
- 201 创建成功
- 202 请求被接受
- 226 资源被占用

4XX -- 通常作用于业务逻辑错误:

- 400 错误的请求
	- 最常见的地方在于我们后台需要的参数与前端传入的参数数量不符
- 401 没有授权
	- 显而易见，就是需要登录后才能访问。
- 402 需要先付款
	- 顾名思义，需要先付款，才能查看这个资源。
- 403 请求被拒绝
	- 意味着即使你已经登录了，但是没有查看这个资源的权限。
- 404 资源不存在
	- 不解释
- 405 不允许的请求方式
	- 当一个 URL 只允许通过 GET 请求资源，而你采用了其他方式，则返回这个状态。
- 406 无法接受
- 407 需要代理认证
- 408 请求超时
- 409 冲突
- 410 资源曾经存在，但是现在没有了
- 411 拒绝接受没有定义内容长度的请求
- 412 前置条件失败
- 413 服务器负载太大，无法响应
- 414 URI太长
- 415 不支持的媒体类型
- 416 请求范围不符合要求
- 417 希望这次请求失败
	- 测试会用到这个状态
- 418 我是茶壶 —— 与错误的请求类似，例如更新员工的时候你传入的是部门对象
- 422 无法处理提交的实体
	- 常见于后端参数校验。
- 423 资源被锁定
	- 应用场景很多
- 424 依赖失败
- 425 过早的请求
	- 例如延时报表的生成，在还没有生成之前，可以提示用户这个状态码。
- 426 需要升级
- 428 必须要有前置条件
- 429 请求次数太多
	- 最显而易见的应用场景就是防止重复提交
- 431 请求头包含的字段太多
- 451 法律原因禁止访问

5XX -- 通常由网络层面发起的错误

- 500 服务端错误
	- 主要针对后端开发没有捕获到的异常处理
- 501 未实现
	- 主要针对旧版本的 HTTP 协议
- 502 网关错误
- 503 服务不可用
- 504 网关超时
- 505 后台服务不支持该 HTTP 版本的请求
- 511 请求的网络需要先认证

<div class="alert alert-primary">以下规范如无特殊说明，统一使用字符串来返回错误信息！</div>

<div class="alert alert-danger">
5XX的错误通常是网络层面的错误（除了500），因此运维层面在网络发生问题之后，也需要严格遵守标准状态码来进行错误反馈，如果必须要自定义错误状态码，则联系标准制定人在标准文档中体现出来。
</div>

**必须将 400 预留给 Spring MVC 自身的参数校验**

Spring boot 本身也会通过 400 状态码来表述参数缺失，而我们的 `BeanValidator` 则需要通过 422 表述参数校验失败。

**必须使用 401 表示用户未登录**

**必须使用 403 表示用户无权操作该资源**

**必须使用 408 表示请求超时**

**必须使用 404 来表示资源不存在**

<div class="alert alert-success">特殊情况下，可以与前端开发人员特别沟通，用其他状态码表示，例如 410 状态码。</div>

**必须使用 422 表示后端业务上的参数校验失败**

统一异常处理如下（以JSR-303 为例）：

```java
/**
 * 参数校验失败
 *
 * @param e 参数校验失败
 * @return 失败的信息集合
 */
@ExceptionHandler(value = MethodArgumentNotValidException.class)
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public List<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    List<String> errors = new ArrayList<>();
    for (ObjectError error : e.getBindingResult().getAllErrors()) {
        errors.add(error.getDefaultMessage());
    }
    return errors;
}
```

关于422状态码在 HTTP 标准中的解释为：

> The server understands the content type of the request entity (hence a 415 Unsupported Media Type status code is inappropriate), and the syntax of the request entity is correct (thus a 400 Bad Request status code is inappropriate) but was unable to process the contained instructions.

翻译为：

> 服务器端是支持所传递的实体类型的（因此415状态码不符合），并且请求的语法是正确的（因此400状态码不符合），但是无法处理请求的实体内容。

换句话解释就是：

<div class="alert alert-info">
所传递到后台的请求内容（Request Body）是符合实体类数据结构的，但是因为某种原因导致了服务器端无法处理这个请求内容。
</div>

因此，从语义解释上来看，比较适合描述参数校验，例如长度校验、非空校验等。

<div class="alert alert-success">422 返回的错误提示信息必须包装成 <code>List&lt;String&gt;</code>。</div>

> 此处需要与前端进一步沟通，因为包装成 List 可能不能很好的与 input 标签对应起来。

**其他类型的错误信息尽量在标准的 HTTP 状态码中找到对应的错误**

<div class="alert alert-success">尽量用 HTTP 标准状态码来描述你的错误提示，无法归类的统一使用 406 状态码返回错误提示。</div>

**必须使用 500 来描述意外的错误**

所谓意外的错误，就是开发过程中没有考虑到的异常，例如 `NullPointerException`。


## 无论前端是否做了参数校验，后端必须要做参数校验！

无论前端利用何种手段进行了参数校验，后端必须要写参数校验，我们可以利用 JSR-303 来简化我们的校验，例如下面的例子：

```java
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Alias("user")
public class User implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    @Length(max = 45, message = "用户名长度不能超过45个字符")
    private String username;

    /**
     * 姓名
     */
    @Length(max = 45, message = "姓名不能超过45个字符")
    private String name;

    /**
     * 密码
     */
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 6, max = 16, message = "密码必须大于6位且小于16位。")
    private String password;

    /**
     * 邮箱
     */
    @Length(max = 45, message = "邮箱不能超过45位")
    private String email;

    /**
     * 头像地址
     */
    private String avatar;

}
```

通过加入 JSR-303 的校验，我们就可以轻松的通过 @Valid 注解来对传入的对象进行参数校验：

```java
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @PostMapping
    @NoAuthentication
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@Valid @RequestBody User user) {
        if (StringUtils.isEmpty(user.getName())) {
            user.setName(user.getUsername());
        }
        registerService.reg(user);
    }
}
```

同时，我们可以通过统一异常处理来返回给前端错误信息：

```java
/**
 * 参数校验失败
 *
 * @param e 参数校验失败
 * @return 失败的信息集合
 */
@ExceptionHandler(value = MethodArgumentNotValidException.class)
@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public List<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    List<String> errors = new ArrayList<>();
    for (ObjectError error : e.getBindingResult().getAllErrors()) {
        errors.add(error.getDefaultMessage());
    }
    return errors;
}
```



