package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.User;
import com.app.ecom.service.UserService;
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
    public ResponseEntity<UserResponse> getUsers(@PathVariable Long id) {
        return userService.fetchUsers(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
        return ResponseEntity.ok("User added successfully");

    }

    @PutMapping("{id}") // Don't forget your HTTP mapping annotation!
    public ResponseEntity<String> updateUser(@RequestBody UserRequest updateUserRequest, @PathVariable Long id) {
        boolean updated = userService.updateUser(updateUserRequest, id);

        if (updated) {
            return ResponseEntity.ok("User Updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
