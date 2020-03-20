package com.eurekaclient.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Value("${eureka.instance.instanceId}")
    private Integer instanceId;

    @GetMapping("/")
    public Integer getInstanceId() {
        return instanceId;
    }

    // read
    @GetMapping("/user")
    public String getUserById(@RequestParam(value = "id", required = false) Integer id) {

        List<User> resultList = new ArrayList<>();
        if (id == null) {
            Iterable<User> result = userRepository.findAll();
            result.forEach(resultList::add);
        }
        else {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                resultList.add(user.get());
            }
        }
        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(resultList);
        return prettyJson;
    }

    // create
    @PostMapping("/user")
    public Boolean addCustomer(@RequestBody
                               @RequestParam(value = "id", required = true) Integer id,
                               @RequestParam(value = "name", required = true) String name,
                               @RequestParam(value = "surname", required = true) String surname,
                               @RequestParam(value = "email", required = true) String email,
                               @RequestParam(value = "gender", required = true) String gender,
                               @RequestParam(value = "country", required = true) String country) {
        Optional<User> test = userRepository.findById(id);
        if (test.isPresent()) {
            return false;
        }
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setGender(gender);
        user.setCountry(country);
        userRepository.save(user);

        return true;
    }

    // update
    @PutMapping("/user")
    public Boolean updateCustomer(@RequestBody
                                      @RequestParam(value = "id", required = true) Integer id,
                                  @RequestParam(value = "name", required = true) String name,
                                  @RequestParam(value = "surname", required = true) String surname,
                                  @RequestParam(value = "email", required = true) String email,
                                  @RequestParam(value = "gender", required = true) String gender,
                                  @RequestParam(value = "country", required = true) String country) {
        Optional<User> test = userRepository.findById(id);
        if (!test.isPresent()) {
            return false;
        }
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setGender(gender);
        user.setCountry(country);
        userRepository.save(user);

        return true;
    }

    // delete
    @DeleteMapping("/user")
    public Boolean deleteCustomer(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id) {
        Optional<User> test = userRepository.findById(id);
        if (!test.isPresent()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}