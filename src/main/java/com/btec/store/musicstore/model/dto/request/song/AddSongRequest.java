package com.btec.store.musicstore.model.dto.request.song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSongRequest {
    private String name;
    private String releaseDate;
    private Long singerId;
    private Long albumId;
    private String linkFull;
    private String description;
    private String image;
}
