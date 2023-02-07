package com.work.station.service;

import com.work.station.dto.CarDTO;
import com.work.station.model.Car;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    CarDTO save(Car car);

    CarDTO getCarByLicensePlate(String plate);

    CarDTO updateCarByLicensePlate(String plate, Car Car);

    List<CarDTO> getCarByCustomerName(String name);

    List<CarDTO> getCarByCustomerPhoneNumber(Byte num);

    List<CarDTO> getAllCars(Pageable pageable);

    void deleteCar(Long id);
}
