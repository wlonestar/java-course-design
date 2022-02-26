package com.wjl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * <h1>自定义日志操作类</h1>
 *
 * <p>对关键操作进行日志记录</p>
 * <i>比较简陋，只是方便调试程序</i>
 *
 * @author: wjl
 * @date: 2022/1/8 11:51
 * @version: v1.0
 */
public class LogUtil {

    /**
     * <h2>定义日志文件位置</h2>
     * <p>通过构造方法指定</p>
     */
    private final File file;

    public LogUtil(File file) {
        this.file = file;
    }

    /**
     * <h2>日志记录方法</h2>
     *
     * <p>在传入的字符串前加上当前时间，写入文件并输出到终端</p>
     *
     * @param info info
     */
    public void info(String info) {
        try {
            FileOutputStream outputStream = new FileOutputStream(file, true);
            PrintStream printStream = new PrintStream(outputStream, true, StandardCharsets.UTF_8);
            String log = DateUtil.convertDateToString(new Date()) + " " + info;
            System.out.println(log);
            printStream.println(log);
            printStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
