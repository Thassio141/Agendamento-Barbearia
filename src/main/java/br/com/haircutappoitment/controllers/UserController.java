package br.com.haircutappoitment.controllers;

import br.com.haircutappoitment.domain.dtos.user.UserCreateDto;
import br.com.haircutappoitment.domain.dtos.user.UserDto;
import br.com.haircutappoitment.domain.dtos.user.UserUpdateDto;
import br.com.haircutappoitment.domain.enums.UserRole;
import br.com.haircutappoitment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUserById(id),HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<UserDto>> findAllPageable(@RequestParam Integer page, @RequestParam Integer size){
        return new ResponseEntity<>(userService.findAllPageable(page,size),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserUpdateDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto userUpdateDto){
        return new ResponseEntity<>(userService.updateUser(id,userUpdateDto),HttpStatus.OK);
    }

    @PutMapping("/updaterole/{id}")
    public ResponseEntity<UserCreateDto> updateUserRole(@PathVariable Long id, @RequestParam UserRole userRole){
        return new ResponseEntity<>(userService.updateUserRole(id,userRole),HttpStatus.OK);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity<UserDto> softDeleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.softDeleteUser(id),HttpStatus.OK);
    }

    @PutMapping("/activeuser/{id}")
    public ResponseEntity<UserDto> activeUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.activeUser(id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
