package com.yanchong.springcloud.service;

import com.yanchong.springcloud.entities.Payment;


public interface PaymentService {

    public int create(Payment payment);

    public Payment getById(int id);

    void testBatchInsert();
}
