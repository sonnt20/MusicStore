package com.btec.store.musicstore.model.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultUpload {
    private Long idFile;
    private String name;
    private String path;
}
