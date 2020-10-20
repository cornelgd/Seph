package Seph;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public  class WriteDb {


    private  Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:d:\\baza-ok.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }



    public  void insert(int id, String client, String material, String laminare, int dimx, int dimy ) {
        String sql = "INSERT INTO Lucrare(id,client,material,laminare,dimx,dimy) VALUES(?,?,?,?,?,?)";

        try ( Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, client);
            pstmt.setString(3, material);
            pstmt.setString(4, laminare);
            pstmt.setInt(5, dimx);
            pstmt.setInt(6, dimy);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public  void update(int id, String client, String material, String laminare, int dimx, int dimy) {
        String sql = "UPDATE Lucrare WHERE id = ? , "
                + "SET client = ? , material = ? , laminare = ? , dimx = ? , dimy = ?";
       /* String sql = "UPDATE Lucrare SET id = ? , "
                + "client = ? "
                + "WHERE id = ?";*/

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, client);
            pstmt.setString(3, material);
            pstmt.setString(4, laminare);
            pstmt.setInt(5, dimx);
            pstmt.setInt(6, dimy);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




    public  void main(String[] args) {




    }
}