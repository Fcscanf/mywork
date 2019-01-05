package com.service;

import com.dao.LogDao;
import com.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * LogServiceImpl
 *
 * @author Fcscanf
 * @description
 * @date 下午 16:00 2019-01-05/0005
 */
@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void logWrite(Log log) {
        logDao.logWrite(log);
    }
}
