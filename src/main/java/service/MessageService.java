package service;

import entity.Message;

import java.util.List;

/**
 * <h1>消息类服务接口</h1>
 *
 * <p>定义对象类文件读写接口</p>
 *
 * @author: wjl
 * @date: 2022/1/8 10:27
 * @version: v1.0
 */
public interface MessageService {

    /**
     * 查询所有消息
     *
     * @return list
     */
    List<Message> findAll();

    /**
     * 通过用户名查找消息
     *
     * @param username username
     * @return list
     */
    List<Message> findByUsername(String username);

    /**
     * 从文件中读取所有消息对象
     *
     * @return list
     */
    List<Message> read();

    /**
     * 向文件中写入消息对象列表
     *
     * @param messages messages
     */
    void write(List<Message> messages);

    /**
     * 向文件中写入单个消息对象
     *
     * @param message message
     */
    void write(Message message);

}
