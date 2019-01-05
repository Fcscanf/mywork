package com.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Log
 *
 * @author Fcscanf
 * @description
 * @date 下午 16:38 2019-01-05/0005
 */
public class Log implements Serializable {
    private Integer id;
    private String userName;
    private String loginTime;
    private String logIp;
    private String logoutTime;
    private String log;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public String getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(String logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log1 = (Log) o;
        return Objects.equals(id, log1.id) &&
                Objects.equals(userName, log1.userName) &&
                Objects.equals(loginTime, log1.loginTime) &&
                Objects.equals(logIp, log1.logIp) &&
                Objects.equals(logoutTime, log1.logoutTime) &&
                Objects.equals(log, log1.log);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, loginTime, logIp, logoutTime, log);
    }
}
