package models;

import util.ConsoleUIHelper;

import java.util.ArrayList;
import java.util.List;

public class CarStore {
    private final List<Car> cars;
    private final List<Client> clients;
    private final List<Rental> rentals;

    public CarStore(Car initialCar){
        this.cars = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.rentals = new ArrayList<>();
        this.cars.add(initialCar);
    }

    public void init(){
        do {
            if (!storeMenu()) {
                break;
            }
        } while (true);
    }
    public boolean storeMenu (){
        int option = ConsoleUIHelper.askChooseOption("Escolha uma opção",
                "Inserir Carro",
                "Inserir cliente",
                "Nova reserva",
                "Listar reservas",
                "Sair");

        switch (option){
            case 0:
                System.out.println("car");
                insertCar();
                break;
            case 1:
                System.out.println("cliente");
                insertClient();
                break;
            case 2:
                System.out.println("cliente");
                rentCar();
                break;
            case 3:
                System.out.println("cliente");
                listActiveRentals();
                break;
            case 4:
                return false;
        }
        return true;
    }
    public void insertCar(){
        String licensePlate = ConsoleUIHelper.askNoEmptyInput("Insira a placa do carro:",0);
        for (Car car : cars){
            if(car.getLicensePlate().equals(licensePlate)) {
                ConsoleUIHelper.showMessageAndWait("Carro já registrado!",5);
                return;
            }
        }
        String name = ConsoleUIHelper.askNoEmptyInput("Insira o nome do carro:", 0);
        int option = ConsoleUIHelper.askChooseOption("Selecione o tipo do carro:", CarType.SMALL.name(),CarType.MEDIUM.name(),CarType.SUV.name());
        CarType selectedCarType = CarType.SUV;
        switch (option){
            case 0:
                selectedCarType = CarType.SMALL;
                break;
            case 1:
                selectedCarType = CarType.MEDIUM;
                break;
            case 2:
                selectedCarType = CarType.SUV;
                break;
            default:
                ConsoleUIHelper.showMessageAndWait("Tipo não disponível!",5);
        }

        cars.add(new Car(licensePlate, name, selectedCarType));
    }

    public void insertClient(){
        String document = ConsoleUIHelper.askNoEmptyInput("Insira o documento do cliente:", 0);
        if(clients.size() != 0) {
            for (Client client : clients) {
                if (client.getDocument().equals(document)) {
                    ConsoleUIHelper.showMessageAndWait("Cliente já registrado!", 5);
                    return;
                }
            }
        }
        String name = ConsoleUIHelper.askNoEmptyInput("Insira o nome do cliente:", 0);
        int option = ConsoleUIHelper.askChooseOption("Selecione o tipo de cliente:", ClientType.CPF.name(),ClientType.CNPJ.name());
        ClientType selectedClientType = ClientType.CPF;
        switch (option){
            case 0:
                selectedClientType = ClientType.CPF;
                break;
            case 1:
                selectedClientType = ClientType.CNPJ;
                break;
            default:
                ConsoleUIHelper.showMessageAndWait("Tipo não disponível!",5);
        }

        clients.add(new Client(name, selectedClientType, document));
    }

    public Car selectCar (){
        ConsoleUIHelper.drawHeader("Lista de carros", 24);
        for(int i = 0; i < cars.size(); i++){
            System.out.println( i + " - " + cars.get(i).getName());
        }
        int select = ConsoleUIHelper.askNumber("Digite o número do carro selecionado").intValue();
        return this.cars.get(select);
    }

    public Client selectClients(){
        ConsoleUIHelper.drawHeader("Lista de clientes", 24);
        for(int i = 0; i < clients.size(); i++){
            System.out.println( i + " - " + clients.get(i).getName());
        }
        int select = ConsoleUIHelper.askNumber("Digite o número do cliente selecionado").intValue();
        return this.clients.get(select);
    }
    public void listActiveRentals(){
        for(int i = 0; i < rentals.size(); i++){
            Rental rental = rentals.get(i);
            if(rental.getReturnDate() != null){
                System.out.println( i + " - " + rental.getRenter().getName() + " - " + rental.getRentedCar().getLicensePlate());
            }
        }
    }

    public void rentCar(){
        Car selectedCar = selectCar();
        Client selectedClient = selectClients();

        if(selectedCar.isLocated()){
            System.out.println("Este carro já está locado!");
        } else {
            selectedCar.setLocated(true);
            rentals.add(new Rental(selectedCar,selectedClient));
            System.out.println("Carro locado com sucesso!");
        }
    }

}
