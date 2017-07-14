package xin.utong.configurer.security;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 请求对象
 * Created by yutong on 2017/7/10.
 */
@Setter
@Getter
public class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 814129784442870847L;

    private String username;
    @NotBlank(message = "password cannot be empty")
    private String password;
}
