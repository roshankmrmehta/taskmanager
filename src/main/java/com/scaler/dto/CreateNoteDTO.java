package com.scaler.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateNoteDTO {

    private String title;
    private String body;
}
