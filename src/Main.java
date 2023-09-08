import models.Car;
import models.CarStore;
import models.CarType;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        CarStore nettoStore = new CarStore(new Car("ABC1234","Etios", CarType.SMALL));

        nettoStore.insertCar("ABC1234","Corolla", CarType.MEDIUM);
        nettoStore.insertCar("ABC0987","Corolla", CarType.MEDIUM);
        nettoStore.insertCar("ZXC0000","Rav4", CarType.SUV);

        nettoStore.listCars();
    }
}