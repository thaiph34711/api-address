package com.example.demoAddress.Controller;

import com.example.demoAddress.DTO.AddressCityDTO;
import com.example.demoAddress.Service.AddressCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address-city")
public class AddressCityController {
    @Autowired
    private AddressCityService addressCityService;

    @GetMapping
    public ResponseEntity<Page<AddressCityDTO>> getAllAddressCities(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        try {
            Pageable pageable = PageRequest.of(page, limit);
            Page<AddressCityDTO> cityPage = addressCityService.findAllAddressCities(pageable);
            return ResponseEntity.ok(cityPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}


