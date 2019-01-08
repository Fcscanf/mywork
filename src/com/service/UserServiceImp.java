package com.service;

import java.util.*;

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
	 * 查询在某个时间段注册的用户 
	 *
	 * @param
	 * @return 
	 * @author Fcscanf
	 * @date 上午 8:57 2019-01-08/0008 
	 */
	@Override
	public List<User> queryUserByRegistTime(String startTime, String endTime) {
		List<User> allUsers = userDao.findAllUsers();
		List<User> resultUsers = new ArrayList<>();
        long startDate = dateToLong(startTime);
		long endDate = dateToLong(endTime);
        for (User user : allUsers) {
            long registDate = dateToLong(user.getRegistDate());
            if (registDate >= startDate && registDate <= endDate) {
                resultUsers.add(user);
            }
        }
		return resultUsers;
	}

    public static long dateToLong(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String dairy = date.substring(8, 10);
        String hour = date.substring(11, 13);
        String minute = date.substring(14, 16);
        String second = date.substring(17, 19);
        long i = Long.parseLong(year + month + dairy + hour + minute + second);
        return i;
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
