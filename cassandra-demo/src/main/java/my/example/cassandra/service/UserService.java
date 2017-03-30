package my.example.cassandra.service;

import com.datastax.driver.mapping.Mapper;
import my.example.cassandra.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author olch0615
 *         Date: 3/30/2017
 *         Time: 5:09 PM
 */
@Service
public class UserService {

    private final UserAccessor userAccessor;
    private final Mapper<User> userMapper;

    public UserService(UserAccessor userAccessor, Mapper<User> userMapper) {
        this.userAccessor = userAccessor;
        this.userMapper = userMapper;
    }

    public List<User> getAll() {
        return userAccessor.getAll().all();
    }

    public void saveUser(User user) {
        userMapper.save(user);
    }
}