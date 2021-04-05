package com.valeriotor.iWanderBackend.datahandler.images;

import com.valeriotor.iWanderBackend.model.core.AppUser;

public interface ImageLocationDAO {

    String saveImageAndGetURL(byte[] bytes, AppUser userDetails);
    byte[] getImageBytes(String url);
}
