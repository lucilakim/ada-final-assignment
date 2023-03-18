package ar.com.ada.backend12.carRental.car.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CAR_BRAND")
public class CarBrand {
    @Id
    @Column(name = "CAR_BRAND")
    private String carBrand;

    public CarBrand() {
    }

    public CarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }
}
