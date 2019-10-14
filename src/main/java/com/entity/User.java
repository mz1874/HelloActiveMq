package com.entity;

import java.io.Serializable;

public class User implements Serializable {

    private Integer id;

    private String name;

    public User() {
        this.id = 1;
        this.name = "张三";
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
