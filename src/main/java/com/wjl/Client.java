package com.wjl;

import com.wjl.entity.Message;
import com.wjl.entity.User;
import com.wjl.frame.LoginFrame;
import com.wjl.frame.MainFrame;
import com.wjl.frame.RegisterDialog;
import com.wjl.frame.RegisterFrame;
import com.wjl.service.MessageService;
import com.wjl.service.MessageServiceImpl;
import com.wjl.service.UserService;
import com.wjl.service.UserServiceImpl;
import com.wjl.util.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;
import java.util.List;

/**
 * <h1>Socket 客户端端程序</h1>
 *
 * <br />
 * <b>运行 main 方法，加入命令行参数（服务器地址），与服务器建立 socket 连接</b>
 *
 * @author: wjl
 * @date: 2022/1/8 22:43
 * @version: v1.0
 */
public class Client {

    // 手动注入日志操作类
    private static final LogUtil log = new LogUtil(new File(Constants.CLIENT_LOG));
    // 手动注入用户类服务接口提供类
    private final static UserService userService = new UserServiceImpl();
    // 手动注入消息类服务接口提供类
    private final static MessageService messageService = new MessageServiceImpl();

    ///////////////////////////////////////////////////////////////////////////

    // 组合容器组件
    public LoginFrame loginFrame = new LoginFrame();
    public RegisterFrame registerFrame = new RegisterFrame();
    public RegisterDialog registeredDialog = new RegisterDialog();
    public MainFrame mainFrame = new MainFrame();

    public List<String> users;                   // 从文件读取所有用户名
    public List<Message> messages;               // 从文件读取所以消息
    public String serverAdder;                    // 服务器地址
    public static Scanner in;                    // 输入
    public static PrintWriter out;               // 输出
    public String username;                      // 用户名

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    /**
     * <h1>客户端程序入口</h1>
     *
     * @param args 命令行参数（1个）: 服务器 IP 地址
     * @throws Exception e
     */
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        if (args.length > 1) {
            log.info("没有指定服务器地址");
            return;
        }
        init(args[0]);
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////

    /**
     * <p>构造方法</p>
     *
     * <p>初始化用户名和消息列表</p>
     *
     * @param serverAdder serverAddress
     */
    public Client(String serverAdder) {
        this.serverAdder = serverAdder;
        this.users = userService.findAllUsernames();
        this.messages = messageService.findAll();
    }

