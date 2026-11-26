package client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1, b2, b3;

    Login() {
        setTitle("AUTOMATED TELLER MACHINE");

        ImageIcon i1 = new ImageIcon(
            ClassLoader.getSystemResource("ASimulatorSystem/icons/logo.jpg")
        );
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        l1 = new JLabel("WELCOME TO ATM");
        l1.setFont(new Font("Osward", Font.BOLD, 38));
        l1.setBounds(200, 40, 450, 40);
        add(l1);

        l2 = new JLabel("Card No:");
        l2.setFont(new Font("Raleway", Font.BOLD, 28));
        l2.setBounds(120, 150, 150, 30);
        add(l2);

        tf1 = new JTextField();
        tf1.setFont(new Font("Arial", Font.BOLD, 14));
        tf1.setBounds(300, 150, 250, 30);
        add(tf1);

        l3 = new JLabel("PIN:");
        l3.setFont(new Font("Raleway", Font.BOLD, 28));
        l3.setBounds(120, 220, 150, 30);
        add(l3);

        pf2 = new JPasswordField();
        pf2.setFont(new Font("Arial", Font.BOLD, 14));
        pf2.setBounds(300, 220, 250, 30);
        add(pf2);

        b1 = new JButton("SIGN IN");
        b1.setBounds(300, 300, 100, 30);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("CLEAR");
        b2.setBounds(450, 300, 100, 30);
        b2.addActionListener(this);
        add(b2);

        b3 = new JButton("SIGN UP");
        b3.setBounds(300, 350, 250, 30);
        b3.addActionListener(this);
        add(b3);

        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        setSize(800, 480);
        setLocation(350, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == b2) {
            tf1.setText("");
            pf2.setText("");

        } else if (ae.getSource() == b1) {

            String cardno = tf1.getText().trim();
            String pin = new String(pf2.getPassword());

            if (cardno.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please enter Card Number and PIN");
                return;
            }

            Client client = new Client();

            if (client.verifierLogin(cardno, pin)) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                setVisible(false);
                new Transactios(pin).setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this,
                    "Incorrect Card Number or PIN");
            }

        } else if (ae.getSource() == b3) {
            JOptionPane.showMessageDialog(this,
                "Open your Signup class here.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}