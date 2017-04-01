package my.example.cassandra.spring.entity;

import my.example.cassandra.common.ExtendedUserAddressViewModel;
import org.springframework.core.convert.converter.Converter;

public class UserToExtendedUserAddressViewModelConverter implements Converter<User, ExtendedUserAddressViewModel> {

    @Override
    public ExtendedUserAddressViewModel convert(User source) {
        ExtendedUserAddressViewModel viewModel = new ExtendedUserAddressViewModel();
        viewModel.setId(source.getId().toString());
        viewModel.setAge(source.getAge());
        viewModel.setName(source.getName());
        viewModel.setCity(source.getAddress().getCity());
        viewModel.setStreet(source.getAddress().getStreet());
        viewModel.setHouse(source.getAddress().getHouse());

        return viewModel;
    }
}
