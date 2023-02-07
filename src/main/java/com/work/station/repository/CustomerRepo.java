package com.work.station.repository;

import com.work.station.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByName(String name);
    Customer findAllByCarLicensePlate(String num);
    Customer findByPhoneNumber(Byte num);
    Page<Customer> findAll(Pageable pageable);
    Customer deleteByName(String name);

}
