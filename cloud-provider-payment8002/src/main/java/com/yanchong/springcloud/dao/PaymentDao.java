package com.yanchong.springcloud.dao;

import com.yanchong.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PaymentDao {

    public int create(Payment payment);

    public Payment getById(int id);

    void testBatchInsert(@Param("paymentList") List<Payment> paymentList);
}
