package xin.utong.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xin.utong.core.jwt.JwtUserFactory;
import xin.utong.dao.UserMapper;
import xin.utong.model.User;

import javax.annotation.Resource;

/**
 * Created by apple on 2017/7/7.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectByAccount(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
        } else {
            return JwtUserFactory.createUser(user);
        }
    }
}
