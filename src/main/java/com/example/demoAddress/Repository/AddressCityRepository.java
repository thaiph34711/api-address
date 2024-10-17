package com.example.demoAddress.Repository;

import com.example.demoAddress.Entity.AddressCityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressCityRepository extends JpaRepository<AddressCityEntity, Long> {
    boolean existsByName(String name);
    AddressCityEntity findByName(String name);
}
