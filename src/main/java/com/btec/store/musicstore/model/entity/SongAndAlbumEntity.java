package com.btec.store.musicstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "albums_song_rls")
@Entity
public class SongAndAlbumEntity extends BaseModel { // bảng này chứa quan hệ giữa song với album

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "SONG_ID")
    private Long songId;
    @Column(name = "ALBUM_ID")
    private Long albumId;
    @Column(name = "ENABLE")
    private Long enable; //1- active, 0-inactive
}
