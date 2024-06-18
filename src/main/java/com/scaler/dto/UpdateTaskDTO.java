package com.scaler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateTaskDTO {

    private String description;
    private String deadline;
    private Boolean completed;
}
