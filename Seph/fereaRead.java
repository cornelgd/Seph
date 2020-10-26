package Seph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class fereaRead {

    public JTextField textField1;

    List<lucrare> lucrari = new ArrayList<>();

    public static String[] separat;
    public static String[] rezultat;
    public static float xx;
    public List<Integer> indexFis = new ArrayList<>();
    public int nrcrt, idcur;
    public  int index1 = 0;

    public  List<lucrare> fisiereSel = new ArrayList<>();

    public fereaRead() throws SQLException {

        JFrame frame = new JFrame("Fisa lucrari");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 500);


        JButton button1 = new JButton("Open");
        button1.setBounds(10, 10, 150, 25);
        frame.add(button1);

        JButton buttonSave = new JButton("Save modifications");
        buttonSave.setBounds(400, 405, 150, 25);
        frame.add(buttonSave);
        buttonSave.setVisible(false);


        textField1 = new JTextField();
        textField1.setBounds(170, 10, 400, 25);
        frame.add(textField1);


        JTextField textField2, textField3, textField4, textField5, textField6;
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


        textField2.setBounds(460, 45, 100, 25);
        frame.add(textField2);
        textField3.setBounds(460, 80, 300, 25);
        frame.add(textField3);
        textField4.setBounds(460, 115, 300, 25);
        frame.add(textField4);
        textField5.setBounds(460, 150, 40, 25);
        frame.add(textField5);
        textField6.setBounds(610, 150, 40, 25);
        frame.add(textField6);


        JLabel etich1, etich2, etich3, etich4, etich5;
        etich1 = new JLabel();
        etich2 = new JLabel();
        etich3 = new JLabel();
        etich4 = new JLabel();
        etich5 = new JLabel();

        etich1.setText("Client");
        etich1.setBounds(370, 45, 100, 25);
        etich1.setVisible(false);
        frame.add(etich1);
        etich2.setText("Material");
        etich2.setBounds(370, 80, 100, 25);
        etich2.setVisible(false);
        frame.add(etich2);
        etich3.setText("Laminare");
        etich3.setBounds(370, 115, 100, 25);
        etich3.setVisible(false);
        frame.add(etich3);
        etich4.setText("Dimensiune H");
        etich4.setBounds(370, 150, 100, 25);
        etich4.setVisible(false);
        frame.add(etich4);
        etich5.setText("Dimensiune V");
        etich5.setBounds(520, 150, 100, 25);
        etich5.setVisible(false);
        frame.add(etich5);


        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);


        DefaultListModel listaFis = new DefaultListModel<>();
        JList listFis1 = new JList<>(listaFis);
        listFis1.setBounds(200, 45, 150, 250);
        listFis1.setOpaque(true);
        listFis1.setVisible(true);
        frame.add(listFis1);











        DefaultListModel listaLucr = new DefaultListModel<>();
        JList listLucr1 = new JList<>(listaLucr);
        listLucr1.setBounds(10, 45, 150, 250);
        listLucr1.setOpaque(true);
        listLucr1.setVisible(true);
        frame.add(listLucr1);

        WriteDb appS = new WriteDb();
        int dbsize = appS.dbsize();//

        WriteDb appR = new WriteDb();
        int [] arrayId = new int [dbsize+1];

        int j  = 0;
        for ( int i=1;i<dbsize;i++)
        {

            appR.read(i);  //read first

            if (WriteDb.isLastId == 1) //then if no elements returned
            {
                break;
            }

           arrayId[j] = appR.lucrariCuId.get(0).id;
            String txtinlista = arrayId[j] + " - " +appR.lucrariCuId.get(0).client;

            listaLucr.addElement(txtinlista);
            appR.lucrariCuId.clear();
            j++;
            };












       buttonSave.addActionListener(new ActionListener() {
            WriteDb appI = new WriteDb();

            @Override
            public void actionPerformed(ActionEvent e) {


                    lucrari.remove(nrcrt);
                    lucrare lucrarecur = new lucrare( nrcrt, idcur, textField2.getText(), textField3.getText(), textField4.getText(),textField5.getText(), textField6.getText() );


                    lucrari.add(nrcrt, lucrarecur);



                    appI.update(nrcrt, idcur, textField2.getText(),  textField3.getText(),  textField4.getText(),  Integer.parseInt(textField5.getText()),  Integer.parseInt(textField6.getText()));

            }
        });



        listFis1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list1 = (JList)evt.getSource();
                list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 0) {
                    int index = list1.getSelectedIndex();
                    textField2.setVisible(true);
                    textField3.setVisible(true);
                    textField4.setVisible(true);
                    textField5.setVisible(true);
                    textField6.setVisible(true);
                    textField2.setText(fisiereSel.get(index).client);
                    textField3.setText(fisiereSel.get(index).material);
                    textField4.setText(fisiereSel.get(index).laminare);
                    textField5.setText(fisiereSel.get(index).dimx);
                    textField6.setText(fisiereSel.get(index).dimy);
                    etich1.setVisible(true);
                    etich2.setVisible(true);
                    etich3.setVisible(true);
                    etich4.setVisible(true);
                    etich5.setVisible(true);
                    buttonSave.setVisible(true);

                }

            }
        });

        listLucr1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                indexFis.clear();
                listaFis.removeAllElements();
                JList list2 = (JList)evt.getSource();
                list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 0) {
                    fisiereSel.clear();
                  index1 = arrayId[list2.getSelectedIndex()];
                    int nrFis = 0;

                    WriteDb fisS = new WriteDb();
                    try {
                        nrFis = fisS.fissize(index1);//
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    int[] arrayId1 = new int[nrFis + 1];

                    WriteDb appRf = new WriteDb();

                    int jk = 0;
                    for (int i = 0; i < nrFis; i++) {

                        try {
                            appRf.readfis(index1);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } //read first

                        arrayId1[jk] = appRf.fisiere.get(i+1).id;
                        String txtinlista1 = arrayId1[jk] + " - " + appRf.fisiere.get(i+1).client;

                        listaFis.addElement(txtinlista1);

                        fisiereSel.add(new lucrare(
                                appRf.fisiere.get(i+1).nrcrt,
                                appRf.fisiere.get(i+1).id,
                                appRf.fisiere.get(i+1).client,
                                appRf.fisiere.get(i+1).material,
                                appRf.fisiere.get(i+1).laminare,
                                appRf.fisiere.get(i+1).dimx,
                                appRf.fisiere.get(i+1).dimy));


                        appRf.fisiere.clear();
                        jk++;


                    }
                }
            }
        });


    }

}

