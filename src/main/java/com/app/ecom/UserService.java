package com.app.ecom;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    public List<User> fetchAllUsers() {
        return userList;
    }

    public void addUser(User user) {
        user.setId(nextId++);
        userList.add(user);
    }


    public Optional<User> fetchUsers(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public boolean updateUser(User updatedUser, Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .map(exitingUser -> {
                    exitingUser.setFirstName(updatedUser.getFirstName());
                    exitingUser.setLastName(updatedUser.getLastName());
                    return true;
                }).orElse(false);


    }


}
