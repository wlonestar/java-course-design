package frame;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import service.UserService;
import service.UserServiceImpl;
import util.Constants;
import util.SwingUtil;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;

/**
 * <h1>注册界面类</h1>
 *
 * <p>通过 Idea 的 GUI Designer 插件生成布局代码</p>
 * <p>使用了 Idea 的第三方库</p>
 *
 * @author: wjl
 * @date: 2022/1/8 19:09
 * @version: v1.0
 */
public class RegisterFrame extends JFrame {

    // 手动注入用户类服务接口提供类
    private static final UserService userService = new UserServiceImpl();

    private JPanel pane;                         // 主面板（包括返回面板、标题面板、输入面板和按钮面板）
    ///////////////////////////////////////////////////////////////////////////
    private JPanel backPane;                     // 返回面板
    private JButton backButton;                  // 返回按钮（返回登录界面）
    ///////////////////////////////////////////////////////////////////////////
    private JPanel titlePane;                    // 标题面板
    private JLabel titleLabel;                   // 标题标签（程序名）
    ///////////////////////////////////////////////////////////////////////////
    private JPanel inputPane;                    // 输入面板
    private JLabel usernameLabel;                // 用户名标签（用户名）
    private JTextField username;                 // 用户名输入框
    private JLabel usernameAlert;                // 用户名检验
    private JLabel passwordLabel;                // 密码标签（密码）
    private JPasswordField password;             // 密码输入框
    private JLabel passwordAlert;                // 密码校验
    private JLabel repeatPasswordLabel;          // 确认密码标签（确认密码）
    private JPasswordField repeatRassword;       // 确认密码输入框
    private JLabel repeatPasswordAlert;          // 确认密码校验
    private JLabel ageLabel;                     // 年龄标签（年龄）
    private JTextField age;                      // 年龄输入框
    private JLabel ageAlert;                     // 年龄校验
    private JLabel genderLabel;                  // 性别标签（性别）
    private JPanel genderPane;                   // 性别面板
    private ButtonGroup buttonGroup;             // 性别按钮组
    private JRadioButton maleButton;             // 男性按钮
    private JRadioButton femaleButton;           // 女性按钮
    ///////////////////////////////////////////////////////////////////////////
    private JPanel buttonPane;                   // 按钮面板
    private JButton registerButton;              // 注册按钮
    private JButton clearButton;                 // 清空按钮（清空所有输入框）

