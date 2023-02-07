package com.work.station.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private Byte phoneNumber;
    private List<CarDTO> carsDTO;
}
