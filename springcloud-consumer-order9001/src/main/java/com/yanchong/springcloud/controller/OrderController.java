package com.yanchong.springcloud.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yanchong.springcloud.entities.CommonResult;
import com.yanchong.springcloud.entities.Payment;
import com.yanchong.springcloud.service.FeignPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {


    @Autowired
    FeignPaymentService feignPaymentService;


    private static final String PAYMENT_URL="http://springcloud-provider-payment";
    //private static final String PAYMENT_URL="http://localhost:8001";

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/order/get/{id}")
    public CommonResult getOrder(@PathVariable("id") int id){

        log.info("我要去调用payment啦。。。。。");

        //return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);      //使用restTemplate调用

        return feignPaymentService.getById(id); //使用feign调用（推荐）
    }

    @PostMapping("/order/create")
    public CommonResult create(Payment payment){
        log.info("order 接受的参数payment  ：：：：： " + payment);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/order/getTimeOut")
    @HystrixCommand(fallbackMethod = "timeOutHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="6500")
            })
    public String getTimeOut()
    {
        return feignPaymentService.getTimeOut();
    }


    public String timeOutHandler(){
        return "服务调用方超时间。。。。。。。。了";
    }
}
