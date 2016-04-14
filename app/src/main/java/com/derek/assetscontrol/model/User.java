package com.derek.assetscontrol.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

/**
 * Created by derek on 16/4/11.
 */
public class User implements Serializable{

    @JSONField(name = "staff_id") private String userId;
    @JSONField(name = "username") private String userName;
    @JSONField(name = "department") private String department;
    @JSONField(name = "type") private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
