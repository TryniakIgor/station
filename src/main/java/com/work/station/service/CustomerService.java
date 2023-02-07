package com.work.station.service;

import com.work.station.dto.CustomerDTO;
import com.work.station.model.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDTO save(Customer customer);

    CustomerDTO getCustomer(String name);

    CustomerDTO getCustomerByCarPlateNumber(String licensePlate);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(String name, Customer customer);

    void deleteCustomer(String name);
}
