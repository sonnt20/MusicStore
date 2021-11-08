package com.btec.store.musicstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "GROUP_ROLE")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRoleEntity extends BaseModel {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "ENABLE")
    private Integer enable;

    @Column(name = "ROLES")
    private String roles;

    @Column(name = "GEN_DATE")
    private Date genDate;
//    id integer NOT NULL AUTO_INCREMENT primary key,
//    code varchar(50),
//    enable varchar(1),
//    roles varchar(1000),
//    gen_date Date
}
