package ar.com.ada.backend12.carRental.car.service;

import ar.com.ada.backend12.carRental.car.DAO.CarTypeDAO;
import ar.com.ada.backend12.carRental.car.model.CarTypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarTypeServiceImpl implements CarTypeService{
    @Autowired
    CarTypeDAO carTypeDAO;

    @Override
    public CarTypeList getAll() {
        return new CarTypeList(carTypeDAO.findAll());
    }
}
