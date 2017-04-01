package my.example.cassandra.nativeapi.web;

import my.example.cassandra.common.ExtendedUserAddressViewModel;
import my.example.cassandra.common.UserAddressViewModel;
import my.example.cassandra.nativeapi.entity.User;
import my.example.cassandra.nativeapi.service.UserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author olch0615
 *         Date: 3/30/2017
 *         Time: 5:27 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ConversionService conversionService;

    public UserController(UserService userService, ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Map<String, String> saveUser(@RequestBody UserAddressViewModel user) {
        userService.saveUser(conversionService.convert(user, User.class));

        return Collections.singletonMap("status", "true");
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    @SuppressWarnings("unchecked")
    public List<ExtendedUserAddressViewModel> getAll() {
        return (List<ExtendedUserAddressViewModel>) conversionService.convert(
                userService.getAll(),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(User.class)),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(ExtendedUserAddressViewModel.class))
        );
    }

}