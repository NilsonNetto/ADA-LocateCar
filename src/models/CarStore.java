package models;

import java.util.ArrayList;
import java.util.List;

public class CarStore {
    private final List<Car> cars;
    private final List<Client> clients;

    public CarStore(Car initialCar){
        this.cars = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.cars.add(initialCar);
    }

    public void insertCar(String licensePlate, String name, CarType carType){
        for (Car car : cars){
            if(car.getLicensePlate().equals(licensePlate)) {
                System.out.println("Carro já registrado!");
                return;
            }
        }
        cars.add(new Car(licensePlate, name, carType));
    }

    public void insertClient(String name, ClientType clientType, String document){
        if(clients.size() == 0){
            clients.add(new Client(name, clientType, document));
            return;
        }
        for (Client client : clients){
            if(client.getDocument().equals(document)) {
                System.out.println("Cliente já registrado!");
                return;
            }
        }
        clients.add(new Client(name, clientType, document));
    }

    public void listCars (){
        for(int i = 0; i < cars.size(); i++){
            System.out.println( i + " - " + cars.get(i).getName());
        }
    }

    public void rentCar(int carIndex){
        Car rentedCar = cars.get(carIndex);

        if(rentedCar.isLocated()){
            System.out.println("Este carro já está locado!");
        } else {
            rentedCar.setLocated(true);
            System.out.println("Carro locado com sucesso!");
        }
    }

}
