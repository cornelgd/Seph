package Seph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ferea {
    public int dbsize, nrcrt, id;
    public JTextField textField1;


    List<lucrare> lucrari = new ArrayList<>();

    public static String[] separat;
    public static String[] rezultat;
    public static float xx;

    public  String[] rupere(String textul, String separator) {

        rezultat = textul.split(separator);
        return rezultat;
    }


    public  String mat(String text1) {
        return switch (text1) {
            case "kmx3" -> "Komatex 3 mm";
            case "kmx1" -> "Komatex 1 mm";
            default -> "Material necunoscut";
        };
    }

    public  String lam(String text2) {
        return switch (text2) {
            case "plm" -> "Laminare mata";
            case "bpll" -> "Laminare lucioasa";
            default -> "Laminare necunoscuta";
        };


    }



    public ferea() {

        JFrame frame = new JFrame("Fisa lucrari");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,500);


        JButton buttonOpen=new JButton("Open");
        buttonOpen.setBounds(10,10,150,25);
        frame.add(buttonOpen);

        JButton buttonSave=new JButton("Save to DB");
        buttonSave.setBounds(400,405,150,25);
        frame.add(buttonSave);
        buttonSave.setVisible(false);

        JButton buttonModify=new JButton("Modify");
        buttonModify.setBounds(200,405,150,25);
        frame.add(buttonModify);
        buttonModify.setVisible(false);


        textField1 = new JTextField();
        textField1.setBounds(170,10,400,25);
        frame.add(textField1);

        DefaultListModel listModel1 = new DefaultListModel<>();

        JList list1 = new JList<>(listModel1);
        list1.setBounds(10,45, 150,250);
        list1.setOpaque(false);
        frame.add(list1);


        JTextField textClient,textMaterial,textLaminare,textDimx,textDimy,textNrcrt,textId;
        textClient = new JTextField();
        textMaterial = new JTextField();
        textLaminare = new JTextField();
        textDimx = new JTextField();
        textDimy = new JTextField();
        textNrcrt = new JTextField();
        textId = new JTextField();

        textClient.setVisible(false);
        textMaterial.setVisible(false);
        textLaminare.setVisible(false);
        textDimx.setVisible(false);
        textDimy.setVisible(false);


        textClient.setBounds(260,45,100,25);
        frame.add(textClient);
        textMaterial.setBounds(260,80,300,25);
        frame.add(textMaterial);
        textLaminare.setBounds(260,115,300,25);
        frame.add(textLaminare);
        textDimx.setBounds(260,150,40,25);
        frame.add(textDimx);
        textDimy.setBounds(410,150,40,25);
        frame.add(textDimy);


        JLabel etichClient,etichMaterial,etichLaminare,etichDimx,etichDimy,etichNrcrt,etichId;
        etichClient = new JLabel();
        etichMaterial = new JLabel();
        etichLaminare = new JLabel();
        etichDimx = new JLabel();
        etichDimy = new JLabel();
        etichId = new JLabel();
        etichNrcrt = new JLabel();

        etichClient.setText("Client");
        etichClient.setBounds(170,45,100,25);
        etichClient.setVisible(false);
        frame.add(etichClient);
        etichMaterial.setText("Material");
        etichMaterial.setBounds(170,80,100,25);
        etichMaterial.setVisible(false);
        frame.add(etichMaterial);
        etichLaminare.setText("Laminare");
        etichLaminare.setBounds(170,115,100,25);
        etichLaminare.setVisible(false);
        frame.add(etichLaminare);
        etichDimx.setText("Dimensiune H");
        etichDimx.setBounds(170,150,100,25);
        etichDimx.setVisible(false);
        frame.add(etichDimx);
        etichDimy.setText("Dimensiune V");
        etichDimy.setBounds(320,150,100,25);
        etichDimy.setVisible(false);
        frame.add(etichDimy);


        textNrcrt.setBounds(620, 45, 40, 25);
        textNrcrt.setEditable(false);
        frame.add(textNrcrt);
        textId.setBounds(710, 45, 40, 25);
        frame.add(textId);

        etichNrcrt.setText("Nrcrt");
        etichNrcrt.setBounds(580, 45, 100, 25);
        etichNrcrt.setVisible(false);
        frame.add(etichNrcrt);
        etichId.setText("Id");
        etichId.setBounds(690, 45, 100, 25);
        etichId.setVisible(false);
        frame.add(etichId);



        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);



        buttonOpen.addActionListener(new ActionListener() {

            String mesaj = "";
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fisier = new JFileChooser();
                fisier.setMultiSelectionEnabled(true);
                fisier.setCurrentDirectory(new File("C:\\"));
                Component frame = null;
                fisier.showOpenDialog(frame);
                File[] files = fisier.getSelectedFiles();
                int lungime = files.length;
                String[] nume = new String[lungime];
                WriteDb appIn = new WriteDb();
                try {
                    dbsize = appIn.dbsize();
                    id = appIn.lastID()+1;
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                for ( int i=0;i<lungime;i++){
                    nrcrt = dbsize+1+i;

                    nume[i] = files[i].getName();
                    //   System.out.println(nume[i] );
                    try {


                        mesaj = procesare(nume[i]);

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    listModel1.addElement(nume[i]);
                }


                textField1.setText(listModel1.toString().substring(1,listModel1.toString().length()-1));





            }

            public  String procesare(String fisierul) throws SQLException {


                separat = fisierul.split("\\.");

                if (separat.length != 2 || !separat[separat.length - 1].toUpperCase().equals("TIF")) {
                    JOptionPane.showMessageDialog(null, "Nume fisier gresit! - " + fisierul, "Message", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                } else {
                    fisierul = separat[separat.length - 2];
                }


                separat = fisierul.split("_");
                if (separat.length != 4) {
                    JOptionPane.showMessageDialog(null, "Numar incorect de parametri! - "+ fisierul, "Message", JOptionPane.ERROR_MESSAGE);
                    // return"";
                    System.exit(0);
                }




                String[] dimens = rupere(separat[3], "x");
                if (dimens.length != 2) {
                    JOptionPane.showMessageDialog(null, "Dimensiunea nu este trecuta corect! - " + fisierul, "Message", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }


                try {
                    xx = Float.parseFloat(dimens[0]);
                    xx = Float.parseFloat(dimens[1]);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Dimensiunea nu este trecuta corect! - " + fisierul, "Message", JOptionPane.ERROR_MESSAGE);
                    return "";//  System.exit(0);

                }




                lucrare lucrarecur = new lucrare( nrcrt, id, separat[0], mat(separat[1]), lam(separat[2]),dimens[0], dimens[1] );
                lucrari.add(lucrarecur);



                return mesaj;

            }
        });


        buttonModify.addActionListener(new ActionListener() {

            //    appI.insert(id, textClient.getText(),  textMaterial.getText(),  textLaminare.getText(),  Integer.parseInt(textDimx.getText()),  Integer.parseInt(textDimy.getText()));



            @Override
            public void actionPerformed(ActionEvent e) {

                int index = list1.getSelectedIndex();
                if (index != -1)
                {
                    nrcrt = lucrari.get(index).nrcrt;
                    lucrari.remove(index);
                    String client = textClient.getText();
                    String material = textMaterial.getText();
                    String laminare = textLaminare.getText();
                    String dimx = textDimx.getText();
                    String dimy = textDimy.getText();
                    lucrare lucrarecur = new lucrare( nrcrt, id, client, material, laminare,dimx, dimy );


                    lucrari.add(index, lucrarecur);


                    // int nrcrt = WriteDb.dbsize();
                   /* for (int i = 0; i < lucrari.size(); i++) {
                       System.out.print(lucrari.get(i).nrcrt + " - "+ lucrari.get(i).id + " - "+ lucrari.get(i).client + " - "+ lucrari.get(i).material + " - "+ lucrari.get(i).laminare + " - "+ Integer.parseInt(lucrari.get(i).dimx) + " - "+ Integer.parseInt(lucrari.get(i).dimy)+ "\n");
                    }*/

                    //     appI.update(1, "Sephora", "kmx1", "lucioasa", 111, 222 );



                }
            }
        });



        buttonSave.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                WriteDb appI = new WriteDb();
                try {
                    for (int i = 0; i < lucrari.size(); i++) {
                        appI.insert(lucrari.get(i).nrcrt, lucrari.get(i).id, lucrari.get(i).client, lucrari.get(i).material, lucrari.get(i).laminare, Integer.parseInt(lucrari.get(i).dimx), Integer.parseInt(lucrari.get(i).dimy));
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            buttonSave.setEnabled(false);
buttonSave.setToolTipText("Already saved, if you want to modify \nopen from main menu");
            }
        });


        list1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list1 = (JList)evt.getSource();
                list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 0) {
                    int index = list1.locationToIndex(evt.getPoint());


                    textClient.setVisible(true);
                    textMaterial.setVisible(true);
                    textLaminare.setVisible(true);
                    textDimx.setVisible(true);
                    textDimy.setVisible(true);
                    textClient.setText(lucrari.get(index).client);
                    textMaterial.setText(lucrari.get(index).material);
                    textLaminare.setText(lucrari.get(index).laminare);
                    textDimx.setText(lucrari.get(index).dimx);
                    textDimy.setText(lucrari.get(index).dimy);

                    etichClient.setVisible(true);
                    etichMaterial.setVisible(true);
                    etichLaminare.setVisible(true);
                    etichDimx.setVisible(true);
                    etichDimy.setVisible(true);

                    buttonModify.setVisible(true);
                    buttonSave.setVisible(true);



                }

            }
        });



    }

    private void writeToFile(String listModel1) {
       /* try {
            FileWriter myDb = new FileWriter("jdbc:sqlite:c:\\Users\\Cc1\\IdeaProjects\\Seph\\Seph\\baza.db",true);
            myDb.write( listModel1.toString().substring(1,listModel1.toString().length()-1)            +"\n");
            myDb.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e1) {
            System.out.println("An error occurred writing data.");
        }*/



    }


}

