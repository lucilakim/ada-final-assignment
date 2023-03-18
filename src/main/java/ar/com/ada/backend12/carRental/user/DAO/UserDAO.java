package ar.com.ada.backend12.carRental.user.DAO;

import ar.com.ada.backend12.carRental.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, String> {

}
