package ar.com.ada.backend12.carRental.car.DAO;

import ar.com.ada.backend12.carRental.car.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarDAO extends JpaRepository<Car, String>, CarDAOCustom {
    @Transactional
    @Modifying
    @Query("update Car c set c.associatedContract = :contractNumber where c.carPlateId = :carPlateId")
    void setContractNumber(@Param("contractNumber") Integer contractNumber,
                           @Param("carPlateId") String carPlateId);
}
