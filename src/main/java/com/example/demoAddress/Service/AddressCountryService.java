package com.example.demoAddress.Service;

import com.example.demoAddress.DTO.AddressCountryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AddressCountryService {
    Page<AddressCountryDTO> findAllAddressCountries(Pageable pageable);
}
