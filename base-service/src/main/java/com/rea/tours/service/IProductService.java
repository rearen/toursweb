package com.rea.tours.service;

import com.rea.tours.domain.Product;

import java.util.List;

public interface IProductService
{
    public List<Product> findAll() throws Exception;

    public void save(Product product);
}
