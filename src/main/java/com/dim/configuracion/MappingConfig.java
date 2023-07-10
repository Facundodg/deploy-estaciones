package com.dim.configuracion;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class MappingConfig {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(context ->
                !(context.getSource() instanceof PersistentCollection) || ((PersistentCollection<?>) context.getSource()).wasInitialized());

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD)
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(PRIVATE);

        return modelMapper;
    }
}
