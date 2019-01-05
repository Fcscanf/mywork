package com.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.model.Blackuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.model.User;

@Service("userService")
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao userDao;

	private static Map<String, User> USER_MAP = new HashMap<>();

	private static HashSet<String> BLACKLIST_SET = null;
	
	@Override
	public boolean exits(String username){
		List<User> userList = userDao.findByUsername(username);
		if(userList.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public List<User> queryUsers(String username){
		if(username == null || "".equals(username)) {
			return userDao.findAllUsers();
		} else {
			return userDao.queryByUsername(username);
		}
	}
	
	@Override
	 public User getUser(Integer id){
			return userDao.getUser(id);
	}
	
	@Override
	@Transactional
	public void save(User user){
		userDao.save(user);
	}
	
	@Override
	@Transactional
	public void modifyUser(User user){
		
	}
	
	@Override
	@Transactional
	public void deleteUser(Integer id){
		
	}

	/**
	 * 判断该用户的密码是否正确
	 *
	 * @param username
	 * @param password
	 * @return false
	 * @author Fcscanf
	 * @date 上午 9:45 2019-01-05/0005
	 */
	@Override
	public boolean haveUser(String username, String password) {
		List<User> users = userDao.findByUsername(username);
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			if (user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断用户是否为黑名单用户，是则将错误信息返回到页面 
	 *
	 * @param username
	 * @return
	 * @author Fcscanf
	 * @date 上午 8:35 2019-01-05/0005 
	 */
	@Override
	public boolean isBlackUser(String username) {
		List<Blackuser> blackusers = userDao.checkBlackUser();
		for (int i = 0; i < blackusers.size(); i++) {
			Blackuser blackuser = blackusers.get(i);
			BLACKLIST_SET = new HashSet<>();
			BLACKLIST_SET.add(blackuser.getUserName());
			if (blackuser.getUserName().equals(username)) {
				return true;
			}
		}
//		if (BLACKLIST_SET == null) {
//			System.out.println("黑名单未初始化");
//			BLACKLIST_SET = new HashSet<>();
//			List<Blackuser> blackUsers = userDao.checkBlackUser();
//			for (Blackuser blackuser : blackUsers) {
//				BLACKLIST_SET.add(blackuser.getUserName());
//			}
//		}else{
//			System.out.println("已经初始化");
//		}
		return false;
	}
}
