package Seph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Meniu {
    public  Meniu() {

        JFrame frameMeniu = new JFrame("Ce facem");
        frameMeniu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMeniu.setSize(600,500);




        JButton buttonNew=new JButton("Save modifications");
        buttonNew.setBounds(10,105,150,25);
        frameMeniu.add(buttonNew);
      //  buttonNew.setVisible(true);


        JButton buttonOpen=new JButton("Open");
        buttonOpen.setBounds(10,10,150,25);
        frameMeniu.add(buttonOpen);
      //  buttonOpen.setVisible(true);
        frameMeniu.setLayout(null);
        frameMeniu.setVisible(true);


        buttonOpen.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

             //   new ferea();


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
