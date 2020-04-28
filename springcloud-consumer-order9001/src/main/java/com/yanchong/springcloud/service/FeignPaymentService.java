package com.yanchong.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yanchong.springcloud.entities.CommonResult;
import com.yanchong.springcloud.entities.Payment;
import com.yanchong.springcloud.service.impl.FeignPaymentServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Component
@FeignClient(value = "springcloud-provider-payment",
        fallback = FeignPaymentServiceFallBackImpl.class//fallback = FeignPaymentServiceFallBackImpl.class指定异常处理类，
        // 如果通过feign调用服务提供方出现异常，这里会找到异常处理类的对应方法
)
public interface FeignPaymentService {

    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getById(@PathVariable("id") int id);


    @GetMapping("/payment/getTimeOut")
    String getTimeOut();

    //========服务熔断
    @GetMapping("/payment/rongduan/{id}")
    public String rongduan(@PathVariable("id") int id);

}
