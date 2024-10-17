package com.example.demoAddress.Service.Impl;

import com.example.demoAddress.DTO.AddressCountryDTO;
import com.example.demoAddress.Entity.AddressCountryEntity;
import com.example.demoAddress.Repository.AddressCountryRepository;
import com.example.demoAddress.Service.AddressCountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class AddressCountryServiceImpl implements AddressCountryService {
    @Autowired
    private AddressCountryRepository addressCountryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<AddressCountryDTO> findAllAddressCountries(Pageable pageable) {
        Page<AddressCountryEntity> countries = addressCountryRepository.findAll(pageable);
        return countries.map(country -> modelMapper.map(country, AddressCountryDTO.class));
    }

}
