package com.btec.store.musicstore.service;

import com.btec.store.musicstore.common.Constants;
import com.btec.store.musicstore.model.entity.SongAndSingerEntity;
import com.btec.store.musicstore.model.entity.SongEntity;
import com.btec.store.musicstore.repository.SongAndSingerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongAndSingerService {

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private SongAndSingerRepository songAndSingerRepository;

    public SongAndSingerEntity addRls(SongEntity song, Long singerId) {
        try {
            if (song == null || singerId == null) {
                return null;
            }
//            kiểm tra quan hệ song-singer đã có trong db chưa
            SongAndSingerEntity songAndSingerEntity = songAndSingerRepository.findBySongId(song.getId());

            if (songAndSingerEntity != null) {
//                kiểm tra quan hệ song-sing cũ có giống hiện tại không, giống thì thôi k lưu
                if (songAndSingerEntity.getSingerId() == singerId) {
                    return null;
                } else {
//                    không giống thì inactive quan hệ cũ để tạo quan hệ mới
                    songAndSingerEntity.setEnable(Constants.ENABLE.INACTIVE);
                    songAndSingerRepository.save(songAndSingerEntity);
                }
            }
            //                lưu quan hệ song - singer vào db
            songAndSingerEntity = new SongAndSingerEntity();
            songAndSingerEntity.setSongId(song.getId());
            songAndSingerEntity.setSingerId(singerId);
            songAndSingerEntity.setEnable(Constants.ENABLE.ACTIVE);

            songAndSingerEntity = songAndSingerRepository.save(songAndSingerEntity);
            return songAndSingerEntity;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
