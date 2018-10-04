package com.example.user.coalert.forRestServer;

public class GetUserModel {
    String name;
    String email;
    String type;
    String birth;
    String sex;
    String access;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
    //{Consts.NAME.value : info[0], Consts.EMAIL.value : info[1], Consts.TYPE.value : info[2],
    // Consts.BIRTH.value : info[3].strftime('%Y-%m-%d'), Consts.SEX.value : info[4], Consts.ACCESS.value : info[5]}
}
