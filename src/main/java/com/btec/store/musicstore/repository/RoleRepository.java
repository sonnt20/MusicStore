package com.btec.store.musicstore.repository;

import com.btec.store.musicstore.model.entity.GroupRoleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends BaseRepository<GroupRoleEntity>, JpaSpecificationExecutor<GroupRoleEntity> {
    //     @Query là để custom câu query theo mong muốn
//    lấy group role theo user
    @Query("select gr from GroupRoleEntity gr left join KhUserEntity u on u.grRoleId=gr.id where u.username=:username")
    GroupRoleEntity getGroupRoleEntitiesByUser(String username);
}
