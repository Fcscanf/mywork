package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDAO;
import com.model.User;

@Service("userService")
public class UserServiceImp implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public boolean exits(String username){
		List<User> userList = userDAO.findByUsername(username);
		if(userList.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<User> queryUsers(String username){
		if(username == null || "".equals(username)) {
			return userDAO.findAllUsers();
		} else {
			return userDAO.queryByUsername(username);
		}
	}
	
	@Override
	 public User getUser(Integer id){
			return userDAO.getUser(id);
	}
	
	@Override
	@Transactional
	public void save(User user){
		userDAO.save(user);
	}
	
	@Override
	@Transactional
	public void modifyUser(User user){
		
	}
	
	@Override
	@Transactional
	public void deleteUser(Integer id){
		
	}

	@Override
	public boolean haveUser(String username, String password) {
		List<User> users = userDAO.findByUsername(username);
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			System.out.println(user);
			if (user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
}
