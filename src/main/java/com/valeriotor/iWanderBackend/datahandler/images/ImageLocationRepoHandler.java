package com.valeriotor.iWanderBackend.datahandler.images;

import com.valeriotor.iWanderBackend.datahandler.images.repos.ImageRepo;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.core.ImageEntity;
import com.valeriotor.iWanderBackend.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Optional;

@Repository("imagehandler")
public class ImageLocationRepoHandler implements ImageLocationDAO {

    private final ImageRepo imageRepo;

    @Autowired
    public ImageLocationRepoHandler(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
    }

    @Override
    public void deleteImageByURL(String url) {
        if(url != null) {
            imageRepo.deleteById(url);
            imageRepo.flush();
        }
    }

    @Override
    public String saveImageAndGetURL(byte[] bytes, AppUser userDetails) {
        String path = "/file/image/" + userDetails.getUsername() + "/" + LocalTime.now().toString();
        ImageEntity image = new ImageEntity();
        image.setPath(path);
        image.setBytes(bytes);
        imageRepo.save(image);

        return path;
    }

    @Override
    public byte[] getImageBytes(String url) {
        Optional<ImageEntity> imageEntity = imageRepo.findById(url);
        return imageEntity.isPresent() ? imageEntity.get().getBytes() : new byte[0];
    }

    @Override
    public byte[] getReducedImageBytes(String url, int maxWidth, int maxHeight) throws IOException {
        byte[] bytes = getImageBytes(url);
        BufferedImage bufferedImage = ImageUtil.resizeImage(bytes, 200, 200);
        bytes = ImageUtil.imageToBytes(bufferedImage);
        return bytes;
    }


}
