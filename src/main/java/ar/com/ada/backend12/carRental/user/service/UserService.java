package ar.com.ada.backend12.carRental.user.service;

import ar.com.ada.backend12.carRental.user.model.User;

import java.util.Date;
import java.util.Optional;


public interface UserService {
    public User save(String username, String password, Date expirationDate);
    public Optional<User> get(String username);
    public void login(String username, String password);
}
