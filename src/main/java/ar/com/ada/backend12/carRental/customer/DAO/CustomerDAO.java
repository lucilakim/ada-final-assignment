package ar.com.ada.backend12.carRental.customer.DAO;

import ar.com.ada.backend12.carRental.car.model.Car;
import ar.com.ada.backend12.carRental.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    public Optional<Customer> findByIdCardNumber(Integer idCardNumber);
}
