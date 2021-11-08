package com.btec.store.musicstore.repository;

import com.btec.store.musicstore.model.entity.KhUserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface KhUserRepository extends BaseRepository<KhUserEntity>, JpaSpecificationExecutor<KhUserEntity> {
    //    Tìm user theo username và đang hoạt động
    @Query("SELECT u FROM KhUserEntity u WHERE u.username=:username and u.enable=1")
    KhUserEntity findByUsername(String username);
}
