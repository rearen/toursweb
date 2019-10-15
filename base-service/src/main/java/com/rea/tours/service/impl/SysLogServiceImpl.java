package com.rea.tours.service.impl;

import com.rea.tours.dao.ISysLogDao;
import com.rea.tours.domain.SysLog;
import com.rea.tours.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService
{
    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public List<SysLog> findAll()
    {
        return sysLogDao.findAll();
    }

    @Override
    public void save(SysLog sysLog)
    {
        sysLogDao.save(sysLog);
    }
}
