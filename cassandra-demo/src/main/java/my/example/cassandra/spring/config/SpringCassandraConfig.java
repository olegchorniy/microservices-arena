package my.example.cassandra.spring.config;

import my.example.cassandra.CassandraConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
public class SpringCassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
        return CassandraConstants.KEYSPACE;
    }
}
