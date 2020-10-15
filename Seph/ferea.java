package Seph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ferea {
  //  public JButton button1;
  //  public JPanel fereaView;
    public JTextField textField1;
    //public  JTextPane textPane1;
  //   JList listaLucrari;
    //int id =0;
    List<lucrare> lucrari = new ArrayList<lucrare>();

    public static String[] separat;
    public static String[] rezultat;
    public static float xx;

    public static String[] rupere(String textul, String separator) {

        rezultat = textul.split(separator);
        return rezultat;
    }


    public static String mat(String text1) {
        return switch (text1) {
            case "kmx3" -> "Komatex 3 mm";
            case "kmx1" -> "Komatex 1 mm";
            default -> "Material necunoscut";
        };
    }

    public static String lam(String text2) {
        return switch (text2) {
            case "plm" -> "Laminare mata";
            case "pll" -> "Laminare lucioasa";
            default -> "Laminare necunoscuta";
        };


    }



    public ferea() {

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

        DefaultListModel listModel1 = new DefaultListModel<>();

         JList list1 = new JList<>(listModel1);
        list1.setBounds(10,45, 150,250);
        list1.setOpaque(false);
        frame.add(list1);


/*textPane1 = new JTextPane();
textPane1.setBounds(170,45,400,250);
textPane1.setOpaque(false);
frame.add(textPane1);*/

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



        button1.addActionListener(new ActionListener() {

            String mesaj = "";
          //  String textul ="";
            @Override
            public void actionPerformed(ActionEvent e) {
               /* textField1.setText("");
                textPane1.setText("");
*/
                JFileChooser fisier = new JFileChooser();
                fisier.setMultiSelectionEnabled(true);
                fisier.setCurrentDirectory(new File("C:\\"));
                Component frame = null;
               fisier.showOpenDialog(frame);
                File[] files = fisier.getSelectedFiles();
                int lungime = files.length;
                String[] nume = new String[lungime];

                for ( int i=0;i<lungime;i++){
                    nume[i] = files[i].toString();
                    System.out.println(nume[i] );
                   mesaj = procesare(nume[i]);
                   listModel1.addElement(nume[i]);

                }


/*

                mesaj = "";
                textul = "";
*/




            }

            public  String procesare(String fisierul) {

                String separ = "\\\\";
                separat = fisierul.split(separ);
                fisierul = separat[separat.length - 1];
                separat = fisierul.split("\\.");

                if (separat.length != 2 || !separat[separat.length - 1].toUpperCase().equals("TIF")) {
                    JOptionPane.showMessageDialog(null, "Nume fisier gresit!", "Message", JOptionPane.ERROR_MESSAGE);
                    return ""; // System.exit(0);
                } else {
                    fisierul = separat[separat.length - 2];
                }

                separat = fisierul.split("_");
                if (separat.length != 4) {
                    JOptionPane.showMessageDialog(null, "Numar incorect de parametri!", "Message", JOptionPane.ERROR_MESSAGE);
                    return"";
                    //  System.exit(0);
                }




                String[] dimens = rupere(separat[3], "x");
                if (dimens.length != 2) {
                    JOptionPane.showMessageDialog(null, "Dimensiunea nu este trecuta corect!", "Message", JOptionPane.ERROR_MESSAGE);
                    return"";//   System.exit(0);
                }


                try {
                    xx = Float.parseFloat(dimens[0]);
                    xx = Float.parseFloat(dimens[1]);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Dimensiunea nu este trecuta corect!", "Message", JOptionPane.ERROR_MESSAGE);
                    return "";//  System.exit(0);

                }


                lucrare lucrarecur = new lucrare( separat[0], mat(separat[1]), lam(separat[2]),dimens[0], dimens[1] );
                lucrari.add(lucrarecur);



               /* if (textul.equals("")) {
                     textul = fisierul;
                 }
                 else  textul = textul + ", " + fisierul;
               // mesaj = mesaj +"Lucrare curenta:\n"+
                mesaj ="Lucrare curenta:"+

                         "\nClient: " + lucrari.get(id).client + "\n" + "Material: " + lucrari.get(id).material + "\n" +
                        "Laminare: " + lucrari.get(id).laminare + "\n" +
                        "Dimensiune: " + lucrari.get(id).dimx + "x" + lucrari.get(id).dimy + "cm\n\n";
                // JOptionPane.showMessageDialog(null, mesaj,"Message", JOptionPane.INFORMATION_MESSAGE);
                textField1.setText(textul);
               // textPane1.setText(mesaj);
                id++;
*/
                return mesaj;

            }
        });

        buttonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int index = list1.getSelectedIndex();
                if (index != -1)
                {
//nu merge momentan cu multiple selection

                lucrari.remove(index);
                lucrare lucrarecur = new lucrare( textField2.getText(), textField3.getText(), textField4.getText(),textField5.getText(), textField6.getText() );


                lucrari.add(index, lucrarecur);

                            /*lucrari..set(index, listaLucrari.client) = textField2.getText();
                            textField3.setText(lucrari.get(index).material);
                            textField4.setText(lucrari.get(index).laminare);
                            textField5.setText(lucrari.get(index).dimx);
                            textField6.setText(lucrari.get(index).dimy);*/
                }
            }
        });
        list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list1 = (JList)evt.getSource();
                list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 0) {
                    int index = list1.locationToIndex(evt.getPoint());
                  /*String  mesaj ="Lucrare curenta:\n"+
                            "ID: "+ index
                            + "\nClient: " + lucrari.get(index).client + "\n" + "Material: " + lucrari.get(index).material + "\n" +
                            "Laminare: " + lucrari.get(index).laminare + "\n" +
                            "Dimensiune: " + lucrari.get(index).dimx + "x" + lucrari.get(index).dimy + "cm\n\n";
                    textPane1.setText(mesaj);*/

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

