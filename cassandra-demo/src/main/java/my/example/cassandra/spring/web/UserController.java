package my.example.cassandra.spring.web;

import my.example.cassandra.common.ExtendedUserAddressViewModel;
import my.example.cassandra.common.UserAddressViewModel;
import my.example.cassandra.spring.entity.User;
import my.example.cassandra.spring.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author olch0615
 *         Date: 3/30/2017
 *         Time: 5:27 PM
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public UserController(UserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Map<String, String> saveUser(@RequestBody UserAddressViewModel userModel) {
        User user = conversionService.convert(userModel, User.class);
        user.setId(UUID.randomUUID());

        userRepository.save(user);

        return Collections.singletonMap("status", "true");
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    public List<ExtendedUserAddressViewModel> getAll() {
        List<ExtendedUserAddressViewModel> response = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            response.add(conversionService.convert(user, ExtendedUserAddressViewModel.class));
        }

        return response;
    }
}