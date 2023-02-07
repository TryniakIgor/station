package com.work.station.controller;

import com.work.station.dto.CustomerDTO;
import com.work.station.model.Customer;
import com.work.station.service.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${url}")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDTO>> getAllArticle() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @PostMapping("/customer")
    public ResponseEntity<CustomerDTO> saveArticle (@RequestBody Customer customer) {
        return ResponseEntity.ok().body(customerService.save(customer));
    }

    @GetMapping("/customer/{customerName}")
    public ResponseEntity<CustomerDTO> getCarByCustomerName(@PathVariable String name) {
        return ResponseEntity.ok().body(customerService.getCustomer(name));
    }

    @GetMapping("/customer/get_customer_by_license_plate/{licensePlate}")
    public ResponseEntity<CustomerDTO> getCustomerByCarPlateNumber(@PathVariable String licensePlate) {
        return ResponseEntity.ok().body(customerService.getCustomerByCarPlateNumber(licensePlate));
    }

    @PutMapping("/customer/{name}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String name, @RequestBody Customer customer) {
        return ResponseEntity.ok().body(customerService.updateCustomer(name, customer));
    }

    @DeleteMapping("customer/{name}")
    public void deleteCustomer(@PathVariable String name){
       customerService.deleteCustomer(name);
    }
}
