package com.zheng.springboot.runner;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author zhenglian
 * @Date 2018/5/15 9:21
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunner");
        System.out.println("sourceArgs: ");
        List<String> values;
        String[] sourceArgs = args.getSourceArgs();
        for (String name : sourceArgs) {
            values = args.getOptionValues(name);
            System.out.println(name+":"+ StringUtils.join(values));
        }
    }
}
