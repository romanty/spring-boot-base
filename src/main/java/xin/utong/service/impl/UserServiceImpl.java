package xin.utong.service.impl;

import xin.utong.dao.UserMapper;
import xin.utong.model.User;
import xin.utong.service.UserService;
import xin.utong.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2017/07/06.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;

}
