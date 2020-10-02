package Seph;

import javax.swing.JFileChooser;
import java.io.File;

public class Main {
    public static String[] separat;
    public static String[] rezultat;
    public static float xx, yy;
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

    public static void main(String[] args) {

        JFileChooser fisier = new JFileChooser();
        fisier.setCurrentDirectory(new File("C:\\"));
        int result = fisier.showOpenDialog(fisier);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fisier.getSelectedFile();
            System.out.println("Selected file: " + selectedFile);


            fisierul = selectedFile.toString();
        } else System.exit(0);

        //
        String separ = "\\\\";
        String[] fisier2 = rupere(fisierul, separ);


        fisierul = fisier2[fisier2.length - 1];

        fisier2 = rupere(fisierul, "\\.");
        if (fisier2.length > 2) {
            System.out.println("Prea multe puncte in numele fisierului!");
            System.exit(0);
        }

        fisierul = fisier2[0];
        separat = fisierul.split("_");
        if (separat.length != 4) {
            System.out.println("Numar incorect de parametri!");
            System.exit(0);
        }


        lucrare lucrarecur = new lucrare();
        lucrarecur.client = separat[0];
        lucrarecur.material = separat[1];
        lucrarecur.laminare = separat[2];

        lucrarecur.material = mat(lucrarecur.material);
        lucrarecur.laminare = lam(lucrarecur.laminare);

        String[] dimens = rupere(separat[3], "x");
        if (dimens.length != 2) {
            System.out.println("Dimensiunea nu este trecuta corect!");
            System.exit(0);
        }

        try {
            xx = Float.parseFloat(dimens[0]);

        } catch (Exception e) {
            xx = 0;
        }

        try {
            yy = Float.parseFloat(dimens[1]);
        } catch (Exception e) {
            yy = 0;
        }

        if (xx != 0 && yy != 0) {

            lucrarecur.dimensiune[0] = dimens[0];
            lucrarecur.dimensiune[1] = dimens[1];


        } else {
            System.out.println("Dimensiunea nu este trecuta corect!");
            System.exit(0);
        }


        System.out.println("Lucrare curenta:\n"
                + "Client: " + lucrarecur.client + "\n" + "Material: " + lucrarecur.material + "\n" +
                "Laminare: " + lucrarecur.laminare + "\n" +
                "Dimensiune: " + lucrarecur.dimensiune[0] + "x" + lucrarecur.dimensiune[1] + "cm");

    }


}



 
