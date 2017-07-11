package xin.utong.configurer.security;

import java.io.Serializable;

/**
 * 请求对象
 * Created by yutong on 2017/7/10.
 */
public class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 814129784442870847L;

    private String username;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public JwtAuthenticationRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JwtAuthenticationRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
