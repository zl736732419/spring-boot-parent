package com.zheng.springboot.service.impl;

import com.zheng.springboot.service.MathService;
import org.springframework.stereotype.Service;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import java.util.Objects;
import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/5/18 16:10
 */
@CacheDefaults(cacheName = "piDecimals")
@Service
public class MathServiceImpl implements MathService {
    @CacheResult
    @Override
    public Integer computePiDecimal(Integer num) {
        if (!Optional.ofNullable(num).isPresent() || Objects.equals(num, 0)) {
            return null;
        }
        int result = num / 360;
        return result;
    }
}
