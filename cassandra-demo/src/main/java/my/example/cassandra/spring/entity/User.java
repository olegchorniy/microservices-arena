package my.example.cassandra.spring.entity;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table("users")
public class User {

    @PrimaryKeyColumn(name = "id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @PrimaryKeyColumn(name = "name", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String name;

    private int age;
    private Address address;

    public UUID getId() {
        return id;
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public User setAddress(Address address) {
        this.address = address;
        return this;
    }
}
