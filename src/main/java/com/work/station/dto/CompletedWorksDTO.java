package com.work.station.dto;

import com.work.station.model.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class CompletedWorksDTO {
    private Long id;
    private String performedWork;
    private Double pricePerWork;
    private String parts;
    private Double pricePerParts;
    private LocalDateTime workDate;
    private Car car;
}
