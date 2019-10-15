package com.rea.tours.dao;

import com.rea.tours.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ISysLogDao
{
    @Select("select * from syslog")
    public List<SysLog> findAll();

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) " +
            "values (#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    public void save(SysLog sysLog);
}
