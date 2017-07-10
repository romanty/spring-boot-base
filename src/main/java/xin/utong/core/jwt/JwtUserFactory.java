package xin.utong.core.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import xin.utong.model.User;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by apple on 2017/7/7.
 */
public final class JwtUserFactory {
    private JwtUserFactory() {

    }

    public static JwtUser createUser(User user) {
        return new JwtUser(user.getId().toString(), user.getAccount(), user.getPassword(), user.getEmail(),
                mapToGrantedAuthorities(user.getRoles()), user.getCreatetime());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
