package com.work.station.service.implementation;

import com.work.station.dto.CustomerDTO;
import com.work.station.exeption.EntityAlreadyExist;
import com.work.station.exeption.ResourseNotFoundExeption;
import com.work.station.mapper.CustomerMapper;
import com.work.station.model.Customer;
import com.work.station.repository.CustomerRepo;
import com.work.station.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;


    @Override
    public CustomerDTO save(Customer customer) {
        log.info("Saving new customer {} to DB", customer.getName());

        Optional<Customer> existingcustomer = Optional.ofNullable(customerRepo.findByPhoneNumber(customer.getPhoneNumber()));
        existingcustomer.ifPresentOrElse(
                (value) -> {
                    throw new EntityAlreadyExist("customer with already exist ", customer.getPhoneNumber().toString());
                }, () -> customerRepo.save(customer)
        );
        return CustomerMapper.toDTO(customerRepo.save(customer));
    }

    @Override
    public CustomerDTO getCustomer(String name) {
        log.info("Fetching by name {}", name);
        return Optional.ofNullable(CustomerMapper.toDTO(customerRepo.findByName(name))).orElseThrow(() -> new ResourseNotFoundExeption("Customer", name));
    }

    @Override
    public CustomerDTO getCustomerByCarPlateNumber(String licensePlate) {
        log.info("Fetching customer by license plate");
        return CustomerMapper.toDTO(Optional.ofNullable(customerRepo.findAllByCarLicensePlate(licensePlate)).orElseThrow(() -> new ResourseNotFoundExeption("Plate number", licensePlate)));
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
        log.info("Fetching all customers");
        return customerRepo.findAll().stream().map(CustomerMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(String name, Customer customer) {
        log.info("Update customer {} ", customer.getName());
        Customer oldCustomer = Optional.ofNullable(customerRepo.findByName(customer.getName())).orElseThrow(() -> new ResourseNotFoundExeption("customer", customer.getName()));
        oldCustomer.setId(customer.getId());
        oldCustomer.setName(customer.getName());
        oldCustomer.setPhoneNumber(customer.getPhoneNumber());

        return CustomerMapper.toDTO(oldCustomer);
    }

    @Override
    public void deleteCustomer(String name) {
        log.info("Deleting customer by name {}", name);
        customerRepo.deleteByName(name);
    }
}
