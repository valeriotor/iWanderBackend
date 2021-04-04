package com.valeriotor.iWanderBackend.datahandler.images;

import com.valeriotor.iWanderBackend.model.core.ApplicationUserDetails;

public interface ImageLocationDAO {

    String saveImageAndGetURL(byte[] bytes, ApplicationUserDetails userDetails);
    byte[] getImageBytes(String url);
}
