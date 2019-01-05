package com.dao;

import com.model.Log;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * LogDao
 *
 * @author Fcscanf
 * @description
 * @date 下午 15:59 2019-01-05/0005
 */
@Component
public class LogDao {

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void logWrite(Log log) {
        hibernateTemplate.save(log);
    }
}
