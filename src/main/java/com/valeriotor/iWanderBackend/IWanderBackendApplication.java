package com.valeriotor.iWanderBackend;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories("com.valeriotor.iWanderBackend.datahandler")
public class IWanderBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(IWanderBackendApplication.class, args);
	}

	@Bean(name = "mapper")
	public Mapper mapper() {
		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(Collections.singletonList("dozer_configuration.xml"));
		return mapper;
	}

}
