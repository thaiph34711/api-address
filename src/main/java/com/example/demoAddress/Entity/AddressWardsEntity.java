package com.example.demoAddress.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "address_wards")
public class AddressWardsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "district_id")
    private AddressDistrictEntity district;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private AddressCityEntity city;

    // Getters và Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(AddressDistrictEntity district) {
        this.district = district;
    }

    public AddressCityEntity getCity() {
        return city;
    }

    public void setCity(AddressCityEntity city) {
        this.city = city;
    }
}
