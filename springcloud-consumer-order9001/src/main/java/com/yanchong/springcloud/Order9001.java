package com.yanchong.springcloud;

import com.yanchong.ribbonRule.RuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "springcloud-provider-payment",configuration = RuleConfig.class)     //加载自定义的配置类，更改默认负载均衡规则
@EnableFeignClients     //开启feign客户端功能
@EnableHystrix
public class Order9001 {

    public static void main(String[] args) {
        SpringApplication.run(Order9001.class,args);
    }
}
