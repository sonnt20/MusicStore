package com.btec.store.musicstore.repository;

import com.btec.store.musicstore.model.entity.SongAndSingerEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SongAndSingerRepository extends BaseRepository<SongAndSingerEntity>, JpaSpecificationExecutor<SongAndSingerEntity> {
    List<SongAndSingerEntity> findAllBySingerId(Long singerId);

    @Query("select sosi from SongAndSingerEntity sosi where sosi.songId=:songId and sosi.enable=1")
    SongAndSingerEntity findBySongId(@Param("songId") Long songId);
}
