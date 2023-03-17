package ar.com.ada.backend12.carRental.carType.service;

import ar.com.ada.backend12.carRental.carType.DAO.CarTypeDAO;
import ar.com.ada.backend12.carRental.carType.model.CarTypeList;
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
