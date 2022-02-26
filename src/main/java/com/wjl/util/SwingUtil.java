package com.wjl.util;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.util.Enumeration;
import java.util.Locale;

/**
 * <h1>Swing 框架的工具类</h1>
 *
 * <p>定义常用方法</p>
 *
 * @author: wjl
 * @date: 2022/1/8 19:06
 * @version: v1.0
 */
public class SwingUtil {

    /**
     * 定义 {@code JFrame} 容器组件的启动方法
     *
     * @param frame frame
     * @param panel panel
     * @param title title
     */
    public static void run(final JFrame frame, final JPanel panel, String title) {
        SwingUtilities.invokeLater(() -> {
            frame.setTitle(title);
            frame.setContentPane(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Constants.ICON_IMAGE));
            frame.pack();
            frame.setLocationRelativeTo(null);
        });
    }

    /**
     * 定义 {@code JDialog} 容器组件的启动方法
     *
     * @param dialog dialog
     * @param title title
     */
    public static void run(final JDialog dialog, String title) {
        SwingUtilities.invokeLater(() -> {
            dialog.setTitle(title);
            dialog.setIconImage(Toolkit.getDefaultToolkit().getImage(Constants.ICON_IMAGE));
            dialog.pack();
            dialog.setLocationRelativeTo(null);
        });
    }

    /**
     * 获取当前容器组件的文字
     *
     * @param fontName fontName
     * @param style style
     * @param size size
     * @param currentFont currentFont
     * @return font
     */
    public static Font getFont(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) {
            return null;
        }
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * 指定全局字体
     *
     * @param font font
     */
    public static void initGlobalFont(Font font) {
        FontUIResource fontUIResource = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontUIResource);
            }
        }
    }

    /**
     * 向 {@code JTextPane} 容器组件中添加组件
     *
     * @param panel panel
     * @param message message
     * @param color color
     * @param fontSize fontSize
     */
    public static void appendToPanel(JTextPane panel, String message, Color color, int fontSize) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();
        AttributeSet set = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        set = styleContext.addAttribute(set, StyleConstants.FontFamily, "楷体");
        set = styleContext.addAttribute(set, StyleConstants.FontFamily, "Verdana");
        set = styleContext.addAttribute(set, StyleConstants.FontSize, fontSize);
        set = styleContext.addAttribute(set, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        panel.setCaretPosition(panel.getDocument().getLength());
        panel.setCharacterAttributes(set, false);
        panel.replaceSelection(message + "\n");
    }

}
