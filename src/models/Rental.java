package models;

import java.time.Duration;
import java.time.LocalDateTime;

public class Rental {
    private Car rentedCar;
    private Client renter;
    private LocalDateTime initialDate;
    private String initialLocal;
    private LocalDateTime returnDate;
    private String returnLocal;
    public Rental(Car rentedCar, Client renter, String initialLocal) {
        this.rentedCar = rentedCar;
        this.renter = renter;
        this.initialLocal = initialLocal;
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

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getInitialLocal() {
        return initialLocal;
    }

    public String getReturnLocal() {
        return returnLocal;
    }

    public void setReturnLocal(String returnLocal) {
        this.returnLocal = returnLocal;
    }

    public double getValue(){
        long secondsRented = Duration.between(initialDate, returnDate).toSeconds();
        int daysRented = (int)Math.floor( secondsRented / 86400.0) + 1;
        CarType rentedCarType = rentedCar.getCarType();
        double rentedValue = 0.0;
        switch (rentedCarType){
            case SMALL:
                rentedValue = daysRented * 100;
                break;
            case MEDIUM:
                rentedValue = daysRented * 150;
                break;
            case SUV:
                rentedValue = daysRented * 200;
                break;
        }
        ClientType renterType = renter.getClientType();

        if(renterType == ClientType.CPF && daysRented > 5){
            rentedValue = rentedValue * 0.95;
        }
        if (renterType == ClientType.CNPJ && daysRented > 3){
            rentedValue = rentedValue * 0.9;
        }
        return rentedValue;
    }
}
