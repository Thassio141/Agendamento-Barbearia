package com.br.scheduling.dtos;

import com.br.scheduling.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User,UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getCellphoneNumber(),
                user.getUserType()
        );
    }
}
