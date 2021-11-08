package com.btec.store.musicstore.controller.api;


import com.btec.store.musicstore.model.dto.request.song.AddSongRequest;
import com.btec.store.musicstore.model.dto.request.song.DeleteSongRequest;
import com.btec.store.musicstore.model.dto.request.song.EditSongRequest;
import com.btec.store.musicstore.model.dto.request.GetOneByIdRequest;
import com.btec.store.musicstore.model.entity.SongEntity;
import com.btec.store.musicstore.repository.SongAndAlbumRepository;
import com.btec.store.musicstore.repository.SongAndSingerRepository;
import com.btec.store.musicstore.repository.SongRepository;
import com.btec.store.musicstore.service.SongService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/song")
public class SongApi {
    private Logger logger = LogManager.getLogger(this.getClass());

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongAndAlbumRepository songAndAlbumRepository;

    @Autowired
    private SongAndSingerRepository songAndSingerRepository;

    @Autowired
    private SongService songService;

    @PostMapping("/list")
    public ResponseEntity<?> getSongs() {
        List<SongEntity> songs = null;
        try {
            songs = songRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping("/addSong")
    public ResponseEntity<?> addSong(@RequestBody AddSongRequest request) {
//        check request
        if (request == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        if ((request.getName() == null || !request.getName().trim().equals("")) &&
                (request.getSingerId() == null)) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        SongEntity song = new SongEntity();
        try {
            song = songService.addNewSong(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
//        trả về song mới lưu nếu không có lỗi gì
//        có lỗi thì trả về null
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @PostMapping("/editSong")
    public ResponseEntity<?> editSong(@RequestBody EditSongRequest request) {
//        check request
        if (request == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        if ((request.getName() == null || !request.getName().trim().equals("")) &&
                (request.getSingerId() == null)) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        SongEntity song = null;
        try {
            song = songService.editSong(request);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteSong(@RequestBody DeleteSongRequest request) {
//        check request
        if (request == null || request.getId() == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        SongEntity song = null;
        try {
            //        tìm bài hát trong db
            Optional<SongEntity> songOptional = songRepository.findById(request.getId().longValue());

            if (!songOptional.isPresent()) {
                return new ResponseEntity<>("Không tìm thấy bài hát", HttpStatus.NO_CONTENT);
            }

            song = songOptional.get();

            int deleteFlag = songRepository.deleteSongEntitiesById(request.getId().longValue());

            if (deleteFlag <= 0) {
                return new ResponseEntity<>("Không thể xóa", HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @PostMapping("/getOne")
    public ResponseEntity<?> getOneById(@RequestBody GetOneByIdRequest request) {
//        check request
        if (request == null || request.getId() == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        SongEntity song = null;
        try {
            //        tìm bài hát trong db
            Optional<SongEntity> songOptional = songRepository.findById(request.getId().longValue());

            if (!songOptional.isPresent()) {
                return new ResponseEntity<>("Không tìm thấy bài hát", HttpStatus.NO_CONTENT);
            }

            song = songOptional.get();

            return new ResponseEntity<>(song, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
