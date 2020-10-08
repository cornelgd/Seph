package Seph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ferea {
    public JButton button1;
    public JPanel fereaView;
    private JTextField textField1;
    private JTextArea textArea1;
    private JTextPane textPane1;
    public static String[] separat;
    public static String[] rezultat;
    public static float xx;
    public static String fisierul;

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

        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textPane1.setText("");
                JFileChooser fisier = new JFileChooser();
                fisier.setCurrentDirectory(new File("C:\\"));
                int result = fisier.showOpenDialog(fisier);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fisier.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile);


                    fisierul = selectedFile.toString();
                } else System.exit(0);


                String separ = "\\\\";
                separat = fisierul.split(separ);
                fisierul = separat[separat.length-1];
                separat = fisierul.split("\\.");
                if (separat.length!=2 || !separat[separat.length-1].toUpperCase().equals("TIF")) {
                    JOptionPane.showMessageDialog(null, "Nume fisier gresit!","Message", JOptionPane.ERROR_MESSAGE);
                  return; // System.exit(0);
                } else {
                    fisierul = separat[separat.length - 2];
                }

                separat = fisierul.split("_");
                if (separat.length != 4) {
                    JOptionPane.showMessageDialog(null, "Numar incorect de parametri!","Message", JOptionPane.ERROR_MESSAGE);
                    return;
                    //  System.exit(0);
                }


                lucrare lucrarecur = new lucrare();
                lucrarecur.client = separat[0];
                lucrarecur.material = mat(separat[1]);
                lucrarecur.laminare = lam(separat[2]);

                String[] dimens = rupere(separat[3], "x");
                if (dimens.length != 2) {
                    JOptionPane.showMessageDialog(null, "Dimensiunea nu este trecuta corect!","Message", JOptionPane.ERROR_MESSAGE);
                    return;//   System.exit(0);
                }

                try {
                    xx = Float.parseFloat(dimens[0]);
                    lucrarecur.dimensiune[0] = dimens[0];
                    xx = Float.parseFloat(dimens[1]);
                    lucrarecur.dimensiune[1] = dimens[1];
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Dimensiunea nu este trecuta corect!","Message", JOptionPane.ERROR_MESSAGE);
                    return;//  System.exit(0);
                }




                String mesaj = "Lucrare curenta:\n"
                        + "Client: " + lucrarecur.client + "\n" + "Material: " + lucrarecur.material + "\n" +
                        "Laminare: " + lucrarecur.laminare + "\n" +
                        "Dimensiune: " + lucrarecur.dimensiune[0] + "x" + lucrarecur.dimensiune[1] + "cm";
               // JOptionPane.showMessageDialog(null, mesaj,"Message", JOptionPane.INFORMATION_MESSAGE);
                textField1.setText(fisierul);
                textPane1.setText(mesaj);
            }

        });




    }


}
