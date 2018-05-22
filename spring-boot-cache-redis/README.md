##### redis cache
使用redis cache需要借助前面与redis的整合，这里直接采用的redisTemplate来获取redis连接
需要注册RedisCacheManager

注意，在使用缓存注解时，方法只会操作方法执行的结果，比如如果我们在int UserService.save(User user)方法上添加了
@CachePut(value="user", key="#user.id"),最终缓存的只会是(userId, changeRecordCount)
所以在使用findById(userId)进行查找时就会报错：int can not cast to User type
