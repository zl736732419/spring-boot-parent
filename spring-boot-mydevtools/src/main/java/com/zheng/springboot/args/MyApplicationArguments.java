package com.zheng.springboot.args;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 获取SpringApplication.run(args)中的args
 * @Author zhenglian
 * @Date 2018/5/14 17:44
 */
@Component
public class MyApplicationArguments {
    @Autowired
    public MyApplicationArguments(ApplicationArguments args) {
        System.out.println("applicationArguments:");
        Set<String> optionNames = args.getOptionNames();
        System.out.println("optionNames: ");
        List<String> values;
        for (String name : optionNames) {
            values = args.getOptionValues(name);
            System.out.println(name+":"+ StringUtils.join(values));
        }
        System.out.println("nonOptionArgs: ");
        List<String> nonOptionArgs = args.getNonOptionArgs();
        for (String name : nonOptionArgs) {
            values = args.getOptionValues(name);
            System.out.println(name+":"+ StringUtils.join(values));
        }
        System.out.println("hello");
        System.out.println("sourceArgs: ");
        String[] sourceArgs = args.getSourceArgs();
        for (String name : sourceArgs) {
            values = args.getOptionValues(name);
            System.out.println(name+":"+ StringUtils.join(values));
        }

    }
}
