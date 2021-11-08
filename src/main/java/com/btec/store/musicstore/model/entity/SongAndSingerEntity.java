package com.btec.store.musicstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "singer_song_rls")
public class SongAndSingerEntity extends BaseModel {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "SONG_ID")
    private Long songId;
    @Column(name = "SINGER_ID")
    private Long singerId;
    @Column(name = "ENABLE")
    private Long enable; //1- active, 0-inactive

}
