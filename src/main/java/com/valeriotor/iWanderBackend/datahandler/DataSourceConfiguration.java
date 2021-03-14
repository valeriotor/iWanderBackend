package com.valeriotor.iWanderBackend.datahandler;

import com.valeriotor.iWanderBackend.datasource.ProfileDataSource;
import com.valeriotor.iWanderBackend.datasource.emulated.EmulatedProfileDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

    @Bean
    public ProfileDataSource profileDataSource() {
        return new EmulatedProfileDataSource();
    }

}
