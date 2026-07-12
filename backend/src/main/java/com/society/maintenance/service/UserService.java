package com.society.maintenance.service;


import com.society.maintenance.entity.User;
import com.society.maintenance.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {


    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    // Create User
    public User createUser(User user) {

        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }



    // Get all users
    public List<User> getAllUsers(){

        return userRepository.findAll();

    }



    // Get user by ID
    public User getUserById(Long id){

        return userRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException("User not found")
                );

    }



    // Get user by email
    public User getUserByEmail(String email){

        return userRepository.findByEmail(email)
                .orElseThrow(
                    () -> new RuntimeException("User not found")
                );

    }



    // Update User
    public User updateUser(Long id, User updatedUser){


        User existingUser = getUserById(id);


        existingUser.setName(updatedUser.getName());

        existingUser.setPhone(updatedUser.getPhone());

        existingUser.setApartmentNumber(
                updatedUser.getApartmentNumber()
        );


        return userRepository.save(existingUser);

    }



    // Delete User
    public void deleteUser(Long id){

        User user = getUserById(id);

        userRepository.delete(user);

    }


}