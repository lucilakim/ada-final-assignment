package ar.com.ada.backend12.carRental.user.service;

import ar.com.ada.backend12.carRental.exception.BadRequestException;
import ar.com.ada.backend12.carRental.exception.UnauthorizedException;
import ar.com.ada.backend12.carRental.user.DAO.UserDAO;
import ar.com.ada.backend12.carRental.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDAO userDAO;

    @Override
    public User save(String username, String password, Date expirationDate){
        User user = new User(username, password, expirationDate);

        Boolean existUsername = this.get(user.getUsername()).isPresent();
        if (existUsername) {
            throw new BadRequestException(String.format("Username %s already exists. Please try another username.", username));
        }

        return userDAO.save(user);
    }

    @Override
    public Optional<User> get(String username) {
        return userDAO.findById(username);
    }

    @Override
    public void login(String username, String password) {
        if (Objects.isNull(username) && Objects.isNull(password)) {
            throw new BadRequestException("Missing username and password in the header. Please fill them and try again.");
        } else if (Objects.isNull(username)){
            throw new BadRequestException("Missing username in the header. Please fill it and try again..");
        } else if (Objects.isNull(password)){
        throw new BadRequestException("Missing password in the header. Please fill it and try again..");
        }

        Optional<User> user = this.get(username);
        if(user.isEmpty()) {
            throw new UnauthorizedException("Incorrect username and/or password or expired credentials");
        }

        String returnedPassword = user.get().getPassword();
        Date expirationDate = user.get().getExpirationDate();
        Date currentDate = new Date();

        if (!returnedPassword.equals(password) || expirationDate.before(currentDate)) {
            throw new UnauthorizedException("Incorrect username and/or password or expired credentials");
        }
    }
}
