package ar.com.ada.backend12.carRental.customer.DAO;

import ar.com.ada.backend12.carRental.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    @Transactional
    @Modifying
    @Query("update Customer c set c.associatedContract = :contractNumber where c.idCardNumber = :idCardNumber")
    void setContractNumber(@Param("contractNumber") Integer contractNumber,
                           @Param("idCardNumber") Integer idCardNumber);
}
