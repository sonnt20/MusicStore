package com.btec.store.musicstore.repository;

import com.btec.store.musicstore.model.entity.SongAndAlbumEntity;
import com.btec.store.musicstore.model.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongAndAlbumRepository extends BaseRepository<SongAndAlbumEntity>, JpaSpecificationExecutor<SongAndAlbumEntity> {

    List<SongAndAlbumEntity> findAllByAlbumId(Long albumId);

    @Query("select soal from SongAndAlbumEntity soal where soal.songId=:songId and soal.enable=1")
    SongAndAlbumEntity findBySongId(@Param("songId") Long songId);

    @Query("select so from SongEntity so inner join SongAndAlbumEntity soal on soal.songId=so.id where soal.albumId=:albumId")
    List<SongEntity> getSongEntityByAlbumId(@Param("albumId") Long albumId);

}
