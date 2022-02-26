package com.wjl.service;

import com.wjl.entity.User;
import com.wjl.util.Constants;

import java.io.*;
import java.util.*;

/**
 * <h1>用户类服务接口实现类</h1>
 *
 * <p>实现用户类读写操作，和一些常用方法</p>
 *
 * @author: wjl
 * @date: 2022/1/8 9:25
 * @version: v1.0
 */
public class UserServiceImpl implements UserService {

    /**
     * 定义用户类存储文件
     */
    private final File file = new File(Constants.USER_FILE);

    /**
     * 查询所以消息
     *
     * @return list
     */
    @Override
    public List<String> findAllUsernames() {
        List<User> users = read();
        List<String> names = new ArrayList<>();
        for (User user : users) {
            names.add(user.getUsername());
        }
        return names;
    }

    /**
     * 通过用户名查找用户
     *
     * @param username username
     * @return user
     */
    @Override
    public User findUserByUsername(String username) {
        List<User> users = read();
        for (User user : users) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    /**
     * 通过用户名和密码查找用户
     *
     * @param username username
     * @param password password
     * @return user
     */
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        List<User> users = read();
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    /**
     * 从文件中读取所有用户对象
     *
     * @return list
     */
    @Override
    public synchronized List<User> read() {
        List<User> users = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            users = (List<User>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * 向文件中写入用户对象列表
     *
     * @param users users
     */
    @Override
    public synchronized void write(List<User> users) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(users);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向文件中写入单个用户对象
     *
     * @param user user
     */
    @Override
    public synchronized void write(User user) {
        List<User> users = read();
        users.add(user);
        write(users);
    }

}

