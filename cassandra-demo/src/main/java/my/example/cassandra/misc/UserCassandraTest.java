package my.example.cassandra.misc;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import my.example.cassandra.CassandraConstants;
import my.example.cassandra.entity.User;
import my.example.cassandra.service.UserAccessor;

import java.io.IOException;
import java.util.function.Consumer;

/*
* Properties changed in cassandra.yaml on each node:
*   1) seed_provider.parameters.seeds: "127.0.0.1"
*   2) listen_address: 127.0.0.*
*   3) rpc_address: 127.0.0.*
*   4) cluster_name [optional]
*   5) JMX port in cassandra.bat
*   6) auto_bootstrap: false - for seed node
*
*   !!! Don't touch any port !!!
* */
public class UserCassandraTest {

    public static void main(String[] args) throws IOException {
        simpleSelectWithConsistencyLevel();
    }

    public static void importUsers(Iterable<User> users) {
        withSession(session -> {
            MappingManager manager = new MappingManager(session);
            Mapper<User> mapper = manager.mapper(User.class);

            for (User user : users) {
                mapper.save(user);
            }

            System.out.println("Done");
        });
    }

    public static void insertAndReadUser(User user) {
        withSession(session -> {
            MappingManager manager = new MappingManager(session);
            Mapper<User> mapper = manager.mapper(User.class);

            mapper.save(user);

            mapper.map(session.execute("SELECT * FROM users")).forEach(System.out::println);
        });
    }

    public static void mappedSelect() {
        withSession(session -> {
            MappingManager manager = new MappingManager(session);
            Mapper<User> userMapper = manager.mapper(User.class);

            ResultSet results = session.execute("SELECT * FROM users");
            Result<User> users = userMapper.map(results);

            System.out.println("Mapped select");
            for (User user : users) {
                System.out.println("User : " + user);
            }
        });
    }

    public static void fetchSizeTest() {
        withSession(session -> {
            session.getCluster().getConfiguration().getQueryOptions().setFetchSize(400);

            MappingManager manager = new MappingManager(session);
            UserAccessor accessor = manager.createAccessor(UserAccessor.class);

            Result<User> users = accessor.getAll();
            System.out.println("Accessor select");

            int counter = 0;
            for (User user : users) {
                counter++;

                if (counter % 300 == 0 || counter % 500 == 0) {
                    System.out.println(counter + ": available = " + users.getAvailableWithoutFetching());
                }
            }
        });
    }

    public static void accessorSelect() {
        withSession(session -> {
            MappingManager manager = new MappingManager(session);
            UserAccessor accessor = manager.createAccessor(UserAccessor.class);

            Result<User> users = accessor.getAll();

            System.out.println("Accessor select");
            for (User user : users) {
                System.out.println("User : " + user);
            }
        });
    }

    public static void simpleSelect() {
        withSession(session -> {
            session.execute("SELECT * FROM users").forEach(System.out::println);
        });
    }

    public static void simpleSelectWithConsistencyLevel() {
        withSession(session -> {
            SimpleStatement statement = new SimpleStatement("SELECT * FROM users LIMIT 10");
            statement.setConsistencyLevel(ConsistencyLevel.TWO);

            session.execute(statement).forEach(System.out::println);
        });
    }

    private static void withSession(Consumer<Session> sessionConsumer) {
        try (Cluster cluster = buildCluster()) {
            sessionConsumer.accept(cluster.connect(CassandraConstants.KEYSPACE));
        }
    }

    private static Cluster buildCluster() {
        return Cluster.builder()
                .addContactPoint("127.0.0.1")
                .build();
    }
}
