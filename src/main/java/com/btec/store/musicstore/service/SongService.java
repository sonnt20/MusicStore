package com.btec.store.musicstore.service;

import com.btec.store.musicstore.common.Constants;
import com.btec.store.musicstore.model.dto.request.song.AddSongRequest;
import com.btec.store.musicstore.model.dto.request.song.EditSongRequest;
import com.btec.store.musicstore.model.entity.SongEntity;
import com.btec.store.musicstore.repository.SongRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class SongService {
    private Logger logger = LogManager.getLogger(this.getClass());

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongAndAlbumService songAndAlbumService;

    @Autowired
    private SongAndSingerService songAndSingerService;

    public SongEntity addNewSong(AddSongRequest request) {
        SongEntity song = new SongEntity();
        try {
            //set giá trị cho đối tượng vừa song vừa tạo
            if (request.getDescription() != null && !request.getDescription().trim().equals("")) {
                song.setDescription(request.getDescription());
            }
            if (request.getName() != null && !request.getName().trim().equals("")) {
                song.setName(request.getName());
            }
            if (request.getReleaseDate() != null) {
                song.setReleaseDate(new Date(simpleDateFormat.parse(request.getReleaseDate()).getTime()));
            }
            if (request.getLinkFull() != null && !request.getLinkFull().trim().equals("")) {
                song.setLinkFull(request.getLinkFull());
            }
            if (request.getImage() != null && !request.getImage().trim().equals("")) {
                song.setImage(request.getImage());
            }
            song.setGenDate(new Date(new java.util.Date().getTime()));
            song.setStatus(Constants.SONG_STATUS.ACTIVE);
            //Lưu đối tượng song vào db
            song = songRepository.save(song);

            songAndAlbumService.addRls(song, request.getAlbumId());

            songAndSingerService.addRls(song, request.getSingerId());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return song;
    }

    public SongEntity editSong(EditSongRequest request) {
        SongEntity song = null;
        try {
            //        tìm bài hát trong db
            Optional<SongEntity> songOptional = songRepository.findById(request.getId().longValue());

            if (!songOptional.isPresent()) {
                return null;
            }

            song = songOptional.get();

            if (request.getDescription() != null && !request.getDescription().trim().equals("")) {
                song.setDescription(request.getDescription());
            }
            if (request.getName() != null && !request.getName().trim().equals("")) {
                song.setName(request.getName());
            }
            if (request.getReleaseDate() != null) {
                song.setReleaseDate(new Date(simpleDateFormat.parse(request.getReleaseDate()).getTime()));
            }
            if (request.getLinkFull() != null && !request.getLinkFull().trim().equals("")) {
                song.setLinkFull(request.getLinkFull());
            }
            if (request.getImage() != null && !request.getImage().trim().equals("")) {
                song.setImage(request.getImage());
            }

            song.setLastUpdate(new Date(new java.util.Date().getTime()));
            //Lưu đối tượng song vào db
            song = songRepository.save(song);

            songAndAlbumService.addRls(song, request.getAlbumId());

            songAndSingerService.addRls(song, request.getSingerId());

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return song;
    }
}
