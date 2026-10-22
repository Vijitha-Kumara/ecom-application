package com.app.ecom.service;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.model.Address;
import com.app.ecom.model.AddressDTO;
import com.app.ecom.model.User;
import com.app.ecom.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepostory;


    public List<UserResponse> fetchAllUsers() {
        return userRepostory.findAll().stream()
                .map(this::mapToUseResponse)
                .collect(Collectors.toList());

    }

    public void addUser(UserRequest userRequest) {
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepostory.save(user);
    }


    public Optional<UserResponse> fetchUsers(Long id) {
        return userRepostory.findById(id)
                .map(this::mapToUseResponse);
    }

    public boolean updateUser(UserRequest updatedUserRequest, Long id) {
        return userRepostory.findById(id)
                .map(exitingUser -> {
                    updateUserFromRequest(exitingUser, updatedUserRequest);
                    userRepostory.save(exitingUser);
                    return true;
                }).orElse(false);


    }


    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        if (user.getAddress() != null) {
            Address address = new Address();
            address.setStreet(userRequest.getAddressDTO().getStreet());
            address.setCity(userRequest.getAddressDTO().getCity());
            address.setState(userRequest.getAddressDTO().getState());
            address.setCounty(userRequest.getAddressDTO().getCounty());
            address.setZipCode(userRequest.getAddressDTO().getZipCode());

        }

    }

    private UserResponse mapToUseResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());

        if (response.getAddressDTO() != null) {
            AddressDTO addressDto = new AddressDTO();
            addressDto.setStreet(user.getAddress().getStreet());
            addressDto.setCity(user.getAddress().getCity());
            addressDto.setCounty(user.getAddress().getCounty());
            addressDto.setState(user.getAddress().getState());
            addressDto.setZipCode(user.getAddress().getZipCode());


        }
        return response;
    }
}
