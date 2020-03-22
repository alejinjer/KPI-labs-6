package app;

import app.User.UserClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
@RestController
@EnableAutoConfiguration
public class FeignClientApplication {
    @Autowired
    UserClient userClient;

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }

    @GetMapping("/")
    public String getInstanceId() {
        return userClient.getInstanceId();
    }

    // read
    @GetMapping("/users")
    public String getUserById(@RequestParam(value = "id", required = false) Integer id) {
        System.out.println("Response from service: " + userClient.getInstanceId());
        return userClient.getUserById(id);
    }

    // create
    @PostMapping("/users")
    public Boolean addUser(@RequestBody
                           @RequestParam(value = "id", required = true) Integer id,
                           @RequestParam(value = "name", required = true) String name,
                           @RequestParam(value = "surname", required = true) String surname,
                           @RequestParam(value = "email", required = true) String email,
                           @RequestParam(value = "gender", required = true) String gender,
                           @RequestParam(value = "country", required = true) String country) {
        return userClient.addUser(id, name, surname, email, gender, country);
    }

    // update
    @PutMapping("/users")
    public Boolean updateUser(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "surname", required = true) String surname,
                              @RequestParam(value = "email", required = true) String email,
                              @RequestParam(value = "gender", required = true) String gender,
                              @RequestParam(value = "country", required = true) String country) {
        return userClient.updateUser(id, name, surname, email, gender, country);
    }

    // delete
    @DeleteMapping("/users")
    public Boolean deleteUser(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id) {
        return userClient.deleteUser(id);
    }

}
