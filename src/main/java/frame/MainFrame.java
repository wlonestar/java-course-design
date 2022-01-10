package frame;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import service.UserService;
import service.UserServiceImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * <h1>主聊天界面类</h1>
 *
 * <p>通过 Idea 的 GUI Designer 插件生成布局代码</p>
 * <p>使用了 Idea 的第三方库</p>
 *
 * @author: wjl
 * @date: 2022/1/8 21:11
 * @version: v1.0
 */
public class MainFrame extends JFrame {

    // 手动注入用户类服务接口提供类
    private final static UserService userService = new UserServiceImpl();

    private JPanel pane;                         // 主面板（包括侧边面板和聊天面板）
    ///////////////////////////////////////////////////////////////////////////
    private JPanel sidePane;                     // 侧边面板（包括侧边标题面板和在线用户面板）
    private JPanel sidetitlePane;                // 侧边标题面板
    private JLabel onlineLabel;                  // 群成员标签
    private JButton joinButton;                  // 加入群聊按钮
    ///////////////////////////////////////////////////////////////////////////
    private JScrollPane onlinePane;              // 在线用户面板
    private JTextPane usersPane;                 // 用户列表
    ///////////////////////////////////////////////////////////////////////////
    private JPanel chatPane;                     // 聊天面板（包括头部面板、消息面板和发送面板）
    private JPanel headPane;                     // 头部面板
    private JPanel titlePane;                    // 标题面板
    private JLabel windowTitle;                  // 聊天室标签
    private JPanel namePane;                     // 用户面板
    private JLabel username;                     // 用户名标签
    private JButton logoutButton;                // 注销按钮
    ///////////////////////////////////////////////////////////////////////////
    private JScrollPane messagePane;             // 消息面板
    private JTextPane showPane;                  // 消息列表
    //////////////////////////////////////////////////////////////////////////
    private JPanel sendPane;                     // 发送面板
    private JTextField sendField;                // 消息输入框
    private JLabel sendAlert;                    // 消息合法检验

    // 设置容器组件边距
    EmptyBorder eb = new EmptyBorder(new Insets(10, 10, 10, 10));

