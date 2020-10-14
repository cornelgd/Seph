package Seph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.File;
import java.util.Arrays;

public class ferea {
    public JButton button1;
    public JPanel fereaView;
    public JTextField textField1;
    public  JTextPane textPane1;
     JList listaLucrari;
    // public  JList listaLucrari;



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


textField1 = new JTextField();
textField1.setBounds(170,10,400,25);
        frame.add(textField1);

        DefaultListModel listModel1 = new DefaultListModel<>();
       // listModel1.addElement("Windows");
       // listModel1.addElement("MacOS");
       // listModel1.addElement("Linux");
         JList list1 = new JList<>(listModel1);
        list1.setBounds(10,45, 150,250);
        list1.setOpaque(false);
        frame.add(list1);


textPane1 = new JTextPane();
textPane1.setBounds(170,45,400,250);
textPane1.setOpaque(false);
frame.add(textPane1);





        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);


        button1.addActionListener(new ActionListener() {

            String mesaj = "";
            String textul ="";
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textPane1.setText("");

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
                   mesaj = procesare(nume[i], mesaj);
                   listModel1.addElement(nume[i]);

                }



                mesaj = "";
                textul = "";




            }

            public  String procesare(String fisierul, String mesaj) {

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

                lucrare lucrarecur = new lucrare(separat[0], mat(separat[1]), lam(separat[2]),dimens[0], dimens[1] );



                if (textul.equals("")) {
                     textul = fisierul;
                 }
                 else  textul = textul + ", " + fisierul;

                mesaj = mesaj +"Lucrare curenta:\n"
                        + "Client: " + lucrarecur.client + "\n" + "Material: " + lucrarecur.material + "\n" +
                        "Laminare: " + lucrarecur.laminare + "\n" +
                        "Dimensiune: " + lucrarecur.dimx + "x" + lucrarecur.dimy + "cm\n\n";
                // JOptionPane.showMessageDialog(null, mesaj,"Message", JOptionPane.INFORMATION_MESSAGE);
                textField1.setText(textul);
                textPane1.setText(mesaj);


                return mesaj;

            }
        });



    }



}

