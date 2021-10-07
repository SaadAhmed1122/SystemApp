package com.mvvm.systemapp;

public class modelclass {
    String SOL1DELAYTIME,SOL1SPRAYTIME,SOL2DELAYTIME,SOL2SPRAYTIME,
            created_time,test,uid,email,name,token,status,statusmsg;

    public modelclass(String SOL1DELAYTIME, String SOL1SPRAYTIME, String SOL2DELAYTIME, String SOL2SPRAYTIME, String created_time, String test, String uid, String email, String name, String token, String status, String statusmsg) {
        this.SOL1DELAYTIME = SOL1DELAYTIME;
        this.SOL1SPRAYTIME = SOL1SPRAYTIME;
        this.SOL2DELAYTIME = SOL2DELAYTIME;
        this.SOL2SPRAYTIME = SOL2SPRAYTIME;
        this.created_time = created_time;
        this.test = test;
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.token = token;
        this.status = status;
        this.statusmsg = statusmsg;
    }

    public modelclass(String SOL1DELAYTIME, String SOL1SPRAYTIME, String SOL2DELAYTIME, String SOL2SPRAYTIME, String created_time, String test, String uid, String email, String name, String token) {
        this.SOL1DELAYTIME = SOL1DELAYTIME;
        this.SOL1SPRAYTIME = SOL1SPRAYTIME;
        this.SOL2DELAYTIME = SOL2DELAYTIME;
        this.SOL2SPRAYTIME = SOL2SPRAYTIME;
        this.created_time = created_time;
        this.test = test;
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusmsg() {
        return statusmsg;
    }

    public void setStatusmsg(String statusmsg) {
        this.statusmsg = statusmsg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSOL1DELAYTIME() {
        return SOL1DELAYTIME;
    }

    public void setSOL1DELAYTIME(String SOL1DELAYTIME) {
        this.SOL1DELAYTIME = SOL1DELAYTIME;
    }

    public String getSOL1SPRAYTIME() {
        return SOL1SPRAYTIME;
    }

    public void setSOL1SPRAYTIME(String SOL1SPRAYTIME) {
        this.SOL1SPRAYTIME = SOL1SPRAYTIME;
    }

    public String getSOL2DELAYTIME() {
        return SOL2DELAYTIME;
    }

    public void setSOL2DELAYTIME(String SOL2DELAYTIME) {
        this.SOL2DELAYTIME = SOL2DELAYTIME;
    }

    public String getSOL2SPRAYTIME() {
        return SOL2SPRAYTIME;
    }

    public void setSOL2SPRAYTIME(String SOL2SPRAYTIME) {
        this.SOL2SPRAYTIME = SOL2SPRAYTIME;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
