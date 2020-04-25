package com.yanchong.springcloud.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.yanchong.springcloud.entities.CommonResult;
import com.yanchong.springcloud.entities.Payment;
import com.yanchong.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @Autowired
    PaymentService paymentService;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        //Payment payment = new Payment(1,"ddd");
        log.info("ddfdfdf");

        int i = paymentService.create(payment);
        return new CommonResult<>(200,"成功=====" + port,i);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getById(@PathVariable("id") int id){
        log.info("ddfdddddddddddddddddddddddddfdf");

        Payment payment = paymentService.getById(id);
        return new CommonResult<>(200,"成功=+++++++++++====" + port,payment);
    }


    @GetMapping("/payment/getTimeOut")
    public String getTimeOut()
    {

        try {
            TimeUnit.SECONDS.sleep(3);  //程序暂停3秒钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  port;
    }
}
