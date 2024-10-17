package com.example.demoAddress.Repository;

import com.example.demoAddress.Entity.AddressCountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressCountryRepository extends JpaRepository<AddressCountryEntity, Long> {
    boolean existsByName(String name);
}
