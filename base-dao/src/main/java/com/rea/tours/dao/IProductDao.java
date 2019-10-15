package com.rea.tours.dao;

import com.rea.tours.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//@Repository
public interface IProductDao
{
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    //根据id查询产品
    @Select("select * from product where id=#{id}")
    public Product findById(String id) throws Exception;

    @Insert("insert into product(productNum,productName,cityName," +
            "departureTime,productPrice,productDesc,productStatus) " +
            "values(replace(uuid(),'-',''),#{productNum},#{productName},#{cityName}," +
            "#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
