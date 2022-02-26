package com.wjl.util;

/**
 * <h1>定义全局常量</h1>
 *
 * @author: wjl
 * @date: 2022/1/8 9:04
 * @version: v1.0
 */
public class Constants {

    // 线程池大小
    public static final int THREAD_NUMBER = 500;
    // 服务端端口号
    public static final int SERVER_PORT = 59001;
    // 程序名
    public static final String NAME = "JChat";

    ////////////////////////// socket 通信标志词 ///////////////////////////////////

    // 提交用户请求
    public static final String SUBMIT = "submit";
    // 接受用户请求
    public static final String ACCEPT = "accept";
    // 用户发送的消息
    public static final String MESSAGE = "message";
    // 系统消息通知
    public static final String NOTIFICATION = "notification";

    ////////////////////////// 全局文件路径 /////////////////////////////////////////

    // 程序图标文件路径
    public static final String ICON_IMAGE = "./icon.png";
    // 用户存储文件路径
    public static final String USER_FILE = "./users.dat";
    // 消息存储文件路径
    public static final String MESSAGE_FILE = "./message.dat";
    // 服务端日志文件路径
    public static final String SERVER_LOG = "./chat-server.log";
    // 客户端日志文件路径
    public static final String CLIENT_LOG = "./chat-client.log";

}
