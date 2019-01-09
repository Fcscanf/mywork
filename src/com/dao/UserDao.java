package com.dao;

import java.util.List;

import javax.annotation.Resource;

import com.model.Blackuser;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.model.User;

@Component
public class UserDao {
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public void save(User user){
		hibernateTemplate.save(user);
	}
	public void update(User user){
        hibernateTemplate.update(user);
	}
	public void delete(Integer id){
		
	}
	public User getUser(Integer id){
		return (User)this.hibernateTemplate.load(User.class,id);
	}
	public List<User> findByUsername(String name){
		return (List<User>) hibernateTemplate.find("from User u where u.name = ?",name);
	}
	public List<User> queryByUsername(String name){
		return (List<User>) hibernateTemplate.find("from User u where u.name like ?","%"+name+"%");
	}
	
	public List<User> findAllUsers(){
		 return this.getHibernateTemplate().find("from User order by id");  
	}

	/**
	 * 查询黑名单用户
	 *
	 * @param
	 * @return
	 * @author Fcscanf
	 * @date 上午 8:29 2019-01-05/0005
	 */
	public List<Blackuser> checkBlackUser() {
		return (List<Blackuser>) hibernateTemplate.find("from Blackuser order by id");
	}

}
