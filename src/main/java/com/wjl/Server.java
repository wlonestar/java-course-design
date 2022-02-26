package com.wjl;

import com.wjl.entity.Message;
import com.wjl.service.MessageService;
import com.wjl.service.MessageServiceImpl;
import com.wjl.util.Constants;
import com.wjl.util.DateUtil;
import com.wjl.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Executors;

/**
 * <h1>Socket 服务端程序</h1>
 *
 * <br />
 * <b>运行 main 方法，在本机指定端口启动 socket 服务</b>
 *
 * @author: wjl
 * @date: 2022/1/8 10:44
 * @version: v1.0
 */
public class Server {

    // 手动注入日志操作类
    private static final LogUtil log = new LogUtil(new File(Constants.SERVER_LOG));
    // 手动注入消息类服务接口提供类
    private static final MessageService messageService = new MessageServiceImpl();

    ///////////////////////////////////////////////////////////////////////////

    // 建立 socket 通信的用户名，不唯一，注销后用户名即去除
    public static final Set<String> names = new HashSet<>();
    // 对每一个建立 socket 通信的用户分配信息输出
    public static Set<PrintWriter> writers = new HashSet<>();
    // 线程连接数
    private static int count = 0;

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * <h1>服务端程序入口</h1>
     *
     * @param args 命令行参数为空
     * @throws Exception e
     */
    public static void main(String[] args) throws Exception {
        log.info("[log] server is running at " + "/127.0.0.1:" + Constants.SERVER_PORT);
        var pool = Executors.newFixedThreadPool(Constants.THREAD_NUMBER);
        try (var listener = new ServerSocket(Constants.SERVER_PORT)) {
            while (true) {
                Handler handler = new Handler(listener.accept());
                pool.execute(handler);
                String remoteAddress = handler.socket.getRemoteSocketAddress().toString();
                log.info("[log] (client " + (count++) + ") is running at " + remoteAddress);
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * <h2>socket 连接处理类</h2>
     *
     * <p>对每个 socket 连接分别进行处理</p>
     */
    private static class Handler implements Runnable {
        private String name;                     // 用户名
        private final Socket socket;                   // socket 实例
        private Scanner in;                      // 输入
        private PrintWriter out;                 // 输出

        /**
         * 构造方法
         *
         * @param socket socket
         */
        public Handler(Socket socket) {
            this.socket = socket;
        }

        /**
         * <p>重写 {@code run()} 方法</p>
         *
         * <p>对用户进行上下线处理、对消息进行分发处理</p>
         */
        @Override
        public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);
                while (true) {
                    String submit = Constants.SUBMIT;
                    out.println(submit);
                    name = in.nextLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!name.isBlank() && !names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }
                // 当有用户与服务器建立连接后
                // 定义系统消息，向所有其他连接的用户广播
                String accept = Constants.ACCEPT + " " + name;
                out.println(accept);
                String username = "[系统消息]:";
                String content = name + " 加入了群聊";
                String time = "[" + DateUtil.dateToLocalDateTimeWithoutMill(new Date()) + "]";
                Message message = new Message(username, time, content);
                for (PrintWriter writer : writers) {
                    writer.println(Constants.NOTIFICATION + " " + username + " " + time + " " + content);
                }
                writers.add(out);
                log.info("[log] " + message);
                while (true) {
                    String input = in.nextLine();
                    if (input.toLowerCase().startsWith("/quit")) {
                        return;
                    }
                    // 当用户发送消息时
                    // 向所有其他连接的用户广播用户发送的消息
                    for (PrintWriter writer : writers) {
                        String messag = Constants.MESSAGE + " " + name + ": " + input;
                        writer.println(messag);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    writers.remove(out);
                }
                if (name != null) {
                    // 当用户下线时
                    // 从 set 集合里删除该用户，向所有其他连接的用户广播
                    names.remove(name);
                    String username = "[系统消息]:";
                    String content = name + " 退出了群聊";
                    String time = "[" + DateUtil.dateToLocalDateTimeWithoutMill(new Date()) + "]";
                    for (PrintWriter writer : writers) {
                        writer.println(Constants.MESSAGE + " " + username + " " + time + " " + content);
                    }
                    Message message = new Message(username, time, content);
                    messageService.write(message);
                    log.info("[log] " + message);
                }
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }

    }

}
