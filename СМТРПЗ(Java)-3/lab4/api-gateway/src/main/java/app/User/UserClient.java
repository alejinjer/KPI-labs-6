package app.User;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(value = "eureka-client")
public interface UserClient {

    @GetMapping("/config")
    @ResponseBody
    Map<String, String> getConfig();

    @GetMapping("/")
    String getInstanceId();

    // read
    @GetMapping("/users")
    String getUserById(@RequestParam(value = "id", required = false) Integer id);

    // create
    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody
                           @RequestParam(value = "id", required = true) Integer id,
                                          @RequestParam(value = "name", required = true) String name,
                                          @RequestParam(value = "surname", required = true) String surname,
                                          @RequestParam(value = "email", required = true) String email,
                                          @RequestParam(value = "gender", required = true) String gender,
                                          @RequestParam(value = "country", required = true) String country);

    // update
    @PutMapping("/users")
    public ResponseEntity<String> updateUser(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id,
                              @RequestParam(value = "name", required = true) String name,
                              @RequestParam(value = "surname", required = true) String surname,
                              @RequestParam(value = "email", required = true) String email,
                              @RequestParam(value = "gender", required = true) String gender,
                              @RequestParam(value = "country", required = true) String country);

    // delete
    @DeleteMapping("/users")
    public ResponseEntity<String> deleteUser(@RequestBody
                              @RequestParam(value = "id", required = true) Integer id);
}
