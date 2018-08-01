import java.io.Serializable;

/**
 * 服务器配置类
 */
public class ConfigInfo implements Serializable {
    public String auth;
    public String server;
    public String mailSendHost;

    public String account;
    public String password;
}
