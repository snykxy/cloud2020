package com.yanchong.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
@DefaultProperties(defaultFallback = "globalFullBack")      //指定全局的降级方法
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
    @HystrixCommand //采用服务降级，这里没有指定降级的方法，会采用类上标注的“@DefaultProperties(defaultFallback = "globalFullBack")”找到全局的降级方法
    public CommonResult getById(@PathVariable("id") int id){
        log.info("ddfdddddddddddddddddddddddddfdf");

        //int age = 10/0;


        Payment payment = paymentService.getById(id);
        return new CommonResult<>(200,"成功=+++++++++++====" + port,payment);
    }


    @GetMapping("/payment/getTimeOut")
    @HystrixCommand(fallbackMethod = "timeOutHandler",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value="3000")
    })
    public String getTimeOut()
    {

//        log.info("10/0.....begin");     //这个会被打印
//        int age = 10/0;
//        log.info("10/0.....end");       //这个不会被打印


        try {
            TimeUnit.SECONDS.sleep(2);  //程序暂停3秒钟
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  port;
    }


    public String timeOutHandler(){
        return "超时了。。。。。。";
    }

    //服务降级，不可能给每个方法都配置一个降级方法，不指定的话 ，可以在类上加上“@DefaultProperties(defaultFallback = "globalFullBack")”，
    //然后只要有降级的，都会走到这个方法上
    //全局的兜底降级方法，需要说明的是，如果采用了服务降级，那么原方法的返回参数应该和这个一样
    public CommonResult globalFullBack(){
        return new CommonResult(200,"全局的兜底降级方法");
    }




    //服务熔断
    @GetMapping("/payment/rongduan/{id}")
    @HystrixCommand(fallbackMethod = "rongduanExceptionHandler",
            commandProperties = {
                @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),    //是否开启熔断器
                @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  //请求次数
                @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    //时间窗口期
                @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),    //失败率达到多少后跳闸

    })
    /**
     * 三个参数的一些说明
     * 1.快照时间窗:断路器确定是否要打开需要统计一些请求和错误数据,而统计的时间范围就是快照时间窗,默认为最近的10秒
     * 2.请求总数阈值:在快照时间窗内,必须满足请求总数阈值才有资格熔断,默认为20,意味着在10秒内,如果该hystrix命令的调用次数不足20次
     *      即使所有的请求都超时或者其他原因失败,断路器都不会打开
     * 3.错误百分比阈值:当请求总数在快照时间窗口内超过了阈值,比如发生了30次调用,如果在这30次调用中,有15次发生了超时异常,
     *  也就是超过了50%的错误百分比,在默认设定50%阈值情况下,这个时候就会将断路器打开
     *
     *  断路器断开和恢复的一些说明:
     *  当满足一定的阈值和失败率的时候,断路器会开启,一旦断路器开启,所有的请求都会走到fullback方法中
     *  一段时间后(默认是5秒钟),这个时候断路器会是半开状态,会让其中一个请求进行转发(走到正常的方法中),
     *  如果成功,断路器会关闭,若失败,断路器继续保持开启状态,重复以上步骤...
     */
    public String rongduan(@PathVariable("id") int id){
        if(id<0){
            throw new RuntimeException("小于0,报错了....");
        }

        return "熔断正常提供服务...........";
    }

    public String rongduanExceptionHandler(@PathVariable("id") int id){
        return "*****熔断了,失败了." + id;
    }

}
