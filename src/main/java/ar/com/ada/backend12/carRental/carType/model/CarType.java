package ar.com.ada.backend12.carRental.carType.model;

import ar.com.ada.backend12.carRental.util.api.ApiReturnable;
import jakarta.persistence.*;

@Entity
@Table(name = "CAR_TYPE")
public class CarType implements ApiReturnable {
    @Id
    @Column(name = "CAR_TYPE_NAME")
    String carTypeName;

    public CarType() {
    }

    public CarType(String carTypeName) {
        this.carTypeName = carTypeName;
    }

    public String getCarTypeName() {
        return carTypeName;
    }

    public void setCarTypeName(String carTypeName) {
        this.carTypeName = carTypeName;
    }
}
