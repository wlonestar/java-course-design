package com.wjl.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * <h1>用户实体类</h1>
 *
 * <p>记录注册用户信息，包括用户名、密码、性别、年龄</p>
 * <p>实现序列化接口，支持对象持久化</p>
 *
 * @author: wjl
 * @date: 2022/1/8 8:57
 * @version: v1.0
 */
public class User implements Serializable {

    /**
     * <h3>用户名</h3>
     *
     * <p>不少于3字符且不多于20字符</p>
     */
    private String username;

    /**
     * <h3>登录密码</h3>
     *
     * <p>不低于6位且不多于20位</p>
     */
    private final String password;

    /**
     * <h3>性别</h3>
     *
     * <p>男: true</p>
     * <p>女: false</p>
     */
    private final boolean gender;

    /**
     * <h3>年龄</h3>
     *
     * <p>年龄不能大于100岁</p>
     */
    private final Integer age;

    public User(String username, String password, boolean gender, Integer age) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword());
    }

    @Override
    public String toString() {
        return "'" + username + "' '" + password + "' '" + gender + "' '" + age + "'";
    }

}
