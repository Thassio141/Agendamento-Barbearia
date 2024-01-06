package com.br.scheduling.controllers;

import com.br.scheduling.dtos.UserDTO;
import com.br.scheduling.services.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/page")
    public List<UserDTO> pageSearchUser(){
        //TODO
        return userService.pageSearchUser();
    }
    @PostMapping("/create")
    public UserDTO createUser() {
        //TODO
        return userService.createUser();
    }

    @PutMapping("/update/{id}")
    public UserDTO updateUser(@PathVariable Long id)
    {
        //TODO
        return userService.updateUser();
    }

    @PutMapping("/softdelete/{id}")
    public UserDTO softDelete(@PathVariable Long id){
        //TODO
        return userService.softDeleteUser();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        //TODO
        userService.deleteUser(id);
    }
}
