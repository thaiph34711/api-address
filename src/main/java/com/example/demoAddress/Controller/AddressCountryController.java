package com.example.demoAddress.Controller;

import ch.qos.logback.core.model.Model;
import com.example.demoAddress.DTO.AddressCountryDTO;

import com.example.demoAddress.Service.AddressCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/address-country")
public class AddressCountryController {
    @Autowired
    private AddressCountryService addressCountryService;

    @GetMapping
    public ResponseEntity<Page<AddressCountryDTO>> getAllAddressCountries(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        try {
            Pageable pageable = PageRequest.of(page, limit);
            Page<AddressCountryDTO> countryPage = addressCountryService.findAllAddressCountries(pageable);
            return ResponseEntity.ok(countryPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

