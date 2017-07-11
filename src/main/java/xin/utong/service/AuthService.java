package xin.utong.service;

import xin.utong.model.UserVo;

/**
 * 鉴权服务
 * Created by yutong on 2017/7/10.
 */
public interface AuthService {
    UserVo register(UserVo userToAdd);
    String login(String username, String password);
    String refreshToken(String oldToken);
}
