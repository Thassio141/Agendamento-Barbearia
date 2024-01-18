package br.com.schedule.auth;

import br.com.schedule.models.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
