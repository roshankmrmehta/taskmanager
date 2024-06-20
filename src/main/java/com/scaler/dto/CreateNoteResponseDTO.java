package com.scaler.dto;

import com.scaler.entities.NoteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class CreateNoteResponseDTO {

    private Integer taskId;
    private NoteEntity note;
}
