package xin.utong.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import xin.utong.model.UserVo;

/**
 * user dao
 * Created by yutong on 2017/7/11.
 */
@Repository
public interface UserRepository extends MongoRepository<UserVo, String> {
    UserVo findUserVoByUsername(final String username);
}