    public MainFrame() {
        // generated by GUI designer
        pane = new JPanel();
        pane.setLayout(new GridLayoutManager(1, 2, new Insets(10, 0, 10, 10), -1, -1));
        pane.setInheritsPopupMenu(false);
        pane.setPreferredSize(new Dimension(900, 600));
        sidePane = new JPanel();
        sidePane.setLayout(new GridLayoutManager(2, 1, new Insets(0, 10, 5, 5), -1, -1));
        pane.add(sidePane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(175, -1), null, 0, false));
        onlinePane = new JScrollPane();
        onlinePane.setAutoscrolls(false);
        sidePane.add(onlinePane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        onlinePane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        usersPane = new JTextPane();
        onlinePane.setViewportView(usersPane);
        sidetitlePane = new JPanel();
        sidetitlePane.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        sidePane.add(sidetitlePane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 30), null, 0, false));
        onlineLabel = new JLabel();
        onlineLabel.setText("群成员");
        sidetitlePane.add(onlineLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        joinButton = new JButton();
        joinButton.setText("加入群聊");
        sidetitlePane.add(joinButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(125, 30), null, 0, false));
        final Spacer spacer1 = new Spacer();
        sidetitlePane.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        chatPane = new JPanel();
        chatPane.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        pane.add(chatPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(600, -1), null, 0, false));
        headPane = new JPanel();
        headPane.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        chatPane.add(headPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, true));
        titlePane = new JPanel();
        titlePane.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        headPane.add(titlePane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        windowTitle = new JLabel();
        windowTitle.setText("摸鱼聊天室");
        titlePane.add(windowTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        namePane = new JPanel();
        namePane.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        headPane.add(namePane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        username = new JLabel();
        username.setText("wjl");
        namePane.add(username, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logoutButton = new JButton();
        logoutButton.setText("注销");
        namePane.add(logoutButton, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 30), null, 0, false));
        final Spacer spacer2 = new Spacer();
        namePane.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        sendPane = new JPanel();
        sendPane.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        chatPane.add(sendPane, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, true));
        sendField = new JTextField();
        sendField.setPreferredSize(new Dimension(-1, 25));
        sendPane.add(sendField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 30), null, 0, false));
        messagePane = new JScrollPane();
        chatPane.add(messagePane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(-1, 450), null, 0, false));
        showPane = new JTextPane();
        showPane.setText("");
        messagePane.setViewportView(showPane);
        sendAlert = new JLabel();
        sendAlert.setForeground(new Color(-693140));
        sendAlert.setText("Label");
        chatPane.add(sendAlert, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        // 自定义初始化容器参数
        sendAlert.setText("");
        sendField.setEditable(false);
        showPane.setBorder(eb);
        showPane.setMargin(new Insets(10, 5, 10, 5));
        usersPane.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        usersPane.setMargin(new Insets(15, 5, 15, 5));
    }

    public JPanel getPane() {
        return pane;
    }

    public void setPane(JPanel pane) {
        this.pane = pane;
    }

    public JPanel getSidePane() {
        return sidePane;
    }

    public void setSidePane(JPanel sidePane) {
        this.sidePane = sidePane;
    }

    public JScrollPane getOnlinePane() {
        return onlinePane;
    }

    public void setOnlinePane(JScrollPane onlinePane) {
        this.onlinePane = onlinePane;
    }

    public JPanel getChatPane() {
        return chatPane;
    }

    public void setChatPane(JPanel chatPane) {
        this.chatPane = chatPane;
    }

    public JPanel getHeadPane() {
        return headPane;
    }

    public void setHeadPane(JPanel headPane) {
        this.headPane = headPane;
    }

    public JLabel getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(JLabel windowTitle) {
        this.windowTitle = windowTitle;
    }

    public JScrollPane getMessagePane() {
        return messagePane;
    }

    public void setMessagePane(JScrollPane messagePane) {
        this.messagePane = messagePane;
    }

    public JPanel getSendPane() {
        return sendPane;
    }

    public void setSendPane(JPanel sendPane) {
        this.sendPane = sendPane;
    }

    public JLabel getOnlineLabel() {
        return onlineLabel;
    }

    public void setOnlineLabel(JLabel onlineLabel) {
        this.onlineLabel = onlineLabel;
    }

    public JTextPane getShowPane() {
        return showPane;
    }

    public void setShowPane(JTextPane showPane) {
        this.showPane = showPane;
    }

    public JPanel getSidetitlePane() {
        return sidetitlePane;
    }

    public void setSidetitlePane(JPanel sidetitlePane) {
        this.sidetitlePane = sidetitlePane;
    }

    public JTextPane getUsersPane() {
        return usersPane;
    }

    public void setUsersPane(JTextPane usersPane) {
        this.usersPane = usersPane;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(JButton logoutButton) {
        this.logoutButton = logoutButton;
    }

    public JLabel getUsername() {
        return username;
    }

    public void setUsername(JLabel username) {
        this.username = username;
    }

    public JButton getJoinButton() {
        return joinButton;
    }

    public void setJoinButton(JButton joinButton) {
        this.joinButton = joinButton;
    }

    public JPanel getTitlePane() {
        return titlePane;
    }

    public void setTitlePane(JPanel titlePane) {
        this.titlePane = titlePane;
    }

    public JPanel getNamePane() {
        return namePane;
    }

    public void setNamePane(JPanel namePane) {
        this.namePane = namePane;
    }

    public JTextField getSendField() {
        return sendField;
    }

    public void setSendField(JTextField sendField) {
        this.sendField = sendField;
    }

    public JLabel getSendAlert() {
        return sendAlert;
    }

    public void setSendAlert(JLabel sendAlert) {
        this.sendAlert = sendAlert;
    }

}
