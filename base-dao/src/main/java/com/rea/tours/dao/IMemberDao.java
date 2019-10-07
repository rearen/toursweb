package com.rea.tours.dao;

import com.rea.tours.domain.Member;
import org.apache.ibatis.annotations.Select;


public interface IMemberDao {

    @Select("select * from `member` where id=#{id}")
    public Member findById(String id) throws Exception;
}