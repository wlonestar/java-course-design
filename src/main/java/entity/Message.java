package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * <h1>消息实体类</h1>
 *
 * <p>记录消息的发送人（用户或者系统）、发送时间（转换成字符串存储）、消息内容</p>
 * <p>实现序列化接口，支持对象持久化</p>
 *
 * @author: wjl
 * @date: 2022/1/8 9:13
 * @version: v1.0
 */
public class Message implements Serializable {

    /**
     * <h3>发送人</h3>
     *
     * <p>如果是注册用户，就是用户名</p>
     * <p>如果是系统发送的消息，就是 {@code [系统消息]}</p>
     */
    private String username;

    /**
     * <h3>消息发送时间</h3>
     *
     * <p>记录下消息发送时间，以 {@code 'yyyy-MM-dd'T'HH:mm:ss'} 格式存储到字符串中</p>
     */
    private String time;

    /**
     * <h3>消息内容</h3>
     *
     * <p>记录消息内容，消息内容不能为空或全为空格</p>
     */
    private String content;

    public Message() {
    }

    public Message(String username, String time, String content) {
        this.username = username;
        this.time = time;
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return Objects.equals(getUsername(), message.getUsername()) && Objects.equals(getTime(), message.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getTime());
    }

    @Override
    public String toString() {
        return "'" + username + "' '" + time + "' '" + content + "'";
    }

}
