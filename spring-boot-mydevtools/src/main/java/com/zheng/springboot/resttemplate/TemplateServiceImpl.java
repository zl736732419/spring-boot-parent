package com.zheng.springboot.resttemplate;

import com.zheng.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @Author zhenglian
 * @Date 2018/5/21 22:39
 */
@Service
public class TemplateServiceImpl implements TemplateService {
    
    private RestTemplate restTemplate;

    @Value("${rest.template.user.url}")
    private String userUrl;
    
    @Autowired
    public TemplateServiceImpl(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    } 
    
    @Override
    public User getUser(Integer userId) {
        String url = userUrl.replaceAll("\\{userId\\}", userId + "");
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
}
