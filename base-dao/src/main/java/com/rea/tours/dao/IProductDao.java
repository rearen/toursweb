package com.rea.tours.dao;

import com.rea.toursweb.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface IProductDao
{
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    @Insert("insert into product(id,productNum,productName,cityName," +
            "departureTime,productPrice,productDesc,productStatus) " +
            "values(replace(uuid(),'-',''),#{productNum},#{productName},#{cityName}," +
            "#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
