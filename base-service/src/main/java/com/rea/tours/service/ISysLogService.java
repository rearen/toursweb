package com.rea.tours.service;

import com.rea.tours.domain.SysLog;

import java.util.List;

public interface ISysLogService
{

    List<SysLog> findAll();

    void save(SysLog sysLog);
}
