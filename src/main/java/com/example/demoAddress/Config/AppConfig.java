package com.example.demoAddress.Config;

import com.example.demoAddress.DTO.AddressCityDTO;
import com.example.demoAddress.DTO.AddressDistrictDTO;
import com.example.demoAddress.DTO.AddressWardsDTO;
import com.example.demoAddress.Entity.AddressCityEntity;
import com.example.demoAddress.Entity.AddressDistrictEntity;
import com.example.demoAddress.Entity.AddressWardsEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Mapping cho AddressCityDTO
        modelMapper.typeMap(AddressCityEntity.class, AddressCityDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getCountry().getName(), AddressCityDTO::setCountryName);
        });

        // Mapping cho AddressDistrictDTO
        modelMapper.typeMap(AddressDistrictEntity.class, AddressDistrictDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getCity().getName(), AddressDistrictDTO::setCityName);
        });

        // Mapping cho AddressWardsDTO
        modelMapper.typeMap(AddressWardsEntity.class, AddressWardsDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getDistrict().getName(), AddressWardsDTO::setDistrictName);
        });

        return modelMapper;
    }
}
