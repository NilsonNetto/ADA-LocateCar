package models;

import java.time.LocalDateTime;

public class rental {
    private Car rentedCar;
    private Client renter;
    private LocalDateTime initialDate;
    private LocalDateTime returnDate;

    public rental(Car rentedCar, Client renter) {
        this.rentedCar = rentedCar;
        this.renter = renter;
        this.initialDate = LocalDateTime.now();
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    public Client getRenter() {
        return renter;
    }

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
