package my.example.cassandra.spring.entity;

import my.example.cassandra.common.UserAddressViewModel;
import org.springframework.core.convert.converter.Converter;

public class UserAddressViewModelToUserConverter implements Converter<UserAddressViewModel, User> {

    @Override
    public User convert(UserAddressViewModel source) {
        Address address = new Address()
                .setCity(source.getCity())
                .setStreet(source.getStreet())
                .setHouse(source.getHouse());

        User user = new User()
                .setAddress(address)
                .setAge(source.getAge())
                .setName(source.getName());

        return user;
    }
}
