package com.btec.store.musicstore.repository;

import com.btec.store.musicstore.model.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface SongRepository extends BaseRepository<SongEntity>, JpaSpecificationExecutor<SongEntity> {
    @Query("select s from SongEntity s where s.status=1")
    @Override
    List<SongEntity> findAll();

    @Transactional
    @Query("update SongEntity s set s.status =0 where s.id=:id")
    @Modifying
    Integer deleteSongEntitiesById(@Param("id") Long id);
}
