package com.btec.store.musicstore.repository;

import com.btec.store.musicstore.model.entity.AlbumEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlbumRepository extends BaseRepository<AlbumEntity>, JpaSpecificationExecutor<AlbumEntity> {
    @Query("select al from AlbumEntity al" +
            " inner join SongAndAlbumEntity soal on al.id=soal.albumId" +
            " inner join SongEntity so on so.id = soal.songId" +
            " where so.id=:songId and soal.enable=1")
    Optional<AlbumEntity> findAlbumEntityBySongId(@Param("songId") Long songId);
}
