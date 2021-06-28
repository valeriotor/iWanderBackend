package com.valeriotor.iWanderBackend.datahandler.images;

import com.valeriotor.iWanderBackend.model.core.AppUser;

import java.io.IOException;

public interface ImageLocationDAO {

    void deleteImageByURL(String url);
    String saveImageAndGetURL(byte[] bytes, AppUser userDetails);
    byte[] getImageBytes(String url);
    byte[] getReducedImageBytes(String url, int maxWidth, int maxHeight) throws IOException;
}
