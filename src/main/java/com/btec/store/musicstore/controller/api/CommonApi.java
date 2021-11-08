package com.btec.store.musicstore.controller.api;

import com.btec.store.musicstore.model.view.ResultUpload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/common")
public class CommonApi {

    private static final String SUFFIX = ".dat";

    private String pathFile = "/music";

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
                    return new ResponseEntity<>(item, HttpStatus.OK);
                } catch (IOException e) {
                    return new ResponseEntity<>(item, HttpStatus.OK);
                }
            }

        } catch (Exception e) {
            return new ResponseEntity<>(item, HttpStatus.OK);
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

}
