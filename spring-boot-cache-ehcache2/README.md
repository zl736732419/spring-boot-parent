##### ehcache2x
需要在classpath下提供ehcache.xml配置文件，springboot将会使用EhCacheCacheManager对缓存进行管理
需要添加net.sf.ehcache依赖
```
Spring框架的缓存标注：
@Cacheable("a_cache_name")，作用于被缓冲的方法，对方法执行结果的缓存
@CacheEvict，作用于被缓冲的方法，将方法执行的结果从缓存中移除
@CachePut，更新缓存
@Caching，将作用于一个方法的多个缓存操作打包为一个整体
@CacheConfig，作用于Java类，设置通用的缓存相关参数
```