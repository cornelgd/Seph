package Seph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class fereaRead {



    public JTextField textField1;

    List<lucrare> lucrari = new ArrayList<>();

    public static String[] separat;
    public static String[] rezultat;
    public static float xx;





    public fereaRead() {
       // int id = 1;
        JFrame frame = new JFrame("Fisa lucrari");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);


        JButton button1=new JButton("Open");
        button1.setBounds(10,10,150,25);
        frame.add(button1);

        JButton buttonSave=new JButton("Save modifications");
        buttonSave.setBounds(400,405,150,25);
        frame.add(buttonSave);
        buttonSave.setVisible(false);


        textField1 = new JTextField();
        textField1.setBounds(170,10,400,25);
        frame.add(textField1);






        JTextField textField2,textField3,textField4,textField5,textField6;
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        textField6 = new JTextField();
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);
        textField6.setVisible(false);


        textField2.setBounds(260,45,100,25);
        frame.add(textField2);
        textField3.setBounds(260,80,300,25);
        frame.add(textField3);
        textField4.setBounds(260,115,300,25);
        frame.add(textField4);
        textField5.setBounds(260,150,40,25);
        frame.add(textField5);
        textField6.setBounds(410,150,40,25);
        frame.add(textField6);


        JLabel etich1,etich2,etich3,etich4,etich5;
        etich1 = new JLabel();
        etich2 = new JLabel();
        etich3 = new JLabel();
        etich4 = new JLabel();
        etich5 = new JLabel();

        etich1.setText("Client");
        etich1.setBounds(170,45,100,25);
        etich1.setVisible(false);
        frame.add(etich1);
        etich2.setText("Material");
        etich2.setBounds(170,80,100,25);
        etich2.setVisible(false);
        frame.add(etich2);
        etich3.setText("Laminare");
        etich3.setBounds(170,115,100,25);
        etich3.setVisible(false);
        frame.add(etich3);
        etich4.setText("Dimensiune H");
        etich4.setBounds(170,150,100,25);
        etich4.setVisible(false);
        frame.add(etich4);
        etich5.setText("Dimensiune V");
        etich5.setBounds(320,150,100,25);
        etich5.setVisible(false);
        frame.add(etich5);


        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);


        DefaultListModel listModel1 = new DefaultListModel<>();
        JList list1 = new JList<>(listModel1);
        list1.setBounds(10,45, 150,250);
        list1.setOpaque(false);
        list1.setVisible(true);
        frame.add(list1);



        //aici citeste
        int id;
        String client = "";
        String material =""; String laminare = ""; int dimx = 0; int dimy=0;
        WriteDb appR = new WriteDb();
        try {
            lucrari = appR.read(1);//,  client,  material,  laminare,  dimx,  dimy);
            int count = lucrari.size();
            System.out.println(count);
            if (count == 0) {
                //fa ceva sa nu crape daca nu exista cu idul ala
            }
            System.out.println(lucrari.get(0).id +" " +lucrari.get(0).client);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //   System.out.println("1"+  client +  material+  laminare+  Integer.toString(dimx),  Integer.toString(dimy));
        String[] nume = new String[lucrari.size()];
      //  System.out.println(lucrari.get(0).client);
      //  System.out.println(lucrari.get(1).client);
      //  System.out.println(lucrari.get(2).client);
        for ( int i=0;i<lucrari.size();i++){
            nume[i] = lucrari.get(i).client;
          //     System.out.println(nume[i] );
            listModel1.addElement(nume[i]);
        }

















/*        buttonSave.addActionListener(new ActionListener() {
            WriteDb appI = new WriteDb();
            //    appI.insert(id, textField2.getText(),  textField3.getText(),  textField4.getText(),  Integer.parseInt(textField5.getText()),  Integer.parseInt(textField6.getText()));



            @Override
            public void actionPerformed(ActionEvent e) {

                int index = list1.getSelectedIndex();
                if (index != -1)
                {
            int id = 1; //trebuie sa se modifice
                    lucrari.remove(index);
                    lucrare lucrarecur = new lucrare( id, textField2.getText(), textField3.getText(), textField4.getText(),textField5.getText(), textField6.getText() );


                    lucrari.add(index, lucrarecur);



                    appI.insert(id, textField2.getText(),  textField3.getText(),  textField4.getText(),  Integer.parseInt(textField5.getText()),  Integer.parseInt(textField6.getText()));
                    //     appI.update(1, "Sephora", "kmx1", "lucioasa", 111, 222 );



                }
            }
        });*/


        list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list1 = (JList)evt.getSource();
                list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 0) {
                    int index = list1.locationToIndex(evt.getPoint());


                    textField2.setVisible(true);
                    textField3.setVisible(true);
                    textField4.setVisible(true);
                    textField5.setVisible(true);
                    textField6.setVisible(true);
                    textField2.setText(lucrari.get(index).client);
                    textField3.setText(lucrari.get(index).material);
                    textField4.setText(lucrari.get(index).laminare);
                    textField5.setText(lucrari.get(index).dimx);
                    textField6.setText(lucrari.get(index).dimy);

                    etich1.setVisible(true);
                    etich2.setVisible(true);
                    etich3.setVisible(true);
                    etich4.setVisible(true);
                    etich5.setVisible(true);

                    buttonSave.setVisible(true);




                }

            }
        });



    }




}

