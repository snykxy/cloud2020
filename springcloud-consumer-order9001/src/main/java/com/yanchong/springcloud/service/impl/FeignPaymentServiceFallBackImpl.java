package com.yanchong.springcloud.service.impl;

import com.yanchong.springcloud.entities.CommonResult;
import com.yanchong.springcloud.entities.Payment;
import com.yanchong.springcloud.service.FeignPaymentService;
import org.springframework.stereotype.Component;

/**
 *
 * 这个类作为FeignPaymentService的实现类，正常的业务逻辑已经在接口中通过注解实现了。。。
 * 如果不能正常调用，接口中的 @FeignClient(value = "springcloud-provider-payment",fallback = FeignPaymentServiceFallBackImpl.class)
 * fallback = FeignPaymentServiceFallBackImpl.class 指定了本实现类为统一的异常处理类，当调用feign出现异常，就会调用到本实现类中的对应方法
 *
 */
@Component
public class FeignPaymentServiceFallBackImpl implements FeignPaymentService {

    @Override
    public CommonResult<Payment> getById(int id) {
        return new CommonResult(200,"调用不到服务提供方，这里采用fullback进行公共处理，方法名称：getById",null);
    }

    @Override
    public String getTimeOut() {
        return "调用不到服务提供方，这里采用fullback进行公共处理,方法名称：getTimeOut";
    }

    @Override
    public String rongduan(int id) {
        return "调用不到服务提供方，这里采用fullback进行公共处理,方法名称：rongduan";
    }
}
