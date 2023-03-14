package ar.com.ada.backend12.carRental.car.service;

import ar.com.ada.backend12.carRental.car.DAO.CarDAO;
import ar.com.ada.backend12.carRental.car.model.CarList;
import ar.com.ada.backend12.carRental.car.model.Car;
import ar.com.ada.backend12.carRental.contract.model.ContractBase;
import ar.com.ada.backend12.carRental.contract.service.ContractService;
import ar.com.ada.backend12.carRental.exception.BadRequestException;
import ar.com.ada.backend12.carRental.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{
    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
    @Autowired
    CarDAO carDAO;
    @Autowired
    ContractService contractService;

    @Override
    public Car save(Car c){
        Optional<Car> car = carDAO.findById(c.getCarPlateId());
        if(car.isPresent()) {
            throw new BadRequestException(String.format("A car with plate number %s already exists.", c.getCarPlateId()));
        }
        return carDAO.save(c);
    }

    @Override
    public Car update(String plate, Car c) {
        Optional<Car> currentCar = this.get(plate);
        if(currentCar.isEmpty()) {
            throw new NotFoundException(String.format("A car with license plate %s was not found.", plate));
        }
        Car updatedCar = currentCar.get();
        if(c.getBrand() != null) {updatedCar.setBrand(c.getBrand());}
        if(c.getModel() != null) {updatedCar.setModel(c.getModel());}
        if(c.getYear() != null) {updatedCar.setYear(c.getYear());}
        if(c.getColor() != null) {updatedCar.setColor(c.getColor());}
        if(c.getTypeId() != null) {updatedCar.setTypeId(c.getTypeId());}
        if(c.getPassengersNumber() != null) {updatedCar.setPassengersNumber(c.getPassengersNumber());}
        if(c.getMileage() != null) {updatedCar.setMileage(c.getMileage());}
        if(c.getAirConditioning() != null) {updatedCar.setAirConditioning(c.getAirConditioning());}
        if(c.getDailyRent() != null) {updatedCar.setDailyRent(c.getDailyRent());}

        return carDAO.save(updatedCar);
    }

    @Override
    public Optional<Car> get(String carPlateId) {
        Optional<Car> car = carDAO.findById(carPlateId);
        if(car.isEmpty()){
            throw new NotFoundException(String.format("A car with license plate %s was not found.", carPlateId));
        }
        return car;
    }

    @Override
    public CarList getAll(Integer typeId, Integer passengersNumber, String airConditioning, BigDecimal dailyRent){
        List<Car> cars = carDAO.getAll(typeId, passengersNumber, airConditioning, dailyRent);
        if (cars.isEmpty()) {
            throw new NotFoundException("The list of cars (with the filters) is empty.");
        }

        return new CarList(cars);
    }

    @Override
    public void delete(String plate){
        Optional<Car> car = this.get(plate);
        if (car.isEmpty()) {
            throw new NotFoundException(String.format("A car with license plate %s was not found.", plate));
        }

        Optional<ContractBase> contractBase = contractService.getByCarPlateId(car.get().getCarPlateId());
        if(contractBase.isPresent()) {
            throw new BadRequestException(String.format("The car: %s cannot be deleted because it has this contract associated: %s.", car.get().getCarPlateId(), contractBase.get().getContractNumber()));
        }

        carDAO.delete(car.get());
    }
}
