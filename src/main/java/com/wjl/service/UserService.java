package com.wjl.service;

import com.wjl.entity.User;

import java.util.List;

/**
 * <h1>用户类服务接口</h1>
 *
 * <p>定义用户类文件读写接口，和一些常用方法</p>
 *
 * @author: wjl
 * @date: 2022/1/8 9:24
 * @version: v1.0
 */
public interface UserService {

    /**
     * 查询所以消息
     *
     * @return list
     */
    List<String> findAllUsernames();

    /**
     * 通过用户名查找用户
     *
     * @param username username
     * @return user
     */
    User findUserByUsername(String username);

    /**
     * 通过用户名和密码查找用户
     *
     * @param username username
     * @param password password
     * @return user
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 从文件中读取所有用户对象
     *
     * @return list
     */
    List<User> read();

    /**
     * 向文件中写入用户对象列表
     *
     * @param users users
     */
    void write(List<User> users);

    /**
     * 向文件中写入单个用户对象
     *
     * @param user user
     */
    void write(User user);

}
