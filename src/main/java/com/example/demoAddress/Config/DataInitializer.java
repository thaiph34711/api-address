package com.example.demoAddress.Config;

import com.example.demoAddress.Entity.AddressCityEntity;
import com.example.demoAddress.Entity.AddressCountryEntity;
import com.example.demoAddress.Entity.AddressDistrictEntity;
import com.example.demoAddress.Entity.AddressWardsEntity;
import com.example.demoAddress.Repository.AddressCityRepository;
import com.example.demoAddress.Repository.AddressCountryRepository;
import com.example.demoAddress.Repository.AddressDistrictRepository;
import com.example.demoAddress.Repository.AddressWardsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


@Configuration
public class DataInitializer {

    @Autowired
    private AddressCityRepository addressCityRepository;

    @Autowired
    private AddressDistrictRepository addressDistrictRepository; // Sử dụng AddressDistrictRepository cho quận/huyện

    @Autowired
    private AddressWardsRepository addressWardsRepository;


    @Autowired
    private final RestTemplate restTemplate = new RestTemplate();

    // API để lấy dữ liệu cho thành phố, quận huyện và xã phường tại Việt Nam
    private final String API_URL_PROVINCES = "https://provinces.open-api.vn/api/p";
    private final String API_URL_DISTRICTS = "https://provinces.open-api.vn/api/d/";
    private final String API_URL_WARDS = "https://provinces.open-api.vn/api/w/";
    @Autowired
    private AddressCountryRepository addressCountryRepository;

    @PostConstruct
    public void init() {
        fetchAndSaveVietnamData(); // Gọi hàm để lấy dữ liệu Việt Nam
    }

    private void fetchAndSaveVietnamData() {

        // Lấy dữ liệu cho tỉnh
        List<Map<String, Object>> provinces = restTemplate.getForObject(API_URL_PROVINCES, List.class);
        if (provinces != null) {
            if (addressCityRepository.count() == 0) {
                AddressCountryEntity addressCountryEntity = new AddressCountryEntity();
                addressCountryEntity.setName("Việt Nam");
                AddressCountryEntity country = addressCountryRepository.save(addressCountryEntity);
                for (Map<String, Object> province : provinces) {
                    AddressCityEntity newCity = new AddressCityEntity();
                    String provinceName = (String) province.get("name"); // Lấy tên tỉnh/thành phố
                    if (!addressCityRepository.existsByName(provinceName)) {
                        newCity.setName(provinceName);
                        newCity.setCountry(country);
                        addressCityRepository.save(newCity);
                    }

                    Integer provinceId = (Integer) province.get("code"); // Lấy ID của tỉnh
                    if (provinceId != null) {
                        // Lấy danh sách quận/huyện theo tỉnh
                        List<Map<String, Object>> districts = restTemplate.getForObject(API_URL_DISTRICTS, List.class);
                        if (districts != null) {
                            // Lọc quận/huyện theo provinceId
                            districts.stream()
                                    .filter(district -> !addressDistrictRepository.existsByName((String) district.get("name"))) // Kiểm tra tên quận chưa tồn tại
                                    .filter(district -> provinceId.equals(district.get("province_code"))) // Sử dụng equals để so sánh
                                    .forEach(district -> {
                                        String districtName = (String) district.get("name");
                                        AddressDistrictEntity newDistrict = new AddressDistrictEntity();
                                        newDistrict.setName(districtName);
                                        newDistrict.setCity(newCity); // Gán thành phố cho quận/huyện
                                        addressDistrictRepository.save(newDistrict);

                                        Integer districtId = (Integer) district.get("code"); // Lấy ID của quận

//                                     Lấy danh sách xã/phường theo quận
                                        List<Map<String, Object>> wards = restTemplate.getForObject(API_URL_WARDS, List.class, districtId);
                                        if (wards != null) {
                                            // Lọc xã/phường theo districtId
                                            wards.stream()
                                                    .filter(ward -> !addressWardsRepository.existsByName((String) ward.get("name"))) // Kiểm tra xem phường chưa tồn tại
                                                    .filter(ward -> districtId.equals(ward.get("district_code"))) // So khớp ID quận/huyện
                                                    .forEach(ward -> {
                                                        String wardName = (String) ward.get("name");
                                                        AddressWardsEntity newWard = new AddressWardsEntity();
                                                        newWard.setName(wardName);
                                                        newWard.setDistrict(newDistrict); // Gán quận/huyện cho xã/phường
                                                        addressWardsRepository.save(newWard);
                                                    });
                                        }
                                    });
                        } else {
                            System.out.println("Không lấy được dữ liệu quận/huyện cho tỉnh: " + provinceName);
                        }
                    } else {
                        System.out.println("Province ID is null for province: " + provinceName);
                    }
                }
            }

        } else {
            System.out.println("Không lấy được dữ liệu Việt Nam từ API");
        }
    }

}







