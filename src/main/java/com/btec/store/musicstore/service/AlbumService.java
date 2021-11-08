package com.btec.store.musicstore.service;

import com.btec.store.musicstore.model.dto.request.album.AddAlbumRequest;
import com.btec.store.musicstore.model.entity.AlbumEntity;
import com.btec.store.musicstore.repository.AlbumRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AlbumService {
    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private AlbumRepository albumRepository;

    public AlbumEntity addAlbum(AddAlbumRequest request) {
        try {
            AlbumEntity albumEntity = new AlbumEntity();

            albumEntity.setAlbumName(request.getName());
            albumEntity.setDescription(request.getDescription());
            albumEntity.setImage(request.getImage());

            albumEntity.setGenDate(new Date(new java.util.Date().getTime()));

            albumEntity = albumRepository.save(albumEntity);

            return albumEntity;
            
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
