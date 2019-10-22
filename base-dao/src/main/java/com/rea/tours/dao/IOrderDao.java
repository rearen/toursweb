package com.rea.tours.dao;

import com.rea.tours.domain.Orders;
import com.rea.tours.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;


public interface IOrderDao {

    @Select({
            "select * from orders"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="orderNum", property="orderNum", jdbcType=JdbcType.VARCHAR),
            @Result(column="orderTime", property="orderTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="peopleCount", property="peopleCount", jdbcType=JdbcType.INTEGER),
            @Result(column="orderDesc", property="orderDesc", jdbcType=JdbcType.VARCHAR),
            @Result(column="payType", property="payType", jdbcType=JdbcType.INTEGER),
            @Result(column="orderStatus", property="orderStatus", jdbcType=JdbcType.INTEGER),
//            @Result(column="productID", property="productID", jdbcType=JdbcType.VARCHAR),
//            @Result(column="memberid", property="memberid", jdbcType=JdbcType.VARCHAR),
            @Result(property = "product",column = "ProductID",javaType = Product.class,one=@One(select = "com.rea" +
                    ".tours.dao.IProductDao.findById")),
    })
    List<Orders> findAll();

    @Select({
        "select * from orders where id = #{ordersId}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="orderNum", property="orderNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="orderTime", property="orderTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="peopleCount", property="peopleCount", jdbcType=JdbcType.INTEGER),
        @Result(column="orderDesc", property="orderDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="payType", property="payType", jdbcType=JdbcType.INTEGER),
        @Result(column="orderStatus", property="orderStatus", jdbcType=JdbcType.INTEGER),
//        @Result(column="productID", property="productID", jdbcType=JdbcType.VARCHAR),
//        @Result(column="memberid", property="memberid", jdbcType=JdbcType.VARCHAR),
        @Result(property = "product",column = "ProductID",javaType = Product.class,one=@One(select = "com.rea" +
                ".tours.dao.IProductDao.findById")),
//        @Result(column = "id",property = "travellers",javaType = java.util.List.class,many = @Many(select =
//                "com.rea.tours.dao.ITravellerDao.findByOrdersId")),
//        @Result(column = "memberId",property = "member",javaType = Member.class,one = @One(select =
//                "com.rea.tours.dao.IMemberDao.findById"))
        @Result(column = "id",property = "travellers",many = @Many(select =
                "com.rea.tours.dao.ITravellerDao.findByOrdersId")),
        @Result(column = "memberid",property = "member",one = @One(select =
                "com.rea.tours.dao.IMemberDao.findById"))
    })
    public Orders findById(String id);

}