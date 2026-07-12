package com.society.maintenance.controller;


import com.society.maintenance.entity.User;
import com.society.maintenance.service.UserService;


import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {


    private final UserService userService;


    public UserController(UserService userService){
        this.userService=userService;
    }



    @GetMapping
    public List<User> getUsers(){

        return userService.getAllUsers();

    }



    @GetMapping("/{id}")
    public User getUser(
            @PathVariable Long id
    ){

        return userService.getUserById(id);

    }



    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User user
    ){

        return userService.updateUser(id,user);

    }



    @DeleteMapping("/{id}")
    public String deleteUser(
            @PathVariable Long id
    ){

        userService.deleteUser(id);

        return "User deleted";

    }

}