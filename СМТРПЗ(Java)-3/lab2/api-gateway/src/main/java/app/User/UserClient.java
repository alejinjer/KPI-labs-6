package app.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "eureka-client")
public interface UserClient {

    @GetMapping("/")
    String getInstanceId();

    // read
    @GetMapping("/users")
    String getUserById(@RequestParam(value = "id", required = false) Integer id);

    // create
    @PostMapping("/users")
    public Boolean addUser(@RequestBody
                           @RequestParam(value = "id", required = true) Integer id,
                           @RequestParam(value = "name", required = true) String name,
                           @RequestParam(value = "surname", required = true) String surname,
                           @RequestParam(value = "email", required = true) String email,
                           @RequestParam(value = "gender", required = true) String gender,
                           @RequestParam(value = "country", required = true) String country);

    // update
    @PutMapping("/users")
    public Boolean updateUser(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "surname", required = true) String surname,
                              @RequestParam(value = "email", required = true) String email,
                              @RequestParam(value = "gender", required = true) String gender,
                              @RequestParam(value = "country", required = true) String country);

    // delete
    @DeleteMapping("/users")
    public Boolean deleteUser(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id);
}
