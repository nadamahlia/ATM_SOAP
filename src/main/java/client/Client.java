package client;

import Server.ATMInterface;
import jakarta.xml.ws.Service;
import java.net.URI;
import javax.xml.namespace.QName;

public class Client {

    private static final String WSDL = "http://localhost:8080/ATMService?wsdl";

    private ATMInterface service;

    public Client() {
        try {
            Service ws = Service.create(
                    URI.create(WSDL).toURL(),
                    new QName("http://atm/", "ATMService"));

            service = ws.getPort(ATMInterface.class);

            System.out.println("Connexion SOAP réussie !");

        } catch (Exception e) {
            System.out.println("Erreur connexion SOAP : " + e.getMessage());
        }
    }

    public boolean verifierLogin(String cardno, String pin) {
        try {
            return service != null && service.verifierLogin(cardno, pin);
        } catch (Exception e) {
            System.out.println("Erreur Login : " + e.getMessage());
            return false;
        }
    }

    public boolean deposer(String pin, String montant) {
        try {
            return service != null && service.deposer(pin, montant);
        } catch (Exception e) {
            System.out.println("Erreur Dépôt : " + e.getMessage());
            return false;
        }
    }

    public boolean retirer(String pin, String montant) {
        try {
            return service != null && service.retirer(pin, montant);
        } catch (Exception e) {
            System.out.println("Erreur Retrait : " + e.getMessage());
            return false;
        }
    }

    public double getSolde(String pin) {
        try {
            return service != null ? service.getSolde(pin) : -1;
        } catch (Exception e) {
            System.out.println("Erreur Solde : " + e.getMessage());
            return -1;
        }
    }
}
