package ar.com.ada.backend12.carRental.user.service;

import ar.com.ada.backend12.carRental.exception.BadRequestException;
import ar.com.ada.backend12.carRental.exception.NotFoundException;
import ar.com.ada.backend12.carRental.user.DAO.UserDAO;
import ar.com.ada.backend12.carRental.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public boolean login(String username, String password){
        Optional<User> user = this.get(username);
        if(user.isEmpty()) {
            throw new NotFoundException(String.format("Username %s is not registered.", username));
        }

        String returnedUserName = user.get().getUsername();
        String returnedPassword = user.get().getPassword();
        if(returnedUserName.equals(username) && returnedPassword.equals(password)) {
            return true;
        }
        return false;
    }
}
