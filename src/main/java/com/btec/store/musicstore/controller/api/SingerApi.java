package com.btec.store.musicstore.controller.api;

import com.btec.store.musicstore.model.dto.request.GetOneByIdRequest;
import com.btec.store.musicstore.model.entity.SingerEntity;
import com.btec.store.musicstore.repository.SingerRepository;
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
@RequestMapping("/api/singer")
public class SingerApi {
    private Logger logger = LogManager.getLogger(this.getClass());
    @Autowired
    private SingerRepository singerRepository;

    @PostMapping("/list")
    public ResponseEntity<?> getSinger() {
        List<SingerEntity> sings = null;
        try {
            sings = singerRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(sings, HttpStatus.OK);
    }

    @PostMapping("/getOne")
    public ResponseEntity<?> getOneById(@RequestBody GetOneByIdRequest request) {
//        check request
        if (request == null || request.getId() == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        SingerEntity singer = null;
        try {
            //        tìm ca sĩ trong db
            Optional<SingerEntity> songOptional = singerRepository.findById(request.getId().longValue());

            if (!songOptional.isPresent()) {
                return new ResponseEntity<>("Không tìm thấy ca sĩ", HttpStatus.NO_CONTENT);
            }

            singer = songOptional.get();

            return new ResponseEntity<>(singer, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getSingerBySong")
    public ResponseEntity<?> getSingerBySong(@RequestBody GetOneByIdRequest request) {
//        check request
        if (request == null || request.getId() == null) {
            return new ResponseEntity<>("Param invalid", HttpStatus.BAD_REQUEST);
        }
        SingerEntity singer = null;
        try {
            //       tìm ca sĩ của bài hát
            Optional<SingerEntity> singerOptional = singerRepository.findSingerEntityBySongId(request.getId().longValue());

            if (!singerOptional.isPresent()) {
                return new ResponseEntity<>("Không tìm thấy ca sĩ", HttpStatus.NO_CONTENT);
            }

            singer = singerOptional.get();

            return new ResponseEntity<>(singer, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
