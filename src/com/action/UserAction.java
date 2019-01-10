package com.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.model.Log;
import com.service.LogService;
import com.service.UserServiceImp;
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
public class UserAction extends ActionSupport {

    private static final long serialVersionUID = 1L;
    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    private User user;

    private Log log;

    private String searchText;

    private List<User> users;

    private HttpServletRequest request;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentime = dateFormat.format(new Date());

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

    public String addUser() {
        request = ServletActionContext.getRequest();
        String password = user.getPassword();
        /**
         * 年龄判断
         */
        SystemConfig systemConfig = SystemConfig.getInstance();
        systemConfig.init();
        int minage = SystemConfig.getInteger("minage");
        int maxage = SystemConfig.getInteger("maxage");

        if (userService.exits(user.getName())) {
            request.setAttribute("user",user);
            request.setAttribute("nameMsg", "该用户名已存在！");
            return ERROR;
        } else if (!isEngDig(user.getPassword())) {
            request.setAttribute("user",user);
            request.setAttribute("passMsg", "密码不是英文和数字的组合且密码长度至少6不能多于20位！");
            return ERROR;
        } else if (isPhone(user.getPhone())) {
            request.setAttribute("user", user);
            request.setAttribute("phoneMsg", "手机号格式不正确！");
            return ERROR;
        } else if (userService.checkPhone(user.getPhone())) {
            request.setAttribute("user", user);
            request.setAttribute("phoneMsg", "当前手机号已被注册！");
            return ERROR;
        } else if (!(user.getAge() > minage && user.getAge() < maxage)) {
            request.setAttribute("user", user);
            request.setAttribute("ageMsg", "用户年龄大小不符合要求！");
            return ERROR;
        }

        /**
         * 密码的明文加密
         */
        String secretKey = SystemConfig.get("secretKey");
        String encrypt = AESUtilFinal.encrypt(secretKey, password);
        System.out.println(encrypt);
        user.setPassword(encrypt);
        user.setRegistDate(currentime);

        userService.save(user);
        log = new Log();
        log.setUserName(user.getName());
        log.setLoginTime(currentime);
        log.setLogIp(getIpAddr(request));
        log.setLog("用户注册");
        logService.logWrite(log);
        return SUCCESS;
    }

    public String queryUser() {
        searchText = getParam("queryText");
        users = userService.queryUsers(searchText);
        return SUCCESS;
    }

    public String editUser() {
        try {
            Integer param = Integer.parseInt(getParam("param"));
            if(param == 0){
                Integer id = Integer.parseInt(getParam("id"));
                user = userService.getUser( id);
                return "editUser";
            }else if(param == 1){
                userService.modifyUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String deleteUser() {
        try {
            Integer param = Integer.parseInt(getParam("id"));
            userService.deleteUser(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return queryUser() ;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    protected String getParam(String key) {
        return ServletActionContext.getRequest().getParameter(key);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String loginUser() {

        request = ServletActionContext.getRequest();

        String userName = user.getName();
        String password = user.getPassword();
        System.out.println(password);
        long loginTime = UserServiceImp.dateToLong(currentime);

        /**
         * 先判断登录用户是否被锁定
         */
        if (userService.checkLoginLockUser(userName, loginTime)) {
            request.setAttribute("msg", "该用户已被锁定，帐号还在锁定中，不能登录！");
            return ERROR;
        } else
            /**
             * 先判断是否是黑名单用户
             */
            if (userService.isBlackUser(userName)) {
            request.setAttribute("msg", "该用户是黑名单用户，不能登录！");
            return ERROR;
        } else {
            /**
             * 再进行用户密码校验
             */
            SystemConfig systemConfig = SystemConfig.getInstance();
            systemConfig.init();
            String secretKey = SystemConfig.get("secretKey");
            String encrypt = AESUtilFinal.encrypt(secretKey, password);
            System.out.println(encrypt);

            if (userService.haveUser(userName, encrypt)) {
                log = new Log();
                log.setUserName(userName);
                log.setLoginTime(currentime);
                log.setLogIp(getIpAddr(request));
                log.setLog("用户登录");
                logService.logWrite(log);
                return SUCCESS;
            } else if (userService.lockUser(userName, loginTime)) {
                request.setAttribute("user", user);
                request.setAttribute("msg", "密码输错五次，帐号将被锁定五分钟，五分钟内不能登录！");
                return ERROR;
            } else {
                request.setAttribute("user", user);
                request.setAttribute("msg", "用户名或密码错误！");
                return ERROR;
            }
        }
    }

    public String queryUserRegistTime() {
        request = ServletActionContext.getRequest();
        String startTime = getParam("startTime");
        String endTime = getParam("endTime");
        users = userService.queryUserByRegistTime(startTime, endTime);
        request.setAttribute("startTime",startTime);
        request.setAttribute("endTime",endTime);
        return SUCCESS;
    }

    /**
     * 获取用户登录的IP地址
     *
     * @param request
     * @return ip
     * @author Fcscanf
     * @date 下午 19:39 2019-01-05/0005
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.equals("0:0:0:0:0:0:0:1")) {
            ip = "本地";
        }
        return ip;
    }

    /**
     * 判断手机号格式正确性
     *
     * @param phone
     * @return true
     * @author Fcscanf
     * @date 上午 10:08 2019-01-06/0006
     */
    public boolean isPhone(String phone) {
        SystemConfig systemConfig = SystemConfig.getInstance();
        systemConfig.init();
        String phoneLength = SystemConfig.get("phoneLength");
        String phoneFirst = phone.substring(0, 1);
        String telLength = String.valueOf(phone.length());
        if (telLength.equals(phoneLength) && phoneFirst.equals(String.valueOf(1))) {
            return false;
        }
        return true;
    }

    /**
     * 判断密码是否为数字和英文的组合
     *
     * @param password
     * @return
     * @author Fcscanf
     * @date 上午 10:35 2019-01-06/0006
     */
    public static boolean isEngDig(String password) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
        return password.matches(regex);
    }
}
