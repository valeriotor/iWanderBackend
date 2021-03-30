package com.valeriotor.iWanderBackend.datahandler.repos;

import com.valeriotor.iWanderBackend.model.core.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, String> {
}
