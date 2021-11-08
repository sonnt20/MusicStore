package com.btec.store.musicstore.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
//@Entity - là thể hiện của bảng trong database
@Entity
//@Table - mapping với bảng trong database
@Table(name = "KH_USER")

//@Data - generate setter, getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KhUserEntity extends BaseModel {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLE")
    private Integer enable; //1- hoạt động, 0- không hoạt động

    @Column(name = "MAIL")
    private String email;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "GROUP_ROLE_ID")
    private Integer grRoleId; //Id của nhóm các quyền trong database

    @Column(name = "GEN_DATE")
    private Date genDate;//Thời gian tạo

    @Column(name = "JWT")
    private String jwt;


}
