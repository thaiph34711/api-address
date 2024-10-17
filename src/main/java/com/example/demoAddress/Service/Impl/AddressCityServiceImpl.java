package com.example.demoAddress.Service.Impl;

import com.example.demoAddress.DTO.AddressCityDTO;
import com.example.demoAddress.Entity.AddressCityEntity;
import com.example.demoAddress.Repository.AddressCityRepository;
import com.example.demoAddress.Service.AddressCityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AddressCityServiceImpl implements AddressCityService {

    @Autowired
    private AddressCityRepository addressCityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<AddressCityDTO> findAllAddressCities(Pageable pageable) {
        Page<AddressCityEntity> cities = addressCityRepository.findAll(pageable);
        return cities.map(city -> modelMapper.map(city, AddressCityDTO.class));
    }
}
