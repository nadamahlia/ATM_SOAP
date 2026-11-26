package Server;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(name = "ATM", targetNamespace = "http://atm/")
public interface ATMInterface {

    @WebMethod
    boolean verifierLogin(@WebParam(name = "cardno") String cardno,
                          @WebParam(name = "pin") String pin);

    @WebMethod
    boolean deposer(@WebParam(name = "pin") String pin,
                    @WebParam(name = "montant") String montant);

    @WebMethod
    boolean retirer(@WebParam(name = "pin") String pin,
                    @WebParam(name = "montant") String montant);

    @WebMethod
    double getSolde(@WebParam(name = "pin") String pin);
}
