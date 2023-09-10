package models;

import util.ConsoleUIHelper;

import java.time.Duration;
import java.time.LocalDateTime;
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
                "Pesquisar Carro",
                "Retornar carro",
                "Sair");

        switch (option){
            case 0:
                insertCar();
                break;
            case 1:
                insertClient();
                break;
            case 2:
                if(cars.size() == 0 || clients.size() == 0){
                    ConsoleUIHelper.showMessageAndWait("Sem clientes cadastrados!", 5);
                    break;
                }
                rentCar();
                break;
            case 3:
                if(rentals.size() == 0){
                    ConsoleUIHelper.showMessageAndWait("Sem reservas ativas!", 5);
                    break;
                }
                listActiveRentals();
                break;
            case 4:
                searchCar();
                break;
            case 5:
                returnCar();
                break;
            case 6:
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
                ConsoleUIHelper.showMessageAndWait("Tipo não disponível! Carro não cadastrado",5);
                return;
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
                ConsoleUIHelper.showMessageAndWait("Tipo não disponível! Cliente não cadastrado",5);
                return;
        }

        clients.add(new Client(name, selectedClientType, document));
    }

    public Car selectCar (){
        ConsoleUIHelper.drawHeader("Lista de carros", 42);
        for(int i = 0; i < cars.size(); i++){
            System.out.println( i + " - " + cars.get(i).getName());
        }
        int select = ConsoleUIHelper.askNumber("Digite o número do carro selecionado").intValue();
        return this.cars.get(select);
    }

    public Car selectCar (List<Car> carsList){
        ConsoleUIHelper.drawHeader("Lista de carros", 42);
        for(int i = 0; i < carsList.size(); i++){
            System.out.println( i + " - " + carsList.get(i).getName());
        }
        int select = ConsoleUIHelper.askNumber("Digite o número do carro selecionado").intValue();
        return carsList.get(select);
    }

    public Client selectClients(){
        ConsoleUIHelper.drawHeader("Lista de clientes", 42);
        for(int i = 0; i < clients.size(); i++){
            System.out.println( i + " - " + clients.get(i).getName());
        }
        int select = ConsoleUIHelper.askNumber("Digite o número do cliente selecionado").intValue();
        return this.clients.get(select);
    }
    public void listActiveRentals(){
        for(int i = 0; i < rentals.size(); i++){
            Rental rental = rentals.get(i);
            if(rental.getReturnDate() == null){
                System.out.println( i + " - " + rental.getRenter().getName() + " - " + rental.getRentedCar().getName() + " - " + rental.getInitialDate());
            }
        }
        ConsoleUIHelper.askSimpleInput("Pressione qualquer tecla para sair");
    }
    public Rental selectActiveRental(){
        List<Rental> activeRentalList = new ArrayList<>();
        for( Rental rental : rentals){
            if(rental.getReturnDate() == null){
                activeRentalList.add(rental);
            }
        }
        ConsoleUIHelper.drawHeader("Lista de reservas", 42);
        for(int i = 0; i < activeRentalList.size(); i++){
            Rental rental = activeRentalList.get(i);
            System.out.println( i + " - " + rental.getRenter().getName() + " - " + rental.getRentedCar().getName() + " - " + rental.getInitialDate());
        }
        int select = ConsoleUIHelper.askNumber("Digite o número da reserva").intValue();
        return activeRentalList.get(select);
    }

    public void rentCar(){
        Car selectedCar = selectCar();
        Client selectedClient = selectClients();
        String locateLocal = ConsoleUIHelper.askNoEmptyInput("Insira o local de locação:",0);

        if(selectedCar.isLocated()){
            ConsoleUIHelper.showMessageAndWait("Este carro já está locado!", 5);
        } else {
            selectedCar.setLocated(true);
            rentals.add(new Rental(selectedCar,selectedClient, locateLocal));
            ConsoleUIHelper.showMessageAndWait("Carro locado com sucesso!",5);
        }
    }

    public void searchCar(){
        String search = ConsoleUIHelper.askSimpleInput("Insira o nome do carro");
        List<Car> carList = new ArrayList<>();
        for (Car car : cars){
            if(car.getName().contains(search)) {
                carList.add(car);
            }
        }
        if(carList.size() == 0 ){
            ConsoleUIHelper.showMessageAndWait("Nenhum carro encontrado!",5);
        } else {
            Car selectedCar = selectCar(carList);
            showCarDetails(selectedCar);

        }
    }

    public void showCarDetails (Car car){
        System.out.println("Placa: " + car.getLicensePlate());
        System.out.println("Nome: " + car.getName());
        System.out.println("Tipo: " + car.getCarType());
        System.out.println("Alugado: " + car.isLocated());
        ConsoleUIHelper.askSimpleInput("Pressione qualquer tecla para sair");
    }

    public void returnCar (){
        Rental rental = selectActiveRental();
        String returnLocal = ConsoleUIHelper.askNoEmptyInput("Insira o local de devolução:", 0);
        rental.setReturnDate(LocalDateTime.now());
        rental.getRentedCar().setLocated(false);
        rental.setReturnLocal(returnLocal);
        double rentedPrice = rental.getValue();
        ConsoleUIHelper.showMessageAndWait("Valor final do aluguel: R$" + rentedPrice,5);
    }
}
