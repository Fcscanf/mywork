package com.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.deploy.net.HttpResponse;
import com.util.AESUtilFinal;
import com.util.SystemConfig;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;

@Component("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private UserService userService;

    private User user;

    private String searchText;

    private List<User> users;

    private HttpServletRequest request;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public UserService getUserService() {
        return userService;
    }
    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String addUser(){

        /**
         * 年龄判断
         */
        SystemConfig systemConfig = SystemConfig.getInstance();
        systemConfig.init();
        int minage = SystemConfig.getInteger("minage");
        int maxage = SystemConfig.getInteger("maxage");

        /**
         * 密码的明文加密
         */
        String secretKey = SystemConfig.get("secretKey");
        String password = user.getPassword();
        System.out.println(password);
        String encrypt = AESUtilFinal.encrypt(secretKey, password);
        System.out.println(encrypt);
        user.setPassword(encrypt);

        if(userService.exits(String.valueOf(user.getAge()))&&minage>15&&maxage<35){
            return ERROR;
        }
        userService.save(user);
        return SUCCESS;
    }

    public String queryUser(){
        searchText = getParam("queryText");
        users = userService.queryUsers(searchText);
        return SUCCESS;
    }

    public String editUser(){
        return SUCCESS;
    }

    public String deleteUser(){
        return SUCCESS;
    }
    public String getSearchText() {
        return searchText;
    }
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
    protected String getParam(String key){
        return ServletActionContext.getRequest().getParameter(key);
    }
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String loginUser() {
        String userName = user.getName();
        String password = user.getPassword();
        System.out.println(password);

        SystemConfig systemConfig = SystemConfig.getInstance();
        systemConfig.init();
        String secretKey = SystemConfig.get("secretKey");
        String encrypt = AESUtilFinal.encrypt(secretKey, password);
        System.out.println(encrypt);

        if (userService.haveUser(userName, encrypt)){
            return SUCCESS;
        }
        request = ServletActionContext.getRequest();
        request.setAttribute("msg","用户名或密码错误！");
        return ERROR;
    }
}
