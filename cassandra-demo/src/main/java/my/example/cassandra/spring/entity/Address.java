package my.example.cassandra.spring.entity;

import org.springframework.data.cassandra.mapping.UserDefinedType;

@UserDefinedType("address")
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
}
