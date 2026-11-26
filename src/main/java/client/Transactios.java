package client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Transactios extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1, b2;
    String pin;

    Transactios(String pin) {
        this.pin = pin;

ImageIcon i1 = new ImageIcon(
    getClass().getResource("/ASimulatorSystem/icons/atm.jpg")
);
        Image i2 = i1.getImage().getScaledInstance(
            1000, 1180, Image.SCALE_DEFAULT
        );
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(0, 0, 960, 1080);
        add(l2);

        l1 = new JLabel("Please Select Your Transaction");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));

        b1 = new JButton("DEPOSIT");
        b2 = new JButton("WITHDRAWAL");

        setLayout(null);

        l1.setBounds(235, 400, 700, 35);
        l2.add(l1);

        b1.setBounds(170, 499, 150, 35);
        l2.add(b1);

        b2.setBounds(390, 499, 150, 35);
        l2.add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(960, 1080);
        setLocation(500, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == b1) {
            setVisible(false);
            new Deposit(pin).setVisible(true);

        } else if (ae.getSource() == b2) {
            setVisible(false);
            new Withdrawl(pin).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Transactios("").setVisible(true);
    }
}