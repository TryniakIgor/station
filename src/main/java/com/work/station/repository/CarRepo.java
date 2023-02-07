package com.work.station.repository;

import com.work.station.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CarRepo extends PagingAndSortingRepository<Car, Long> {
    Page<Car> findAll(Pageable pageable);
    Car findByLicensePlate(String num);

    //@Query("select c from Car c left join c.customer cu where cu.name =: name")
    List<Car> findAllByCustomerName(String name);
    List<Car> findAllByCustomerPhoneNumber(Byte num);

}
