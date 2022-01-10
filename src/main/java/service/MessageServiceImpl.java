package service;

import entity.Message;
import util.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>消息类服务接口实现类</h1>
 *
 * <p>实现消息类读写操作，和一些常用方法</p>
 *
 * @author: wjl
 * @date: 2022/1/8 10:30
 * @version: v1.0
 */
public class MessageServiceImpl implements MessageService {

    /**
     * 定义消息类存储文件
     */
    private final File file = new File(Constants.MESSAGE_FILE);

    /**
     * 查询所有消息
     *
     * @return list
     */
    @Override
    public List<Message> findAll() {
        return read();
    }

    /**
     * 通过用户名查找消息
     *
     * @param username username
     * @return list
     */
    @Override
    public List<Message> findByUsername(String username) {
        List<Message> list = read();
        List<Message> messages = new ArrayList<>();
        for (Message message : list) {
            if (message.getUsername().equals(username)) {
                messages.add(message);
            }
        }
        return messages;
    }

    /**
     * 从文件中读取所有消息对象
     *
     * @return list
     */
    @Override
    public synchronized List<Message> read() {
        List<Message> messageList = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            messageList = (List<Message>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageList;
    }

    /**
     * 向文件中写入消息对象列表
     *
     * @param messages messages
     */
    @Override
    public synchronized void write(List<Message> messages) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(messages);
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向文件中写入单个消息对象
     *
     * @param message message
     */
    @Override
    public synchronized void write(Message message) {
        List<Message> messageList = read();
        messageList.add(message);
        write(messageList);
    }

}
