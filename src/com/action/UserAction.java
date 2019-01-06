package com.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.model.Log;
import com.service.LogService;
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
        int minpasslength = SystemConfig.getInteger("minpasslength");
        int maxpasslength = SystemConfig.getInteger("maxpasslength");

        if (userService.exits(user.getName())){
            request.setAttribute("msg", "该用户名已存在！");
            return ERROR;
        } else if (!(password.length() > minpasslength && password.length() < maxpasslength)) {
            request.setAttribute("passmsg", "用户密码长度不符合要求！");
            return ERROR;
        } else if (isPhone(user.getPhone())) {
            request.setAttribute("phonemsg", "手机号格式不正确！");
            return ERROR;
        } else if (!(user.getAge() > minage && user.getAge() < maxage)) {
            request.setAttribute("agemsg", "用户年龄大小不符合要求！");
            return ERROR;
        }

        /**
         * 密码的明文加密
         */
        String secretKey = SystemConfig.get("secretKey");
        String encrypt = AESUtilFinal.encrypt(secretKey, password);
        System.out.println(encrypt);
        user.setPassword(encrypt);

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
        return SUCCESS;
    }

    public String deleteUser() {
        return SUCCESS;
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
            } else {
                request.setAttribute("msg", "用户名或密码错误！");
                return ERROR;
            }
        }
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
     * @return
     * @author Fcscanf
     * @date 上午 10:08 2019-01-06/0006
     */
    public boolean isPhone(String phone) {
        SystemConfig systemConfig = SystemConfig.getInstance();
        systemConfig.init();
        String phoneLength = SystemConfig.get("phonelength");
        String phoneFirst = phone.substring(0, 1);
        String telLength = String.valueOf(phone.length());
        if (telLength.equals(phoneLength) && phoneFirst.equals(1)) {
            return false;
        }
        return true;
    }
}
