package com.btec.store.musicstore.controller.api;

import com.btec.store.musicstore.model.dto.request.GetOneByIdRequest;
import com.btec.store.musicstore.model.dto.request.album.AddAlbumRequest;
import com.btec.store.musicstore.model.entity.AlbumEntity;
import com.btec.store.musicstore.repository.AlbumRepository;
import com.btec.store.musicstore.service.AlbumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/album")
public class AlbumApi {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumService albumService;

    @PostMapping("/list")
    public ResponseEntity<?> getAlbum() {
        List<AlbumEntity> albums = null;
        try {
            albums = albumRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    @PostMapping("/getOne")
    public ResponseEntity<?> getOneById(@RequestBody GetOneByIdRequest request) {
//        check request
        if (request == null || request.getId() == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        AlbumEntity album = null;
        try {
            //        tìm album trong db
            Optional<AlbumEntity> songOptional = albumRepository.findById(request.getId().longValue());

            if (!songOptional.isPresent()) {
                return new ResponseEntity<>("Không tìm thấy album", HttpStatus.NO_CONTENT);
            }

            album = songOptional.get();

            return new ResponseEntity<>(album, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addAlbum")
    public ResponseEntity<?> addAlbum(@RequestBody AddAlbumRequest request) {
//        check request
        if (request == null || request.getName() == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        try {
            AlbumEntity album = albumService.addAlbum(request);

            return new ResponseEntity<>(album, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getAlbumsBySong")
    public ResponseEntity<?> getAlbumsBySong(@RequestBody GetOneByIdRequest request) {
//        check request
        if (request == null || request.getId() == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        AlbumEntity album = null;
        try {
            //       tìm ca sĩ của bài hát
            Optional<AlbumEntity> albumOptional = albumRepository.findAlbumEntityBySongId(request.getId().longValue());

            if (!albumOptional.isPresent()) {
                return new ResponseEntity<>("Không tìm thấy album", HttpStatus.NO_CONTENT);
            }

            album = albumOptional.get();

            return new ResponseEntity<>(album, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
