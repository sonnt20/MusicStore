package com.btec.store.musicstore.controller.api;

import com.btec.store.musicstore.model.view.ResultUpload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED;
import static javax.sound.sampled.AudioSystem.NOT_SPECIFIED;

@Controller
@RequestMapping("/common")
public class CommonApi {

    private static final String SUFFIX = ".dat";

    private static String pathFile = "/music";

    @RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestBody final MultipartFile file) {
        if (file == null) {
            return new ResponseEntity<>(new ResultUpload(), HttpStatus.NO_CONTENT);
        }
        if (file.getSize() / 1024 / 1024 > 25) {
            return new ResponseEntity<>(new ResultUpload(), HttpStatus.NOT_ACCEPTABLE);
        }
        SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
        String strDate = yyyyMMdd.format(new Date());
//        String folder = parameterDAO.getParameterByKey(Constants.SYS_UPLOAD_FILE).getValue() + "/" + strDate;
        String folder = pathFile + strDate;
        File directory = new File(folder);

        if (!directory.exists()) {
            directory.mkdir();
        }

        ResultUpload item = new ResultUpload();
        try {

            if (!file.isEmpty()) {
                File fileSave = createNewFile(folder, "CTR_");
                String path = "";
                try {
                    FileOutputStream outputStream = null;
                    outputStream = new FileOutputStream(fileSave);
                    outputStream.write(file.getBytes());
                    path = fileSave.getName();
                    item.setName(file.getOriginalFilename());
                    item.setPath(path);
                    outputStream.close();
                } catch (IOException e) {
                    return new ResponseEntity<>(item, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

        } catch (Exception e) {
            return new ResponseEntity<>(item, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @RequestMapping(value = "/uploadSong", method = RequestMethod.POST)
    public ResponseEntity<?> uploadSong(@RequestBody MultipartFile multipartFile) {
        if (multipartFile == null) {
            return new ResponseEntity<>(new ResultUpload(), HttpStatus.NO_CONTENT);
        }
        ResultUpload item = new ResultUpload();
        try {
            File file = multipartToFile(multipartFile);
            AudioFormat format =
                    new AudioFormat(PCM_SIGNED, 44100, 8, 1, 1, 44100, false);
            AudioSystem.write(getStream(format), Type.WAVE, file);
            item.setPath(file.getAbsolutePath());
        } catch (Exception e) {
            e.getMessage();
            return new ResponseEntity<>(item, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


    //    ---------------------HELPER-------------------------
    private File createNewFile(String directory, String prefix) throws Exception {
        File dirs = new File(directory);
        if (!dirs.exists()) {
            dirs.mkdirs();
        }
        File file = File.createTempFile(prefix, SUFFIX, dirs);
        return file;
    }

    private static AudioInputStream getStream(final AudioFormat format) {
        final int dataSize = 5000 * format.getFrameSize();
        final InputStream in = new ByteArrayInputStream(new byte[dataSize]);
        return new AudioInputStream(in, format, NOT_SPECIFIED);
    }

    private static File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File(pathFile + multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }

}
