import models.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Car initialCar = new Car("ABC1234","Etios", CarType.SMALL);
        CarStore nettoStore = new CarStore(initialCar);

        nettoStore.init();


        //nettoStore.insertCar();

        //nettoStore.insertClient();


        //Car selectedCar = nettoStore.selectCar();
        //Client selectedClient = nettoStore.selectClients();

        //nettoStore.listActiveRentals();
    }
}