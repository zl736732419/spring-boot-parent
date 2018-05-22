##### jcache ehcache3x
需要引入依赖
```
<dependency>
  <groupId>org.ehcache</groupId>
  <artifactId>ehcache</artifactId>
  <version>3.1.3</version>
</dependency>
<!-- JSR107 API -->
<dependency>
  <groupId>javax.cache</groupId>
  <artifactId>cache-api</artifactId>
</dependency>
```
指定配置文件路径
spring.cache.jcache.config=classpath:cache/ehcache3x.xml
在使用jcache ehcache时，缓存注解需要引用jsr定义的而不是spring提供的,具体参考CacheService
```
JSR-107 (JCache)标准已经定义了缓存的标准标注，标准标注与Spring框架的缓存标注的对应关系如下：
@CacheResult，类似于Spring的@Cacheable
@CachePut，类似于Spring的@CachePut
@CacheRemove，类似于Spring的@CacheEvict
@CacheRemoveAll，类似于Spring的@CacheEvict(allEntries=true)
@CacheDefaults，类似于Spring的@CacheConfig
@CacheKey
@CacheValue
```