package app;

import app.User.UserClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Component
public class ProxyService {
    private static final String BACKEND_A = "eureka-client";

    @Autowired
    private UserClient userClient;

    @ResponseBody
    Map<String, String> getConfig() {
        return userClient.getConfig();
    }

    String getInstanceId() {
        return userClient.getInstanceId();
    }

    // read
    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    String getUserById(@RequestParam(value = "id", required = false) Integer id) {
        return userClient.getUserById(id);
    }

    public String fallback(Throwable e) {
        return new String("Fallback");
    }

    // create
    @Retry(name = BACKEND_A)
    public ResponseEntity<String> addUser(@RequestBody
                                          @RequestParam(value = "id", required = true) Integer id,
                                          @RequestParam(value = "name", required = true) String name,
                                          @RequestParam(value = "surname", required = true) String surname,
                                          @RequestParam(value = "email", required = true) String email,
                                          @RequestParam(value = "gender", required = true) String gender,
                                          @RequestParam(value = "country", required = true) String country) {
        return userClient.addUser(id,name,surname,email,gender,country);
    }

    // update
    @Retry(name = BACKEND_A)
    public ResponseEntity<String> updateUser(@RequestBody
                                             @RequestParam(value = "id", required = true) Integer id,
                                             @RequestParam(value = "name", required = true) String name,
                                             @RequestParam(value = "surname", required = true) String surname,
                                             @RequestParam(value = "email", required = true) String email,
                                             @RequestParam(value = "gender", required = true) String gender,
                                             @RequestParam(value = "country", required = true) String country) {
        return userClient.updateUser(id,name,surname,email,gender,country);
    }

    // delete
    public ResponseEntity<String> deleteUser(@RequestBody
                                             @RequestParam(value = "id", required = true) Integer id) {
        return userClient.deleteUser(id);
    }
}