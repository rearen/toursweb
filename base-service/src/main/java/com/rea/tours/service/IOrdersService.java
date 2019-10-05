package com.rea.tours.service;

import com.rea.tours.domain.Orders;

import java.util.List;

public interface IOrdersService
{
    List<Orders> findAll() throws Exception;

    List<Orders> findAllByPage(int page,int pageSize) throws Exception;

    public Orders findById(String id) throws Exception;
}
