package com.service;
import java.util.List;

import com.model.User;

public interface UserService {
	/**
	 * 判断用户是否存在
	 */
	public boolean exits(String username);
	/**
	 * 保存用户
	 */
	public void save(User user);
	/**
	 * 修改用户
	 */
	public void modifyUser(User user);
	/**
	 * 删除用户
	 */
	public void deleteUser(Integer id);
	/**
	 * 获取指定id用户
	 */
	public User getUser(Integer id);
	/**
	 * 获取用户列表
	 */
	public List<User> queryUsers(String username);

	public boolean haveUser(String username, String password);

	public List<User> queryUserByRegistTime(String startTime, String endTime);

    public boolean isBlackUser(String username);

    /**
     * 检查登录的是否为锁定的用户，如果是通过当前登录时间判断超过锁定时间将为该用户解锁
     *
     * @param username
     * @param loginTime
     * @return
     * @author Fcscanf
     * @date 上午 8:16 2019-01-09/0009
     */
	public boolean checkLoginLockUser(String username, long loginTime);

	/**
	 * 记录用户登录次数，如果大于五次就锁定用户
	 *
	 * @param
	 * @return
	 * @author Fcscanf
	 * @date 上午 8:58 2019-01-09/0009
	 */
	public boolean lockUser(String username, long loginTime);
}
