package com.br.scheduling.dtos;

import com.br.scheduling.models.enums.UserType;

public record UserDTO(Long id, String name, String email, String cellphoneNumber, UserType userType) {
}
