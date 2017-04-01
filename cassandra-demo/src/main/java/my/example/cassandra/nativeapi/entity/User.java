package my.example.cassandra.nativeapi.entity;

import com.datastax.driver.mapping.annotations.ClusteringColumn;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import my.example.cassandra.CassandraConstants;

import java.util.UUID;

/**
 * @author olch0615
 *         Date: 3/29/2017
 *         Time: 11:53 AM
 */
@Table(keyspace = CassandraConstants.KEYSPACE, name = "users")
public class User {

    @PartitionKey
    private UUID id;

    @ClusteringColumn
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}