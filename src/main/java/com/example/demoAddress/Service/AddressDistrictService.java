package com.example.demoAddress.Service;

import com.example.demoAddress.DTO.AddressDistrictDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AddressDistrictService {
    Page<AddressDistrictDTO> findAllAddressDistricts(Pageable pageable);
}
