package com.example.demoAddress.Repository;

import com.example.demoAddress.Entity.AddressDistrictEntity;
import com.example.demoAddress.Entity.AddressWardsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressWardsRepository extends JpaRepository<AddressWardsEntity, Long> {
    boolean existsByName(String name);

    AddressWardsEntity findByName(String name);

    // Thêm phương thức tìm theo quận/huyện
    List<AddressWardsEntity> findByDistrict(AddressDistrictEntity district);

    List<AddressWardsEntity> findByDistrictId(Long districtId);
}


