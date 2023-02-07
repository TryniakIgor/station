package com.work.station.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Brand brand;
    private String model;
    private Integer year;
    private String licensePlate;
    private Double engineCapacity;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy = "car")
    private List<CompletedWorks> completedWorks;
}
