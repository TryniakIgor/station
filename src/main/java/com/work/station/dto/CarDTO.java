package com.work.station.dto;

import com.work.station.model.Brand;
import com.work.station.model.CompletedWorks;
import com.work.station.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CarDTO {
    private Long id;
    private String model;
    private Brand brand;
    private Integer year;
    private String licensePlate;
    private Double engineCapacity;
    private Customer customer;
    private List<CompletedWorks> completedWorks;
}
