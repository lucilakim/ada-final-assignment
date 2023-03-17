package ar.com.ada.backend12.carRental.car.service;

import ar.com.ada.backend12.carRental.car.DAO.CarBrandDAO;
import ar.com.ada.backend12.carRental.car.model.CarBrands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarBrandServiceImpl implements CarBrandService {
    @Autowired
    CarBrandDAO carBrandDAO;

    @Override
    public CarBrands getAll() {
        return new CarBrands(carBrandDAO.findAll());
    }
}
