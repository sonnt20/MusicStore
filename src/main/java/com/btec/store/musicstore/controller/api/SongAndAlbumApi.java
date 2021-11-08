package com.btec.store.musicstore.controller.api;

import com.btec.store.musicstore.model.dto.request.GetSongsInAlbumRequest;
import com.btec.store.musicstore.model.dto.request.GetSongsOfSingerRequest;
import com.btec.store.musicstore.model.entity.SongAndAlbumEntity;
import com.btec.store.musicstore.model.entity.SongAndSingerEntity;
import com.btec.store.musicstore.model.entity.SongEntity;
import com.btec.store.musicstore.repository.SongAndAlbumRepository;
import com.btec.store.musicstore.repository.SongAndSingerRepository;
import com.btec.store.musicstore.repository.SongRepository;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/rls")
public class SongAndAlbumApi {

    private Logger logger = LogManager.getLogger(this.getClass());

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private SongAndAlbumRepository songAndAlbumRepository;

    @Autowired
    private SongAndSingerRepository songAndSingerRepository;

    @Autowired
    private SongRepository songRepository;

    @PostMapping("/getRlsSongsInAlbum")
    public ResponseEntity<?> getRlsSongsInAlbum(@RequestBody GetSongsInAlbumRequest request) {
        List<SongEntity> songs = new ArrayList<>();
        if (request == null || request.getId() == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        try {
            List<SongAndAlbumEntity> list = songAndAlbumRepository.findAllByAlbumId(request.getId());
            for (SongAndAlbumEntity songAndAlbumEntity : list) {
                SongEntity song = songRepository.getById(songAndAlbumEntity.getSongId());
                songs.add(song);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping("/getSongsInAlbum")
    public ResponseEntity<?> getSongsInAlbum(@RequestBody GetSongsInAlbumRequest request) {
        List<SongEntity> songs = new ArrayList<>();
        if (request == null || request.getId() == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        try {
            songs = songAndAlbumRepository.getSongEntityByAlbumId(request.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @PostMapping("/SongsOfSinger")
    public ResponseEntity<?> getSongsOfSinger(@RequestBody GetSongsOfSingerRequest request) {
        List<SongAndSingerEntity> list = null;
        if (request == null || request.getSingerId() == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        try {
            list = songAndSingerRepository.findAllBySingerId(request.getSingerId());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
