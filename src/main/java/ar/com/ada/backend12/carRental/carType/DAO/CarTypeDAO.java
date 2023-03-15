package ar.com.ada.backend12.carRental.carType.DAO;

import ar.com.ada.backend12.carRental.carType.model.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarTypeDAO extends JpaRepository<CarType, String> {

}
