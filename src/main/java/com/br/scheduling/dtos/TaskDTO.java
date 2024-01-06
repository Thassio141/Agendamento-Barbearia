package com.br.scheduling.dtos;

import com.br.scheduling.models.enums.StatusTask;

public record TaskDTO(Long id, String name, Double price, StatusTask statusTask) {
}
