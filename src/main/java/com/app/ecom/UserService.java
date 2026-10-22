package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepostory;


    public List<User> fetchAllUsers() {
        return userRepostory.findAll();
    }

    public void addUser(User user) {
        userRepostory.save(user);
    }


    public Optional<User> fetchUsers(Long id) {
        return userRepostory.findById(id);
    }

    public boolean updateUser(User updatedUser, Long id) {
        return userRepostory.findById(id)
                .map(exitingUser -> {
                    exitingUser.setFirstName(updatedUser.getFirstName());
                    exitingUser.setLastName(updatedUser.getLastName());
                    userRepostory.save(exitingUser);
                    return true;
                }).orElse(false);


    }


}
