package my.example.cassandra.nativeapi.misc;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class CassandraExamples {

    private static final Random random = new Random();

    public static void main(String[] args) {
        selectWithUDT();
    }

    private static void selectWithUDT() {
        withSession(session -> {
            for (Row group : session.execute("SELECT * FROM groups")) {

                System.out.println(group);
            }
        });
    }

    public static void insertUTD() {
        withSession(session -> {

            UserType personUDT = session.getCluster()
                    .getMetadata()
                    .getKeyspace("test_keyspace")
                    .getUserType("person");

            BoundStatement statement = session.prepare("" +
                    "INSERT INTO groups(name, course, leader, students) " +
                    "VALUES(:name, :course, :leader, :students)"
            ).bind();

            UDTValue elize = personUDT.newValue()
                    .setString("name", "Elize")
                    .setInt("age", 21);

            UDTValue katia = personUDT.newValue()
                    .setString("name", "Katia")
                    .setInt("age", 22);
            UDTValue dima = personUDT.newValue()
                    .setString("name", "Dima")
                    .setInt("age", 23);

            statement.setString("name", "FB-61m");
            statement.setInt("course", 5);
            statement.setUDTValue("leader", elize);
            statement.setList("students", Arrays.asList(katia, dima));

            session.execute(statement);
        });
    }

    public static void insertTest() {
        withSession(session -> {
            PreparedStatement statement = session.prepare("" +
                    "INSERT INTO test(pk1, pk2, ck1, ck2, col1, col2) " +
                    "VALUES(?, ?, ?, ?, ?, ?)"
            );

            for (int i = 0; i < 100_000; i++) {
                session.execute(statement.bind(
                        random.nextInt(50),
                        randomString(),
                        random.nextInt(50),
                        randomString(),
                        random.nextInt(50),
                        randomString()
                ));
            }
        });
    }

    public static void selectTest() {

        Select select = QueryBuilder.select()
                .from("test")
                .where(QueryBuilder.eq("pk1", 1))
                .and(QueryBuilder.eq("pk2", "pk_val1"))
                .orderBy(QueryBuilder.desc("ck1"));

        withSession(session -> {
            ResultSet result = session.execute("SELECT * FROM test");
            result.forEach(System.out::println);

            System.out.println("-------------------------------------------------------");

            session.execute(select).forEach(System.out::println);
        });
    }

    private static void withSession(Consumer<Session> sessionConsumer) {
        try (Cluster cluster = Cluster.builder()
                .addContactPoint("localhost")
                .build()) {

            Session session = cluster.connect("test_keyspace");
            sessionConsumer.accept(session);
        }
    }

    private static String randomString() {
        String alphabet = "abcdefgh";

        return random
                .ints(10, 0, alphabet.length())
                .mapToObj(i -> Character.toString(alphabet.charAt(i)))
                .reduce(String::concat)
                .get();
    }

    private static void printColumnDefs(ColumnDefinitions definitions) {
        for (ColumnDefinitions.Definition definition : definitions) {
            DataType type = definition.getType();
            System.out.print(definition.getName() + "\t" + type);

            if (type instanceof UserType) {
                UserType userType = (UserType) type;
                System.out.print(userType.getFieldNames());
            }

            System.out.println();
        }
    }
}
