package Seph;
//import ferea;
import javax.swing.*;
import java.io.File;

public class Main {


    public static void main(String[] args) {

        JFrame frame = new JFrame("Fisa lucrari");
        frame.setContentPane(new ferea().fereaView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}



 
