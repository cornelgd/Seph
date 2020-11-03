package Seph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Meniu {
    public  Meniu() {

        JFrame frameMeniu = new JFrame("Welcome");
        frameMeniu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMeniu.setSize(600,500);




        JButton buttonNew=new JButton("New order");
        buttonNew.setBounds(10,105,150,25);
        frameMeniu.add(buttonNew);


        JButton buttonOpen=new JButton("Open");
        buttonOpen.setBounds(10,10,150,25);
        frameMeniu.add(buttonOpen);
        frameMeniu.setLayout(null);
        frameMeniu.setLocationRelativeTo(null);

        frameMeniu.setVisible(true);


        buttonOpen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new fereaReadTable();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });


        buttonNew.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                new ferea();


            }

        });
    }
}
