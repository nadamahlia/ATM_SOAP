
package Server;

import jakarta.jws.WebService;
import java.sql.*;
import java.time.LocalDateTime;

@WebService(
    endpointInterface = "Server.ATMInterface",
    serviceName = "ATMService",
    portName = "ATMPort",
    targetNamespace = "http://atm/"
)
public class Metier implements ATMInterface {

    private Conn db = new Conn();

    // Vérifier Login
    @Override
    public boolean verifierLogin(String cardno, String pin) {

        String sql =
            "SELECT 1 FROM login WHERE cardno = ? AND pin = ?";

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, cardno);
            ps.setString(2, pin);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur Login", e);
        }
    }

    // Dépôt
    @Override
    public boolean deposer(String pin, String montant) {

        double somme;

        try {
            somme = Double.parseDouble(montant);
        } catch (Exception e) {
            return false;
        }

        if (pin == null || pin.isBlank()
                || !Double.isFinite(somme) || somme <= 0) {
            return false;
        }

        String sql =
            "INSERT INTO bank (pin, date, mode, amount) VALUES (?, ?, ?, ?)";

        try (Connection c = db.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, pin);
            ps.setString(2, LocalDateTime.now().toString());
            ps.setString(3, "Deposit");
            ps.setString(4, String.valueOf(somme));

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Erreur dépôt", e);
        }
    }

    // Calculer le solde
    private double calculerSolde(Connection c, String pin)
            throws SQLException {

        String sql =
            "SELECT mode, amount FROM bank WHERE pin = ?";

        double solde = 0;

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, pin);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    String mode = rs.getString("mode");
                    double montant =
                        Double.parseDouble(rs.getString("amount"));

                    if (mode.equalsIgnoreCase("Deposit")
                            || mode.equalsIgnoreCase("Depot")
                            || mode.equalsIgnoreCase("Dépôt")) {

                        solde += montant;

                    } else if (mode.equalsIgnoreCase("Withdraw")
                            || mode.equalsIgnoreCase("Withdrawal")
                            || mode.equalsIgnoreCase("Retrait")) {

                        solde -= montant;
                    }
                }
            }
        }

        return solde;
    }

    // Retrait
    @Override
    public boolean retirer(String pin, String montant) {

        double somme;

        try {
            somme = Double.parseDouble(montant);
        } catch (Exception e) {
            return false;
        }

        if (pin == null || pin.isBlank()
                || !Double.isFinite(somme)
                || somme <= 0 || somme > 10000) {
            return false;
        }

        try (Connection c = db.getConnection()) {

            c.setAutoCommit(false);

            try {

                // Verrouiller les opérations du même compte
                try (PreparedStatement lock =
                        c.prepareStatement(
                            "SELECT pg_advisory_xact_lock(hashtext(?))")) {

                    lock.setString(1, pin);
                    lock.execute();
                }

                double solde = calculerSolde(c, pin);

                if (solde < somme) {
                    c.rollback();
                    return false;
                }

                String sql =
                    "INSERT INTO bank (pin, date, mode, amount) VALUES (?, ?, ?, ?)";

                try (PreparedStatement ps =
                        c.prepareStatement(sql)) {

                    ps.setString(1, pin);
                    ps.setString(2, LocalDateTime.now().toString());
                    ps.setString(3, "Withdraw");
                    ps.setString(4, String.valueOf(somme));

                    ps.executeUpdate();
                }

                c.commit();
                return true;

            } catch (Exception e) {

                c.rollback();
                throw e;

            } finally {
                c.setAutoCommit(true);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur retrait", e);
        }
    }

    // Consulter le solde
    @Override
    public double getSolde(String pin) {

        try (Connection c = db.getConnection()) {
            return calculerSolde(c, pin);

        } catch (SQLException e) {
            throw new RuntimeException("Erreur solde", e);
        }
    }
}
