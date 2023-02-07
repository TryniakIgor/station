package com.work.station.mapper;

import com.work.station.dto.CarDTO;
import com.work.station.model.Car;

public class CarMapper {
    public static CarDTO toDTO(Car car){
        return CarDTO.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .licensePlate(car.getLicensePlate())
                .customer(car.getCustomer())
                .engineCapacity(car.getEngineCapacity())
                .completedWorks(car.getCompletedWorks())
                .build();
    }
}
