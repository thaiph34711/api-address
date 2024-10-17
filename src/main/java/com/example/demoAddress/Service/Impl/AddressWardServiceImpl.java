package com.example.demoAddress.Service.Impl;

import com.example.demoAddress.DTO.AddressWardsDTO;
import com.example.demoAddress.Entity.AddressWardsEntity;
import com.example.demoAddress.Repository.AddressCityRepository;
import com.example.demoAddress.Repository.AddressWardsRepository;
import com.example.demoAddress.Service.AddressWardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressWardServiceImpl implements AddressWardService {

    @Autowired
    private AddressWardsRepository addressWardsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<AddressWardsDTO> findAllAddressWards(Pageable pageable) {
        Page<AddressWardsEntity> wards = addressWardsRepository.findAll(pageable);
        return wards.map(ward -> modelMapper.map(ward, AddressWardsDTO.class));
    }
}


