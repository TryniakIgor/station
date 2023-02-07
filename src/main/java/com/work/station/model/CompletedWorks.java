package com.work.station.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompletedWorks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String discriptionWork;
    private Double pricePerWork;
    private String parts;
    private Double pricePerParts;
    private LocalDateTime workDate;
    @ManyToOne
    private Car car;


}
