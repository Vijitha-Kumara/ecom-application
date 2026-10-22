package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<User> getUsers(@PathVariable Long id) {
        return userService.fetchUsers(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User added successfully");

    }

    @PutMapping("{id}") // Don't forget your HTTP mapping annotation!
    public ResponseEntity<String> updateUser(@RequestBody User updateUser, @PathVariable Long id) {
        boolean updated = userService.updateUser(updateUser, id);

        if (updated) {
            return ResponseEntity.ok("User Updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
