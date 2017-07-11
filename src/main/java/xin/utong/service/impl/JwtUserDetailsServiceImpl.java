package xin.utong.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xin.utong.core.jwt.JwtUserFactory;
import xin.utong.model.UserVo;
import xin.utong.repository.UserRepository;

import javax.annotation.Resource;

/**
 * jwt user details
 * Created by apple on 2017/7/7.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserVo user = userRepository.findUserVoByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", s));
        } else {
            return JwtUserFactory.createUser(user);
        }
    }
}
