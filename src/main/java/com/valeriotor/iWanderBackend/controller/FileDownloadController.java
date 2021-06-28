package com.valeriotor.iWanderBackend.controller;

import com.valeriotor.iWanderBackend.datahandler.images.ImageLocationDAO;
import com.valeriotor.iWanderBackend.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

@RestController
public class FileDownloadController {

    private final ImageLocationDAO imageLocationDAO;
    private final JedisHandler jedisHandler;

    @Autowired
    public FileDownloadController(ImageLocationDAO imageLocationDAO, JedisHandler jedisHandler) {
        this.imageLocationDAO = imageLocationDAO;
        this.jedisHandler = jedisHandler;
    }

    @GetMapping(value = "/file/image/{username}/{timestamp}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public Resource imageDownload(@PathVariable String username, @PathVariable String timestamp, boolean reduced) throws IOException {
        String path = "/file/image/" + username + "/" + timestamp;
        byte[] bytes = null;
        if(reduced) {
            Optional<byte[]> optionalBytes = jedisHandler.checkCacheBytes(path);
            if(optionalBytes.isPresent())
                bytes = optionalBytes.get();
            else {
                bytes = imageLocationDAO.getReducedImageBytes(path, 200, 200);
                jedisHandler.addToCacheBytes(path, bytes, 86400);
            }
        } else {
            bytes = imageLocationDAO.getImageBytes(path);
        }
        return new ByteArrayResource(bytes);
    }

}
