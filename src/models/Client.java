package models;

public class Client {
    private String name;
    private ClientType clientType;
    private String document;

    public Client(String name, ClientType clientType, String document) {
        this.name = name;
        this.clientType = clientType;
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
