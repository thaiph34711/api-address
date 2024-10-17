package com.example.demoAddress.Service.Impl;

import com.example.demoAddress.DTO.AddressDistrictDTO;
import com.example.demoAddress.Entity.AddressDistrictEntity;
import com.example.demoAddress.Repository.AddressDistrictRepository;
import com.example.demoAddress.Service.AddressDistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressDistrictServiceImpl implements AddressDistrictService {

    @Autowired
    private AddressDistrictRepository addressDistrictRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<AddressDistrictDTO> findAllAddressDistricts(Pageable pageable) {
        Page<AddressDistrictEntity> districts = addressDistrictRepository.findAll(pageable);
        return districts.map(district -> modelMapper.map(district, AddressDistrictDTO.class));
    }

}
