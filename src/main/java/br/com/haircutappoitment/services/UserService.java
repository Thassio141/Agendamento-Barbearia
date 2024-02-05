package br.com.haircutappoitment.services;

import br.com.haircutappoitment.domain.dtos.user.UserCreateDto;
import br.com.haircutappoitment.domain.dtos.user.UserDto;
import br.com.haircutappoitment.domain.dtos.user.UserUpdateDto;
import br.com.haircutappoitment.domain.entities.UserEntity;
import br.com.haircutappoitment.domain.enums.ActivityStatus;
import br.com.haircutappoitment.domain.enums.UserRole;
import br.com.haircutappoitment.exceptions.NotFoundException;
import br.com.haircutappoitment.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    
    public List<UserDto> findAllUsers(){
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
            .map(this::convertEntityToDto)
            .collect(Collectors.toList());
    }

    public UserDto findUserById(Long id){
        return convertEntityToDto(findByIdEntity(id));
    }

    public Page<UserDto> findAllPageable(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);

        Page<UserEntity> userEntities = userRepository.findAllBy(pageable);

        return userEntities.map(this::convertEntityToDto);
    }

    public UserUpdateDto updateUser(Long id, UserUpdateDto userUpdateDto){
        UserEntity userEntity = findByIdEntity(id);

        modelMapper.map(userUpdateDto, userEntity);

        userRepository.save(userEntity);

        return convertEntityToUpdateDto(userEntity);
    }

    public UserCreateDto updateUserRole(Long id, UserRole userRole){
        UserEntity userEntity = findByIdEntity(id);

        userEntity.setUserRole(userRole);

        userRepository.save(userEntity);

        return convertEntityToCreateDto(userEntity);
    }

    public void deleteUser(Long id){
        UserEntity userEntity = findByIdEntity(id);
        userRepository.delete(userEntity);
    }

    public UserDto softDeleteUser(Long id){
        UserEntity userEntity = findByIdEntity(id);
        userEntity.setUserStatus(ActivityStatus.INACTIVE);
        return convertEntityToDto(userEntity);
    }

    public UserDto activeUser(Long id){
        UserEntity userEntity = findByIdEntity(id);
        userEntity.setUserStatus(ActivityStatus.ACTIVE);
        return convertEntityToDto(userEntity);
    }

    private UserEntity findByIdEntity(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with id:"+ id));
    }

    private UserDto convertEntityToDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }

    private UserCreateDto convertEntityToCreateDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserCreateDto.class);
    }

    private UserUpdateDto convertEntityToUpdateDto(UserEntity userEntity){
        return modelMapper.map(userEntity,UserUpdateDto.class);
    }
}
