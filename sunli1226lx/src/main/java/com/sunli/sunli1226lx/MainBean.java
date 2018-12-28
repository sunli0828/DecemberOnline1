package com.sunli.sunli1226lx;

public class MainBean {
    String name;
    String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public MainBean(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
