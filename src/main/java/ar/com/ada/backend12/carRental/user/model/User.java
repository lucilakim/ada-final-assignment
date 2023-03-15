package ar.com.ada.backend12.carRental.user.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "USER")
public class User implements ApiReturnable {
    @Id
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EXPIRATION_DATE")
    private Date expirationDate;

    public User() {
    }

    public User(String username, String password, Date expirationDate) {
        this.username = username;
        this.password = password;
        this.expirationDate = expirationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
