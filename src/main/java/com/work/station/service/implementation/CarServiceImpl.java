package com.work.station.service.implementation;

import com.work.station.dto.CarDTO;
import com.work.station.exeption.EntityAlreadyExist;
import com.work.station.exeption.ResourseNotFoundExeption;
import com.work.station.mapper.CarMapper;
import com.work.station.model.Car;
import com.work.station.model.Customer;
import com.work.station.repository.CarRepo;
import com.work.station.repository.CustomerRepo;
import com.work.station.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {

    private final CarRepo carRepo;
    private final CustomerRepo customerRepo;

    @Override
    public CarDTO save(Car car) {
        log.info("Saving new car {} to DB", car.getLicensePlate());

        Optional<Car> existingCar = Optional.ofNullable(carRepo.findByLicensePlate(car.getLicensePlate()));
        existingCar.ifPresentOrElse(
                (value) -> {
                    throw new EntityAlreadyExist("Car with plate number {} already exist ", car.getLicensePlate());
                }, () -> carRepo.save(car)
        );
        return CarMapper.toDTO(carRepo.save(car));
    }

    @Override
    public List<CarDTO> getCarByCustomerName(String name) {
        log.info("Fetching all cars by user {}", name);
        Customer customer = Optional.ofNullable(customerRepo.findByName(name)).orElseThrow(() -> new ResourseNotFoundExeption("Customer", name));
        return customer.getCar().stream().map(CarMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CarDTO getCarByLicensePlate(String plate) {
        log.info("Fetching all cars by licensePlate {}", plate);
        return CarMapper.toDTO(Optional.ofNullable(carRepo.findByLicensePlate(plate)).orElseThrow(() -> new ResourseNotFoundExeption("Plate number", plate)));
    }

    @Override
    public List<CarDTO> getCarByCustomerPhoneNumber(Byte num) {
        log.info("Fetching all cars by phone number {}", num);
        return Optional.ofNullable(carRepo.findAllByCustomerPhoneNumber(num).stream().map(CarMapper::toDTO).collect(Collectors.toList())).orElseThrow(() -> new ResourseNotFoundExeption("Phone number", num.toString()));
    }

    @Override
    public List<CarDTO> getAllCars(Pageable pageable) {
        log.info("Fetching all cars");
        return carRepo.findAll(pageable).stream().map(CarMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CarDTO updateCarByLicensePlate(String plate, Car car) {
        log.info("Update car {} ", car.getLicensePlate());
        Car oldCar = Optional.ofNullable(carRepo.findByLicensePlate(plate)).orElseThrow(() -> new ResourseNotFoundExeption("License plate", plate));
        oldCar.setId(car.getId());
        oldCar.setBrand(car.getBrand());
        oldCar.setModel(car.getModel());
        oldCar.setYear(car.getYear());
        oldCar.setLicensePlate(car.getLicensePlate());
        oldCar.setEngineCapacity(car.getEngineCapacity());
        oldCar.setCustomer(car.getCustomer());

        return CarMapper.toDTO(oldCar);
    }

    @Override
    public void deleteCar(Long id) {
        log.info("Deleting customer by id {}", id);
        carRepo.deleteById(id);
    }
}
