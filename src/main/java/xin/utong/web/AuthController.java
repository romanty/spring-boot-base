package xin.utong.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.social.TwitterProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xin.utong.configurer.security.JwtAuthenticationRequest;
import xin.utong.configurer.security.JwtAuthenticationResponse;
import xin.utong.model.Message;
import xin.utong.model.UserVo;
import xin.utong.service.AuthService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 鉴权入口
 * Created by yutong on 2017/7/10.
 */
@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @Resource
    private RedisTemplate<String, Message> redisTemplate;

    @PostMapping("${jwt.route.authentication.path}")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtAuthenticationRequest request) {
        Message<UserVo> message = new Message<>();
        message.setId(System.currentTimeMillis());
        redisTemplate.convertAndSend("hello-channel2", message);
        final String token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @GetMapping("${jwt.route.authentication.refresh}")
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = authService.refreshToken(token);
        if (refreshToken == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshToken));
        }
    }

    @PostMapping("${jwt.route.authentication.register}")
    public UserVo register(@RequestBody UserVo userToAdd) {
        return authService.register(userToAdd);
    }
}
