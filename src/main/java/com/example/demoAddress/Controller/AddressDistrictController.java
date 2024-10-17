package com.example.demoAddress.Controller;

import com.example.demoAddress.DTO.AddressDistrictDTO;
import com.example.demoAddress.Service.AddressDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/districts")
public class AddressDistrictController {

    @Autowired
    private AddressDistrictService addressDistrictService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllAddressDistricts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        try {
            Pageable pageable = PageRequest.of(page, limit);
            Page<AddressDistrictDTO> districtPage = addressDistrictService.findAllAddressDistricts(pageable);

            Map<String, Object> response = new HashMap<>();
            response.put("districts", districtPage.getContent());
            response.put("currentPage", districtPage.getNumber());
            response.put("totalItems", districtPage.getTotalElements());
            response.put("totalPages", districtPage.getTotalPages());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = Map.of(
                    "message", e.getMessage(),
                    "status", HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}

