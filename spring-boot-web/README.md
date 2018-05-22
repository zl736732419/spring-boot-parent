```
静态资源配置默认在classpath: /static, /public, /resources, /META-INF/resources目录下
访问这些目录的资源不会被转发到具体的controller，而是直接返回对应的请求资源
默认的请求路径是/**,可以通过spring.mvc.static-path-pattern对请求的路径添加前缀
```

#### webjars
为了更够更加方便的管理前端插件的版本，springboot提供了webjars的支持，可以在webjar官网：http://www.webjars.org/
查找前端需要的js插件，比如bootstrap,jquery等，然后页面直接访问/webjars目录下的js即可，具体可以参考demo.html
需要引入webjars-locator依赖，这样就可以不用指定webjar的版本目录