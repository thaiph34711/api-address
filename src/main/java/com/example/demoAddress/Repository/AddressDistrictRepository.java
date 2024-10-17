package com.example.demoAddress.Repository;

import com.example.demoAddress.Entity.AddressDistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDistrictRepository extends JpaRepository<AddressDistrictEntity, Long> {
    boolean existsByName(String name);
}
