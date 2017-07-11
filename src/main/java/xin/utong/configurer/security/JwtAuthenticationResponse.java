package xin.utong.configurer.security;

import java.io.Serializable;

/**
 * 响应
 * Created by yutong on 2017/7/10.
 */
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 4347475945358258441L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
