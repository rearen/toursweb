package com.rea.tours.service.impl;

import com.rea.tours.dao.IProductDao;
import com.rea.tours.service.IProductService;
import com.rea.toursweb.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService
{
    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll() throws Exception{
        System.out.println("业务层：查询所有记录。。");
        return productDao.findAll();
    }

    @Override
    public void save(Product product)
    {
        productDao.save(product);
    }
}
