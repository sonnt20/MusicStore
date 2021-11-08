package com.btec.store.musicstore.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "ALBUMS")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumEntity extends BaseModel {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ALBUM_NAME")
    private String albumName;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "GEN_DATE")
    private Date genDate;

    @Column(name = "LAST_UPDATE")
    private Date latUpdate;
}
