package com.btec.store.musicstore.service;

import com.btec.store.musicstore.common.Constants;
import com.btec.store.musicstore.model.entity.SongAndAlbumEntity;
import com.btec.store.musicstore.model.entity.SongEntity;
import com.btec.store.musicstore.repository.SongAndAlbumRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongAndAlbumService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private SongAndAlbumRepository songAndAlbumRepository;

    public SongAndAlbumEntity addRls(SongEntity song, Long albumId) {
        try {
            if (song == null || albumId == null) {
                return null;
            }
//            Kiểm tra album cũ của bài hát
            SongAndAlbumEntity songAndAlbumEntity = songAndAlbumRepository.findBySongId(song.getId());
            if (songAndAlbumEntity != null) {
                if (songAndAlbumEntity.getAlbumId() == albumId) {
                    return null;
                } else {
//                    sửa quan hệ song-album cũ thành inactive để thay quan hệ khác
                    songAndAlbumEntity.setEnable(Constants.ENABLE.INACTIVE);
                    songAndAlbumRepository.save(songAndAlbumEntity);
                }
            }
            //UPDATE quan hệ song - album vào db
            songAndAlbumEntity = new SongAndAlbumEntity();
            songAndAlbumEntity.setSongId(song.getId());
            songAndAlbumEntity.setAlbumId(albumId);
            songAndAlbumEntity.setEnable(Constants.ENABLE.ACTIVE);

            songAndAlbumEntity = songAndAlbumRepository.save(songAndAlbumEntity);
            return songAndAlbumEntity;

        } catch (Exception e) {
            logger.error(e.getMessage());

        }
        return null;
    }
}
