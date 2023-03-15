package ar.com.ada.backend12.carRental.car.DAO;

import ar.com.ada.backend12.carRental.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarDAO extends JpaRepository<Car, String>, CarDAOCustom {
    @Query("select distinct c.brand from Car c")
    public List<String> getCarBrands();
}
