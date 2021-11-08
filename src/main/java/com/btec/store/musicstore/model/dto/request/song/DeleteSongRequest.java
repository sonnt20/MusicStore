package com.btec.store.musicstore.model.dto.request.song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteSongRequest {
    private Integer id;
}
