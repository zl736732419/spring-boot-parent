#### 自定义实现错误处理机制的几种方式
##### 1.通过ControllerAdvice将错误信息包装成错误对象，通过json格式返回到前端
可以通过与@ExceptionHandler结合对指定的Controller下面的指定错误进行处理，同时也可以不用指定Controller和具体的错误类型
全局处理所有发生的错误
##### 2.我们可以针对不同状态码，定制不同的错误页面，这些页面必须以状态码命名。可以是html文件或者模板
html文件目录：
```
src/
 +- main/
     +- java/
     |   + <source code>
     +- resources/
         +- public/
             +- error/
             |   +- 404.html
             +- <other public assets>
```
模板文件目录：
```
src/
 +- main/
     +- java/
     |   + <source code>
     +- resources/
         +- templates/
             +- error/
             |   +- 5xx.ftl
             +- <other templates>
```
##### 3.通过MyErrorViewResolver处理复杂的错误映射逻辑，根据status和Request跳转到不同的错误页面
##### 4.通过扩展BasicErrorController，目前springboot默认的错误处理机制
对于text/html类型的请求，返回whitelabel错误页面(位于ErrorMvcAutoConfiguration)，对于其他类型请求返回json错误信息
##### 5.通过ErrorPageRegistrar实现，主要是用于非springmvc环境，通过filter来进行错误处理
