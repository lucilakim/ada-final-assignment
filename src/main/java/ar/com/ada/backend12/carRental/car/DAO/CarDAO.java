package ar.com.ada.backend12.carRental.car.DAO;

import ar.com.ada.backend12.carRental.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarDAO extends JpaRepository<Car, String>, CarDAOCustom {

}
