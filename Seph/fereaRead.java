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
  //  public int[] indexFis = new int[];
   public  List<Integer> indexFis = new ArrayList<>();
public int nrcrt, idcur;


    public fereaRead() {

       // int id = 1;
        JFrame frame = new JFrame("Fisa lucrari");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,500);


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


        textField2.setBounds(460,45,100,25);
        frame.add(textField2);
        textField3.setBounds(460,80,300,25);
        frame.add(textField3);
        textField4.setBounds(460,115,300,25);
        frame.add(textField4);
        textField5.setBounds(460,150,40,25);
        frame.add(textField5);
        textField6.setBounds(610,150,40,25);
        frame.add(textField6);


        JLabel etich1,etich2,etich3,etich4,etich5;
        etich1 = new JLabel();
        etich2 = new JLabel();
        etich3 = new JLabel();
        etich4 = new JLabel();
        etich5 = new JLabel();

        etich1.setText("Client");
        etich1.setBounds(370,45,100,25);
        etich1.setVisible(false);
        frame.add(etich1);
        etich2.setText("Material");
        etich2.setBounds(370,80,100,25);
        etich2.setVisible(false);
        frame.add(etich2);
        etich3.setText("Laminare");
        etich3.setBounds(370,115,100,25);
        etich3.setVisible(false);
        frame.add(etich3);
        etich4.setText("Dimensiune H");
        etich4.setBounds(370,150,100,25);
        etich4.setVisible(false);
        frame.add(etich4);
        etich5.setText("Dimensiune V");
        etich5.setBounds(520,150,100,25);
        etich5.setVisible(false);
        frame.add(etich5);


        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);


        DefaultListModel listaFis = new DefaultListModel<>();
        JList listFis1 = new JList<>(listaFis);
        listFis1.setBounds(200,45, 150,250);
        listFis1.setOpaque(false);
        listFis1.setVisible(true);
        frame.add(listFis1);






        //aici citeste
        int id;
        String client = "";
        String material =""; String laminare = ""; int dimx = 0; int dimy=0;
        WriteDb appR = new WriteDb();
        try {
            lucrari = appR.read(0);//,  client,  material,  laminare,  dimx,  dimy);
            int count = lucrari.size();
            System.out.println(count);
            if (count == 0) {
                //fa ceva sa nu crape daca nu exista cu idul ala
            }
         //   System.out.println(lucrari.get(0).id +" " +lucrari.get(0).client);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //   System.out.println("1"+  client +  material+  laminare+  Integer.toString(dimx),  Integer.toString(dimy));







        DefaultListModel listaLucr = new DefaultListModel<>();
        JList listLucr1 = new JList<>(listaLucr);
        listLucr1.setBounds(10,45, 150,250);
        listLucr1.setOpaque(true);
        listLucr1.setVisible(true);
        frame.add(listLucr1);
        String[] numelucr = new String[lucrari.size()];
        for ( int i=0;i<lucrari.size();i++){
     //       System.out.println("nr fisiere = " + lucrari.size());
            int id1 = lucrari.get(i).id;
            String client1 = lucrari.get(i).client;
            numelucr[i] = id1 + " - " + client1;
            listaLucr.addElement(numelucr[i]);

            for ( int j=i;j<(lucrari.size()-1);j++) {
                if (lucrari.get(j + 1).id == id1) {
                 //   System.out.println("j = " + j  );
                    i++;
                }
            }

//                 System.out.println("i = " + i);
        }














       buttonSave.addActionListener(new ActionListener() {
            WriteDb appI = new WriteDb();
            //    appI.insert(id, textField2.getText(),  textField3.getText(),  textField4.getText(),  Integer.parseInt(textField5.getText()),  Integer.parseInt(textField6.getText()));



            @Override
            public void actionPerformed(ActionEvent e) {

               /* int index = listFis1.getSelectedIndex();
                if (index != -1)
                {
            //int id = 1; //trebuie sa se modifice*/
                    lucrari.remove(nrcrt);
                    lucrare lucrarecur = new lucrare( idcur, textField2.getText(), textField3.getText(), textField4.getText(),textField5.getText(), textField6.getText() );


                    lucrari.add(nrcrt, lucrarecur);



                    appI.update(idcur, textField2.getText(),  textField3.getText(),  textField4.getText(),  Integer.parseInt(textField5.getText()),  Integer.parseInt(textField6.getText()));
                    //     appI.update(1, "Sephora", "kmx1", "lucioasa", 111, 222 );



               // }
            }
        });


        listFis1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list1 = (JList)evt.getSource();
                list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 0) {

                    int index = list1.locationToIndex(evt.getPoint());
System.out.println("indexdin lista fis - "+index);
nrcrt =indexFis.get(index);
                    textField2.setVisible(true);
                    textField3.setVisible(true);
                    textField4.setVisible(true);
                    textField5.setVisible(true);
                    textField6.setVisible(true);
                    idcur = lucrari.get(nrcrt).id;
                    textField2.setText(lucrari.get(nrcrt).client);
                    textField3.setText(lucrari.get(nrcrt).material);
                    textField4.setText(lucrari.get(nrcrt).laminare);
                    textField5.setText(lucrari.get(nrcrt).dimx);
                    textField6.setText(lucrari.get(nrcrt).dimy);

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
                JList list2 = (JList)evt.getSource();
                list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                if (evt.getClickCount() > 0) {
                    listaFis.removeAllElements();
                  int index1 = list2.locationToIndex(evt.getPoint())+1;
              //    indexa = index1;
           //int id = lucrari.get(index).id;
            System.out.println( "index-"+index1+  " size-"+lucrari.size());
                    //String[] numeF = new String[lucrari.size()];
                 //   int j=1;
                    for ( int i=0;i<lucrari.size();i++) {
                        if (lucrari.get(i).id == index1) {

                            listaFis.addElement(lucrari.get(i).id + lucrari.get(i).client);
                            indexFis.add(i);
                            System.out.println("nr cur - "+i);
                         //   j++;
                        }

                    }






                }

            }
        });









    }




}

