package xin.utong.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xin.utong.model.UserVo;
import xin.utong.repository.UserRepository;

import java.util.List;

/**
 * user vo controller
 * Created by yutong on 2017/7/11.
 */
@RestController
@RequestMapping("/users")
public class UserVoController {
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserVo> getUsers() {
        return userRepository.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    UserVo addUser(@RequestBody UserVo addedUser) {
        return userRepository.insert(addedUser);
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public UserVo getUser(@PathVariable String id) {
        return userRepository.findOne(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    UserVo updateUser(@PathVariable String id, @RequestBody UserVo updatedUser) {
        updatedUser.setId(id);
        return userRepository.save(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    UserVo removeUser(@PathVariable String id) {
        UserVo deletedUser = userRepository.findOne(id);
        userRepository.delete(id);
        return deletedUser;
    }

    @PostAuthorize("returnObject.username == principal.username or hasRole('ROLE_ADMIN')")
    @GetMapping("/")
    public UserVo getUserByUsername(@RequestParam(value="username") String username) {
        return userRepository.findUserVoByUsername(username);
    }
}
