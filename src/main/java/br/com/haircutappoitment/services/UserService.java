package br.com.haircutappoitment.services;

import java.util.List;
import java.util.stream.Collectors;

import br.com.haircutappoitment.exceptions.NotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.haircutappoitment.domain.dtos.user.UserCreateDto;
import br.com.haircutappoitment.domain.dtos.user.UserDto;
import br.com.haircutappoitment.domain.entities.UserEntity;
import br.com.haircutappoitment.domain.enums.ActivityStatus;
import br.com.haircutappoitment.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public UserCreateDto updateUser(Long id, UserCreateDto userCreateDto){
        UserEntity userEntity = findByIdEntity(id);
        
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.map(userCreateDto, userEntity);

        userRepository.save(userEntity);

        return userCreateDto;
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
        //ToDo message error
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find user with id:"+ id));
    }

    private UserEntity convertDtoToEntity(UserDto userDto){
        return modelMapper.map(userDto, UserEntity.class);
    }

    private UserDto convertEntityToDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserDto.class);
    }

    private UserEntity convertCreateDtoToEntity(UserCreateDto userCreateDto){
        return modelMapper.map(userCreateDto, UserEntity.class);
    }

    private UserCreateDto convertEntityToCreateDto(UserEntity userEntity){
        return modelMapper.map(userEntity, UserCreateDto.class);
    }
}
