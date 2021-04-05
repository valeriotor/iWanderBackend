package com.valeriotor.iWanderBackend.datahandler.images;

import com.valeriotor.iWanderBackend.datahandler.images.repos.ImageRepo;
import com.valeriotor.iWanderBackend.model.core.AppUser;
import com.valeriotor.iWanderBackend.model.core.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public String saveImageAndGetURL(byte[] bytes, AppUser userDetails) {
        String path = "/file/image/" + userDetails.getUsername() + "/" + LocalTime.now().toString();
        String oldUrl = userDetails.getImageURL();
        if(oldUrl != null) {
            imageRepo.deleteById(oldUrl);
            imageRepo.flush();
        }
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


}
