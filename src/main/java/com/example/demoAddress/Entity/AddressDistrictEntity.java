package com.example.demoAddress.Entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "address_districts")
public class AddressDistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private AddressCityEntity city;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AddressWardsEntity> wards = new HashSet<>();

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

    public AddressCityEntity getCity() {
        return city;
    }

    public void setCity(AddressCityEntity city) {
        this.city = city;
    }

    public Set<AddressWardsEntity> getWards() {
        return wards;
    }

    public void setWards(Set<AddressWardsEntity> wards) {
        this.wards = wards;
    }
}
