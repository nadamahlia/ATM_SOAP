package Server;

import jakarta.xml.ws.Endpoint;

public class Server {

    public static final String URL = "http://localhost:8080/ATMService";

    public static void main(String[] args) {
        try {
            Endpoint.publish(URL, new Metier());

            System.out.println("Serveur SOAP demarre avec succes !");
            System.out.println("WSDL : " + URL + "?wsdl");
            System.out.println("En attente des clients...");

        } catch (Exception e) {
            System.out.println("Erreur serveur SOAP :");
            e.printStackTrace();
        }
    }
}
