package com.btec.store.musicstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "SINGER")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingerEntity extends BaseModel {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SINGER_NAME")
    private String singerName;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "INFO")
    private String info;


//    id integer NOT NULL AUTO_INCREMENT primary key,
//    singer_name varchar(100),
//    full_name varchar(100),
//    age varchar(10),
//    album_ids nvarchar(5000),
//    gender varchar(20),
//    info varchar(500)
}
