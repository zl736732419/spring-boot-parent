要使devtools有效，需要满足几条规则：
```
1. 不能禁用shutdown hook
SpringApplication.setRegisterShutdownHook(true);
2. 项目名称不能是下面几个之一
spring-boot, spring-boot-devtools, spring-boot-autoconfigure, spring-boot-actuator, and spring-boot-starter.
```

##### Restart vs Reload
restart采用两种classloader: base classloader用于加载那些不会改变的class，比如第三方jar;restart classloader用于加载用户修改的class
比如工程项目中的类。当进行restart的时候，restart classloader会被丢弃掉并重新加载一个新的restart classloader,base classloader不会有变化
这样每次restart的时候不会对base classloader做处理，所以更加快速

##### devtools结合livereload实现浏览器页面自动刷新
需要浏览器安装livereload插件，springboot内嵌livereload服务器，当浏览器通过livereload插件客户端连接上服务器之后，当服务器重新构建，
页面会触发自动更新