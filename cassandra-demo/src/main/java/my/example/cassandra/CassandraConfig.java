package my.example.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import my.example.cassandra.entity.User;
import my.example.cassandra.service.UserAccessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author olch0615
 *         Date: 3/30/2017
 *         Time: 5:10 PM
 */
@Configuration
public class CassandraConfig {

    @Value("${cassandra.keyspace}")
    private String keyspace;

    @Value("${cassandra.contact-point}")
    private String contactPoint;

    /* General Cassandra beans */

    @Bean
    public Cluster cluster() {
        return Cluster.builder()
                .addContactPoint(contactPoint)
                .build();
    }

    @Bean
    public Session session() {
        return cluster().connect(keyspace);
    }

    @Bean
    public MappingManager mappingManager() {
        return new MappingManager(session());
    }

    /* User related beans */

    @Bean
    public UserAccessor userAccessor() {
        return mappingManager().createAccessor(UserAccessor.class);
    }

    @Bean
    public Mapper<User> userMapper() {
        return mappingManager().mapper(User.class);
    }
}