    public RegisterFrame() {
        // generated by GUI designer
        pane = new JPanel();
        pane.setLayout(new GridLayoutManager(4, 1, new Insets(10, 20, 10, 20), -1, -1));
        pane.setPreferredSize(new Dimension(480, 400));
        titlePane = new JPanel();
        titlePane.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 10, 0), -1, -1));
        pane.add(titlePane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        titleLabel = new JLabel();
        Font label1Font = SwingUtil.getFont(null, -1, 26, titleLabel.getFont());
        if (label1Font != null) titleLabel.setFont(label1Font);
        titleLabel.setText(Constants.NAME);
        titlePane.add(titleLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        inputPane = new JPanel();
        inputPane.setLayout(new GridLayoutManager(9, 2, new Insets(0, 0, 0, 10), -1, -1));
        pane.add(inputPane, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        username = new JTextField();
        inputPane.add(username, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 25), null, 0, false));
        password = new JPasswordField();
        inputPane.add(password, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 25), null, 0, false));
        age = new JTextField();
        inputPane.add(age, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 25), null, 0, false));
        usernameLabel = new JLabel();
        usernameLabel.setText("用户名");
        inputPane.add(usernameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordLabel = new JLabel();
        passwordLabel.setText("密码");
        inputPane.add(passwordLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        repeatPasswordLabel = new JLabel();
        repeatPasswordLabel.setText("确认密码");
        inputPane.add(repeatPasswordLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ageLabel = new JLabel();
        ageLabel.setText("年龄");
        inputPane.add(ageLabel, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        genderLabel = new JLabel();
        genderLabel.setText("性别");
        inputPane.add(genderLabel, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        repeatRassword = new JPasswordField();
        inputPane.add(repeatRassword, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 25), null, 0, false));
        genderPane = new JPanel();
        genderPane.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 200), -1, -1));
        inputPane.add(genderPane, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        maleButton = new JRadioButton();
        maleButton.setText("男");
        genderPane.add(maleButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        femaleButton = new JRadioButton();
        femaleButton.setText("女");
        genderPane.add(femaleButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 25), null, 0, false));
        passwordAlert = new JLabel();
        passwordAlert.setForeground(new Color(-693140));
        passwordAlert.setText("Label");
        inputPane.add(passwordAlert, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        repeatPasswordAlert = new JLabel();
        repeatPasswordAlert.setForeground(new Color(-693140));
        repeatPasswordAlert.setText("Label");
        inputPane.add(repeatPasswordAlert, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ageAlert = new JLabel();
        ageAlert.setForeground(new Color(-693140));
        ageAlert.setText("Label");
        inputPane.add(ageAlert, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        usernameAlert = new JLabel();
        usernameAlert.setForeground(new Color(-693140));
        usernameAlert.setText("Label");
        inputPane.add(usernameAlert, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        pane.add(buttonPane, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        clearButton = new JButton();
        clearButton.setText("清空");
        buttonPane.add(clearButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(70, 30), null, 0, false));
        registerButton = new JButton();
        registerButton.setText("注册");
        buttonPane.add(registerButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(70, 30), null, 0, false));
        backPane = new JPanel();
        backPane.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pane.add(backPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("返回");
        backPane.add(backButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(40, 20), null, 0, false));

        // 性别单选选项处理
        buttonGroup = new ButtonGroup();
        maleButton.setActionCommand("male");
        femaleButton.setActionCommand("female");
        maleButton.setSelected(true);
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);

        // 添加事件侦听器
        usernameAlert.setText("");
        username.getDocument().addDocumentListener(new UsernameListener());
        passwordAlert.setText("");
        password.getDocument().addDocumentListener(new PasswordListener());
        repeatPasswordAlert.setText("");
        repeatRassword.getDocument().addDocumentListener(new RepeatPasswordListener());
        ageAlert.setText("");
        age.getDocument().addDocumentListener(new AgeListener());
    }

    /**
     * 用户名输入侦听器
     */
    class UsernameListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            warn();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            warn();
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            warn();
        }
        public synchronized void warn() {
            if (username.getText().length() < 3) {
                usernameAlert.setText("用户名太短啦");
            } else if (username.getText().length() > 20) {
                usernameAlert.setText("用户名太长啦");
            } else {
                usernameAlert.setText("");
                List<String> list = userService.findAllUsernames();
                if (list.contains(username.getText())) {
                    usernameAlert.setText("用户名被注册过啦");
                } else {
                    usernameAlert.setText("");
                }
            }
        }
    }

    /**
     * 密码输入侦听器
     */
    class PasswordListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            warn();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            warn();
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            warn();
        }
        public synchronized void warn() {
            String s = String.valueOf(password.getPassword());
            if (s.length() <= 6 || s.length() >= 20) {
                passwordAlert.setText("密码长度必须在6-20位之间");
            } else {
                try {
                    Thread.sleep(100);
                } catch (Exception ignored) {}
                int[] count = new int[3];
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                        count[0]++;
                    }
                    if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                        count[1]++;
                    }
                    if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                        count[2]++;
                    }
                }
                if (count[0] != 0 && count[1] != 0 && count[2] != 0) {
                    passwordAlert.setText("");
                } else {
                    passwordAlert.setText("密码必须包含大、小写字母、数字三种");
                }
            }
        }
    }

    /**
     * 确认密码侦听器
     */
    class RepeatPasswordListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            warn();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            warn();
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            warn();
        }
        public synchronized void warn() {
            String s = String.valueOf(password.getPassword());
            if (password.getPassword().length == 0) {
                repeatPasswordAlert.setText("请先输入密码");
            } else {
                String rs = String.valueOf(repeatRassword.getPassword());
                if (rs.equals(s)) {
                    repeatPasswordAlert.setText("");
                } else {
                    repeatPasswordAlert.setText("两次输入的密码不一致");
                }
            }
        }
    }

    /**
     * 年龄输入侦听器
     */
    class AgeListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            warn();
        }
        @Override
        public void removeUpdate(DocumentEvent e) {
            warn();
        }
        @Override
        public void changedUpdate(DocumentEvent e) {
            warn();
        }
        public synchronized void warn() {
            String s = age.getText();
            char[] chars = s.toCharArray();
            int count = 0;
            for (char c : chars) {
                if (c >= '0' && c <= '9') {
                    count++;
                }
            }
            if (count == 0) {
                ageAlert.setText("年龄填的不对哦");
            } else if (count < 3) {
                ageAlert.setText("");
            } else if (count == 3) {
                if (chars[0] > 2) {
                    ageAlert.setText("年龄填的不对哦");
                } else {
                    ageAlert.setText("");
                }
            } else {
                ageAlert.setText("年龄填的不对哦");
            }
        }
    }

    /**
     * 清空所有输入框中的内容
     */
    public void clearAllInput() {
        username.setText("");
        usernameAlert.setText("");
        password.setText("");
        passwordAlert.setText("");
        repeatRassword.setText("");
        repeatPasswordAlert.setText("");
        age.setText("");
        ageAlert.setText("");
    }

    public JPanel getPane() {
        return pane;
    }

    public void setPane(JPanel pane) {
        this.pane = pane;
    }

    public JTextField getUsername() {
        return username;
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public JLabel getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(JLabel titleLabel) {
        this.titleLabel = titleLabel;
    }

    public JTextField getAge() {
        return age;
    }

    public void setAge(JTextField age) {
        this.age = age;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public void setBackButton(JButton backButton) {
        this.backButton = backButton;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    public void setRegisterButton(JButton registerButton) {
        this.registerButton = registerButton;
    }

    public JButton getClearButton() {
        return clearButton;
    }

    public void setClearButton(JButton clearButton) {
        this.clearButton = clearButton;
    }

    public JPanel getTitlePane() {
        return titlePane;
    }

    public void setTitlePane(JPanel titlePane) {
        this.titlePane = titlePane;
    }

    public JPasswordField getRepeatRassword() {
        return repeatRassword;
    }

    public void setRepeatRassword(JPasswordField repeatRassword) {
        this.repeatRassword = repeatRassword;
    }

    public JPanel getInputPane() {
        return inputPane;
    }

    public void setInputPane(JPanel inputPane) {
        this.inputPane = inputPane;
    }

    public JPanel getBackPane() {
        return backPane;
    }

    public void setBackPane(JPanel backPane) {
        this.backPane = backPane;
    }

    public JPanel getButtonPane() {
        return buttonPane;
    }

    public void setButtonPane(JPanel buttonPane) {
        this.buttonPane = buttonPane;
    }

    public JRadioButton getMaleButton() {
        return maleButton;
    }

    public void setMaleButton(JRadioButton maleButton) {
        this.maleButton = maleButton;
    }

    public JRadioButton getFemaleButton() {
        return femaleButton;
    }

    public void setFemaleButton(JRadioButton femaleButton) {
        this.femaleButton = femaleButton;
    }

    public JPanel getGenderPane() {
        return genderPane;
    }

    public void setGenderPane(JPanel genderPane) {
        this.genderPane = genderPane;
    }

    public JLabel getUsernameLabel() {
        return usernameLabel;
    }

    public void setUsernameLabel(JLabel usernameLabel) {
        this.usernameLabel = usernameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JLabel getRepeatPasswordLabel() {
        return repeatPasswordLabel;
    }

    public void setRepeatPasswordLabel(JLabel repeatPasswordLabel) {
        this.repeatPasswordLabel = repeatPasswordLabel;
    }

    public JLabel getAgeLabel() {
        return ageLabel;
    }

    public void setAgeLabel(JLabel ageLabel) {
        this.ageLabel = ageLabel;
    }

    public JLabel getGenderLabel() {
        return genderLabel;
    }

    public void setGenderLabel(JLabel genderLabel) {
        this.genderLabel = genderLabel;
    }

    public JLabel getUsernameAlert() {
        return usernameAlert;
    }

    public void setUsernameAlert(JLabel usernameAlert) {
        this.usernameAlert = usernameAlert;
    }

    public JLabel getPasswordAlert() {
        return passwordAlert;
    }

    public void setPasswordAlert(JLabel passwordAlert) {
        this.passwordAlert = passwordAlert;
    }

    public JLabel getRepeatPasswordAlert() {
        return repeatPasswordAlert;
    }

    public void setRepeatPasswordAlert(JLabel repeatPasswordAlert) {
        this.repeatPasswordAlert = repeatPasswordAlert;
    }

    public JLabel getAgeAlert() {
        return ageAlert;
    }

    public void setAgeAlert(JLabel ageAlert) {
        this.ageAlert = ageAlert;
    }

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public void setButtonGroup(ButtonGroup buttonGroup) {
        this.buttonGroup = buttonGroup;
    }

}