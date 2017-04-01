package my.example.cassandra.spring.repository;

import my.example.cassandra.spring.entity.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository<User> {
}
