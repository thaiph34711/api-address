package com.example.demoAddress.Entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address_cities")
public class AddressCityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private AddressCountryEntity country;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AddressDistrictEntity> districts = new HashSet<>();

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

    public AddressCountryEntity getCountry() {
        return country;
    }

    public void setCountry(AddressCountryEntity country) {
        this.country = country;
    }

    public Set<AddressDistrictEntity> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<AddressDistrictEntity> districts) {
        this.districts = districts;
    }
}
