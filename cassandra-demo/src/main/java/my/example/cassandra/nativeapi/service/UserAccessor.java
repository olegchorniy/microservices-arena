package my.example.cassandra.nativeapi.service;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import my.example.cassandra.nativeapi.entity.User;

@Accessor
public interface UserAccessor {

    @Query("SELECT * FROM users")
    Result<User> getAll();
}
