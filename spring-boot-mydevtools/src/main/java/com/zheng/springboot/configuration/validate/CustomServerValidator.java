package com.zheng.springboot.configuration.validate;

import com.zheng.springboot.configuration.yaml.Server;
import org.springframework.lang.Nullable;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/5/15 17:58
 */
public class CustomServerValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == Server.class;
    }

    @Override
    public void validate(@Nullable Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmpty(errors, "url", "url.empty");
        Server server = (Server) target;
        if (!Optional.ofNullable(server).isPresent()) {
            return;
        }
        String url = server.getUrl();
        if (!Optional.ofNullable(url).isPresent() || url.indexOf("http") != 0) {
            errors.rejectValue("url", "invalid url");
        }
    }
}
