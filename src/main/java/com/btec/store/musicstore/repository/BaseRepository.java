package com.btec.store.musicstore.repository;

import com.btec.store.musicstore.model.entity.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E extends BaseModel> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> {
//repository là interface - là 1 đặc tả cung cấp các hàm xây dựng câu query  hoặc nó cung cấp sẵn các câu query
}
