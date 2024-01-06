package com.br.scheduling.services;

import com.br.scheduling.dtos.UserDTO;
import com.br.scheduling.dtos.UserDTOMapper;
import com.br.scheduling.models.User;
import com.br.scheduling.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public Optional<User> findUserById(Long id){
        //TODO
        return userRepository.findById(id);
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public List<UserDTO> pageSearchUser(){
        return null;
    }
    public UserDTO createUser(){
        //TODO
        return null;
    }

    public UserDTO updateUser(){
        //TODO
        return null;
    }

    public UserDTO softDeleteUser(){
        //TODO
        return null;
    }

    public void deleteUser(Long id){
        //TODO verify if the user exist
        userRepository.deleteById(id);
    }

    public Optional<User> verifyUserExistById(Long id){
        //TODO
        return userRepository.findById(id);
    }
}
