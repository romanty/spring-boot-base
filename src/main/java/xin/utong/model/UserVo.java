package xin.utong.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;
import java.util.List;

/**
 * user
 * Created by yutong on 2017/7/11.
 */
@Data
public class UserVo {
    @Id
    private String id;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String username;

    private String password;

    private String email;

    private Date lastPasswordResetDate;

    private List<String> roles;
}
