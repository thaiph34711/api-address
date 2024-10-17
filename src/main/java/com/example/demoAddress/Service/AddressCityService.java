package com.example.demoAddress.Service;

import com.example.demoAddress.DTO.AddressCityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressCityService {
    Page<AddressCityDTO> findAllAddressCities(Pageable pageable);
}
