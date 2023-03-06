package ar.com.ada.backend12.carRental.DAO;

import ar.com.ada.backend12.carRental.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDAO extends JpaRepository<Car, Integer> {
}
