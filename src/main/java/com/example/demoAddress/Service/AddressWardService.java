package com.example.demoAddress.Service;

import com.example.demoAddress.DTO.AddressWardsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface AddressWardService {
    Page<AddressWardsDTO> findAllAddressWards(Pageable pageable);
}
