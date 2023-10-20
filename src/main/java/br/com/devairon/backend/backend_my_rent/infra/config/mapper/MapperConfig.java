package br.com.devairon.backend.backend_my_rent.infra.config.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
   @Bean
    public ModelMapper mapper(){ return new ModelMapper();}
}
