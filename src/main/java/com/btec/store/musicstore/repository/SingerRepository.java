package com.btec.store.musicstore.repository;

import com.btec.store.musicstore.model.entity.SingerEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SingerRepository extends BaseRepository<SingerEntity>, JpaSpecificationExecutor<SingerEntity> {
    @Query("select s from SingerEntity s where s.status=1")
    @Override
    List<SingerEntity> findAll();

    @Query("select si from SingerEntity si" +
            " inner join SongAndSingerEntity sosi on si.id=sosi.singerId" +
            " inner join SongEntity so on so.id = sosi.songId" +
            " where so.id=:songId and sosi.enable=1")
    Optional<SingerEntity> findSingerEntityBySongId(@Param("songId") Long songId);
}
