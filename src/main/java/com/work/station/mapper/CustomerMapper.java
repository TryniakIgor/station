package com.work.station.mapper;

import com.work.station.dto.CustomerDTO;
import com.work.station.model.Customer;

import java.util.stream.Collectors;

public class CustomerMapper {
    public static CustomerDTO toDTO(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .carsDTO(customer.getCar().stream().map(CarMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
