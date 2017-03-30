package my.example.cassandra.entity;

import com.datastax.driver.mapping.annotations.UDT;
import my.example.cassandra.CassandraConstants;

/**
 * @author olch0615
 *         Date: 3/29/2017
 *         Time: 11:53 AM
 */
@UDT(keyspace = CassandraConstants.KEYSPACE, name = "address")
public class Address {

    private String city;
    private String street;
    private int house;

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Address setStreet(String street) {
        this.street = street;
        return this;
    }

    public int getHouse() {
        return house;
    }

    public Address setHouse(int house) {
        this.house = house;
        return this;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house=" + house +
                '}';
    }
}
