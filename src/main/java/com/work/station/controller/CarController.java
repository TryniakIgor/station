package com.work.station.controller;

import com.work.station.dto.CarDTO;
import com.work.station.model.Car;
import com.work.station.repository.CarRepo;
import com.work.station.service.CarService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${url}")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CarController {

    private final CarService carService;
    private final CarRepo carRepo;

    @GetMapping("/car/")
    public ResponseEntity<Map<String, Object>> getCars(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Car> pageTuts = carRepo.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("users", carService.getAllCars(paging));
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());

        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/car")
    public ResponseEntity<CarDTO> saveUsers(@RequestBody Car car) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/car").toUriString());
        return ResponseEntity.created(uri).body(carService.save(car));
    }

    @GetMapping("/car/get_car_by_customer_name/{customerName}")
    public ResponseEntity<List<CarDTO>> getCarByCustomerName(@PathVariable String name) {
        return ResponseEntity.ok().body(carService.getCarByCustomerName(name));
    }

    @GetMapping("/car/get_car_by_customer_phonenumber/{num}")
    public ResponseEntity<List<CarDTO>> getCarByCustomerPhoneNumber(@PathVariable Byte num) {
        return ResponseEntity.ok().body(carService.getCarByCustomerPhoneNumber(num));
    }

    @GetMapping("/car/{licensePlate}")
    public ResponseEntity<CarDTO> getCarByLicensePlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok().body(carService.getCarByLicensePlate(licensePlate));
    }

    @PutMapping("/car/{licensePlate}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable String licensePlate, @RequestBody Car car) {
        return ResponseEntity.ok().body(carService.updateCarByLicensePlate(licensePlate, car));
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity deleteByName(@PathVariable Long id) {
        carService.deleteCar(id);
        return  ResponseEntity.noContent().build();
    }

}