    /**
     * <p>程序启动，绘制界面</p>
     *
     * @param serverAddr serverAddress
     */
    private static void init(String serverAddr) {
        // 实例化客户端
        var client = new Client(serverAddr);
        // 设定全局字体
        SwingUtil.initGlobalFont(new Font("宋体", Font.PLAIN, 15));
        // 启动主界面
        SwingUtil.run(client.mainFrame, client.mainFrame.getPane(), Constants.NAME);
        // 初始化用户列表
        JTextPane usersPane = client.mainFrame.getUsersPane();
        client.mainFrame.getUsersPane().setEditable(true);
        for (String user : client.getUsers()) {
            SwingUtil.appendToPanel(usersPane, user, Color.black, 13);
        }
        client.mainFrame.getUsersPane().setEditable(false);
        client.mainFrame.setVisible(false);
        // 初始化消息列表
        JTextPane messagePane = client.mainFrame.getShowPane();
        client.mainFrame.getShowPane().setEditable(true);
        for (Message message : client.getMessages()) {
            String username = message.getUsername();
            String time = message.getTime();
            String content = message.getContent();
            SwingUtil.appendToPanel(messagePane, username + " " + time + " " + content, Color.black, 15);
        }
        // 启动登录界面
        SwingUtil.run(client.loginFrame, client.loginFrame.getPane(), "登录");
        client.loginFrame.setVisible(true);
        // 添加登录界面登录按钮侦听器
        client.loginFrame.getLoginButton().addActionListener(e -> {
            String name = client.loginFrame.getUsername().getText();
            String pwd = String.valueOf(client.loginFrame.getPassword().getPassword());
            if (null != userService.findUserByUsernameAndPassword(name, Base64Util.encrypt(pwd))) {
                client.mainFrame.getUsername().setText(name);
                client.setUsername(name);
                log.info("[用户登录] " + userService.findUserByUsername(name));
                // 添加主界面注销按钮侦听器
                client.mainFrame.getLogoutButton().addActionListener(e1 -> {
                    client.mainFrame.dispose();
                    client.loginFrame.dispose();
                    System.exit(0);
                });
                client.loginFrame.setVisible(false);
                client.mainFrame.setVisible(true);
                client.loginFrame.dispose();
            } else {
                client.loginFrame.getPasswordAlert().setText("密码不正确");
            }
        });
        // 添加登录界面注册按钮侦听器
        client.loginFrame.getRegisterButton().addActionListener(e -> {
            // 启动注册界面
            SwingUtil.run(client.registerFrame, client.registerFrame.getPane(), "注册");
            client.loginFrame.setVisible(false);
            client.registerFrame.setVisible(true);
            // 添加注册界面返回按钮侦听器
            client.registerFrame.getBackButton().addActionListener(e1 -> {
                client.registerFrame.setVisible(false);
                client.registerFrame.clearAllInput();
                client.loginFrame.setVisible(true);
                client.registerFrame.dispose();
            });
            // 添加注册界面注册按钮侦听器
            client.registerFrame.getRegisterButton().addActionListener(e2 -> {
                RegisterFrame frame = client.registerFrame;
                String username = frame.getUsername().getText();
                String password = Base64Util.encrypt(String.valueOf(frame.getPassword().getPassword()));
                Integer age = Integer.valueOf(frame.getAge().getText());
                boolean gender = frame.getButtonGroup().getSelection().getActionCommand().equals("male");
                User user = new User(username, password, gender, age);
                userService.write(user);
                log.info("[新增用户] " + user);
                // 注册成功
                // 启动注册成功对话框
                SwingUtil.run(client.registeredDialog, Constants.NAME);
                client.registeredDialog.setVisible(true);
                client.registerFrame.setVisible(false);
                client.loginFrame.setVisible(true);
                client.registerFrame.dispose();
            });
            // 添加注册界面清空按钮侦听器
            client.registerFrame.getClearButton().addActionListener(e3 -> client.registerFrame.clearAllInput());
        });
        // 添加主界面加入群聊按钮侦听器
        client.mainFrame.getJoinButton().addActionListener(e -> {
            client.mainFrame.getSendField().setEditable(true);
            client.mainFrame.getJoinButton().setVisible(false);
            Thread t = new Thread(() -> {
                try {
                    client.run(client.username);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            t.start();
            log.info("[加入群聊] " + client.username);
        });
        // 添加主界面发送侦听器
        client.mainFrame.getSendField().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {}
            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            public void warn() {
                String content = client.mainFrame.getSendField().getText();
                if (content.trim().length() <= 0 || content.equals("")) {
                    client.mainFrame.getSendAlert().setText("不能为空");
                } else {
                    client.mainFrame.getSendAlert().setText("");
                }
            }
        });
        // 添加主界面输入框侦听器
        client.mainFrame.getSendField().addActionListener(e -> {
            String content = client.mainFrame.getSendField().getText();
            if (content.trim().length() <= 0 || content.equals("")) {
                client.mainFrame.getSendAlert().setText("不能为空");
            } else {
                String time = "[" + DateUtil.dateToLocalDateTimeWithoutMill(new Date()) + "]";
                String username = client.username + ":";
                Message message = new Message(username, time, content);
                messageService.write(message);
                out.println(time + " " + client.mainFrame.getSendField().getText());
                log.info("[发送消息] " + message);
                client.mainFrame.getSendField().setText("");
            }
        });
    }

    /**
     * 消息收发方法入口
     *
     * @param username username
     * @throws IOException e
     */
    private synchronized void run(String username) throws IOException {
        try {
            var socket = new Socket(serverAdder, Constants.SERVER_PORT);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            while (in.hasNextLine()) {
                var line = in.nextLine();
                if (line.startsWith(Constants.SUBMIT)) {
                    out.println(username);
                } else if (line.startsWith(Constants.ACCEPT)) {
                    mainFrame.getSendField().setEditable(true);
                } else if (line.startsWith(Constants.NOTIFICATION)) {
                    JTextPane panel = mainFrame.getShowPane();
                    mainFrame.getShowPane().setEditable(true);
                    SwingUtil.appendToPanel(panel, line.substring(13), Color.black, 15);
                } else if (line.startsWith(Constants.MESSAGE)) {
                    JTextPane panel = mainFrame.getShowPane();
                    mainFrame.getShowPane().setEditable(true);
                    SwingUtil.appendToPanel(panel, line.substring(8), Color.black, 15);
                }
            }
        } finally {
            mainFrame.setVisible(false);
            mainFrame.dispose();
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getUsers() {
        return users;
    }

    public List<Message> getMessages() {
        return messages;
    }

}
