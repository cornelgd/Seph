package Seph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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


        JTextField textClient, textMaterial, textLaminare, textDimx, textDimy, textNrcrt, textId;
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
        textNrcrt.setVisible(false);
        textId.setVisible(false);

        textClient.setBounds(460, 45, 100, 25);
        frame.add(textClient);
        textMaterial.setBounds(460, 80, 300, 25);
        frame.add(textMaterial);
        textLaminare.setBounds(460, 115, 300, 25);
        frame.add(textLaminare);
        textDimx.setBounds(460, 150, 40, 25);
        frame.add(textDimx);
        textDimy.setBounds(610, 150, 40, 25);
        frame.add(textDimy);
        textNrcrt.setBounds(620, 45, 40, 25);
        textNrcrt.setEditable(false);
        frame.add(textNrcrt);
        textId.setBounds(710, 45, 40, 25);
        frame.add(textId);

        JLabel etichClient, etichMaterial, etichLaminare, etichX, etichY,etichNrcrt,etichId;
        etichClient = new JLabel();
        etichMaterial = new JLabel();
        etichLaminare = new JLabel();
        etichX = new JLabel();
        etichY = new JLabel();
        etichNrcrt = new JLabel();
        etichId = new JLabel();

        etichClient.setText("Client");
        etichClient.setBounds(370, 45, 100, 25);
        etichClient.setVisible(false);
        frame.add(etichClient);
        etichMaterial.setText("Material");
        etichMaterial.setBounds(370, 80, 100, 25);
        etichMaterial.setVisible(false);
        frame.add(etichMaterial);
        etichLaminare.setText("Laminare");
        etichLaminare.setBounds(370, 115, 100, 25);
        etichLaminare.setVisible(false);
        frame.add(etichLaminare);
        etichX.setText("Dimensiune H");
        etichX.setBounds(370, 150, 100, 25);
        etichX.setVisible(false);
        frame.add(etichX);
        etichY.setText("Dimensiune V");
        etichY.setBounds(520, 150, 100, 25);
        etichY.setVisible(false);
        frame.add(etichY);
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

                    /*lucrari.remove(nrcrt);
                    lucrare lucrarecur = new lucrare( nrcrt, idcur, textClient.getText(), textMaterial.getText(), textLaminare.getText(),textDimx.getText(), textDimy.getText() );
                    lucrari.add(nrcrt, lucrarecur);
      */
                    appI.update(Integer.parseInt(textNrcrt.getText()), Integer.parseInt(textId.getText()), textClient.getText(),  textMaterial.getText(),  textLaminare.getText(),  Integer.parseInt(textDimx.getText()),  Integer.parseInt(textDimy.getText()));

               

            }
        });



        listFis1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list1 = (JList)evt.getSource();
                list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 0) {
                    int index = list1.getSelectedIndex();
                    textClient.setVisible(true);
                    textMaterial.setVisible(true);
                    textLaminare.setVisible(true);
                    textDimx.setVisible(true);
                    textDimy.setVisible(true);
                    textNrcrt.setVisible(true);
                    textId.setVisible(true);
                    textClient.setText(fisiereSel.get(index).client);
                    textMaterial.setText(fisiereSel.get(index).material);
                    textLaminare.setText(fisiereSel.get(index).laminare);
                    textDimx.setText(fisiereSel.get(index).dimx);
                    textDimy.setText(fisiereSel.get(index).dimy);
                    textNrcrt.setText(String.valueOf(fisiereSel.get(index).nrcrt));
                    textId.setText(String.valueOf(fisiereSel.get(index).id));


                    etichClient.setVisible(true);
                    etichMaterial.setVisible(true);
                    etichLaminare.setVisible(true);
                    etichX.setVisible(true);
                    etichY.setVisible(true);
                    etichNrcrt.setVisible(true);
                    etichId.setVisible(true);
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

