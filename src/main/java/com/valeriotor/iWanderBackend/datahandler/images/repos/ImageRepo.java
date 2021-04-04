package com.valeriotor.iWanderBackend.datahandler.images.repos;

import com.valeriotor.iWanderBackend.model.core.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<ImageEntity, String> {
}
