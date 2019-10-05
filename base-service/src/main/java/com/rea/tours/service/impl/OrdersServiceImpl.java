package com.rea.tours.service.impl;

import com.github.pagehelper.PageHelper;
import com.rea.tours.dao.IOrderDao;
import com.rea.tours.domain.Orders;
import com.rea.tours.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService
{
    @Autowired
    public IOrderDao orderDao;

    @Override
    public List<Orders> findAll(){
        return orderDao.findAll();
    }

    @Override
    public List<Orders> findAllByPage(int page,int pageSize) throws Exception{
        //参数page 是页码值   参数pageSize 代表是每页显示条数
        PageHelper.startPage(page,pageSize);
        return orderDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) throws Exception{
        System.out.println("order业务层：查询所有记录。。");
        return orderDao.findById(ordersId);
    }

}
