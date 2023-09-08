package models;

public class Car {
    private String licensePlate;
    private String name;
    private CarType carType;
    private boolean isLocated;

    public Car(String licensePlate, String name, CarType carType) {
        this.licensePlate = licensePlate;
        this.name = name;
        this.carType = carType;
        this.isLocated = false;
    }


    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public boolean isLocated() {
        return isLocated;
    }

    public void setLocated(boolean located) {
        isLocated = located;
    }
}
