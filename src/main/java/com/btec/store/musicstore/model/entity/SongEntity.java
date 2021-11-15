package com.btec.store.musicstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;

@Entity
@Table(name = "SONG")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongEntity extends BaseModel {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SONG_NAME")
    private String name;

    @Column(name = "STATUS")
    private Integer status;//0-inactive 1-active

    @Column(name = "LINK_DEMO")
    private String linkDemo;

    @Column(name = "LINK_FULL")
    private String linkFull;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @Column(name = "GEN_DATE")
    private Date genDate;

    @Column(name = "LAST_UPDATED")
    private Date lastUpdate;

}
