package my.example.cassandra.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author olch0615
 *         Date: 3/29/2017
 *         Time: 3:06 PM
 */
public class MockUser {

    private static final Gson gson = new Gson();

    private static Type usersTypeToken = new TypeToken<List<MockUser>>() {
    }.getType();

    private String name;
    private int age;
    private String city;
    private String street;
    private int house;

    public static List<User> loadUsers(Path path) throws IOException {
        List<User> users = Files.walk(path)
                .filter(Files::isRegularFile)
                .flatMap(MockUser::extractUsers)
                .collect(Collectors.toList());

        return users;
    }

    private static Stream<User> extractUsers(Path file) {
        try (BufferedReader reader = Files.newBufferedReader(file)) {

            return ((List<MockUser>) gson.fromJson(reader, usersTypeToken))
                    .stream()
                    .map(MockUser::toUser);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static User toUser(MockUser mockUser) {
        Address address = new Address()
                .setCity(mockUser.city)
                .setHouse(mockUser.house)
                .setStreet(mockUser.street);

        return new User()
                .setId(UUID.randomUUID())
                .setAddress(address)
                .setName(mockUser.name)
                .setAge(mockUser.age);
    }
}