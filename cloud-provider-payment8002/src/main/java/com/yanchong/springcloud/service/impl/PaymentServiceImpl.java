package com.yanchong.springcloud.service.impl;

import com.yanchong.springcloud.dao.PaymentDao;
import com.yanchong.springcloud.entities.Payment;
import com.yanchong.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {


    @Resource
    PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getById(int id) {
        return paymentDao.getById(id);
    }

    @Override
    public void testBatchInsert() {

        List<Payment> list =  new ArrayList<>();
        list.add(new Payment(10001,"p1"));
        list.add(new Payment(10002,"p2"));
        list.add(new Payment(10003,"p3"));
        list.add(new Payment(10004,"p4"));
        list.add(new Payment(10005,"p5"));

        paymentDao.testBatchInsert(list);
    }
}
