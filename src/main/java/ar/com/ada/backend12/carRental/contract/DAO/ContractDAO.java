package ar.com.ada.backend12.carRental.contract.DAO;

import ar.com.ada.backend12.carRental.contract.model.ContractBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractDAO extends JpaRepository<ContractBase, Integer> {
    public Optional<ContractBase> findByCarPlateId(String carPlateId);
    public Optional<ContractBase> findByIdCardNumber(Integer idCardNumber);
}
