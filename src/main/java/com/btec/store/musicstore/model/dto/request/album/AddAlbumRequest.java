package com.btec.store.musicstore.model.dto.request.album;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAlbumRequest {

    private String name;
    private String image;
    private String description;
}
