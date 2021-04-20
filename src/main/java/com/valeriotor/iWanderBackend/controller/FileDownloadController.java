package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.images.ImageLocationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDownloadController {

    private final ImageLocationDAO imageLocationDAO;
    @Autowired
    public FileDownloadController(ImageLocationDAO imageLocationDAO) {
        this.imageLocationDAO = imageLocationDAO;
    }

    @GetMapping(value = "/file/image/{username}/{timestamp}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public Resource imageDownload(@PathVariable String username, @PathVariable String timestamp) {
        String path = "/file/image/" + username + "/" + timestamp;
        byte[] bytes = imageLocationDAO.getImageBytes(path);
        return new ByteArrayResource(bytes);
    }

}
