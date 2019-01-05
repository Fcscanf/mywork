package com.model;

import java.sql.Date;
import java.util.Objects;

/**
 * Blackuser
 *
 * @author Fcscanf
 * @description
 * @date 上午 8:26 2019-01-05/0005
 */
public class Blackuser {
    private Integer id;
    private String userName;
    private Date currentTime;

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

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Blackuser blackuser = (Blackuser) o;
        return Objects.equals(id, blackuser.id) &&
                Objects.equals(userName, blackuser.userName) &&
                Objects.equals(currentTime, blackuser.currentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, currentTime);
    }
}
