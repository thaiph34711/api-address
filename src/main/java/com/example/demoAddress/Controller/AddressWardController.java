package com.example.demoAddress.Controller;
import com.example.demoAddress.DTO.AddressWardsDTO;
import com.example.demoAddress.Service.AddressWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/address-ward")
public class AddressWardController {
    @Autowired
    private AddressWardService addressWardService;

    @GetMapping
    public ResponseEntity<Page<AddressWardsDTO>> getAllAddressWards(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "10") int limit) {
        try {
            Pageable pageable = PageRequest.of(page, limit);
            Page<AddressWardsDTO> wardPage = addressWardService.findAllAddressWards(pageable);
            return ResponseEntity.ok(wardPage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

