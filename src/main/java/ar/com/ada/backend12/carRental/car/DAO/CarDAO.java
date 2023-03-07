package ar.com.ada.backend12.carRental.car.DAO;

import ar.com.ada.backend12.carRental.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarDAO extends JpaRepository<Car, Integer>, CarDAOCustom {
    public Optional<Car> findByPlate(String plate);
}
