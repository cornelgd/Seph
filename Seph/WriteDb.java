package Seph;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public  class WriteDb {


    private  Connection conecteaza() {
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

        try (Connection conn = this.conecteaza();
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

        try (Connection conn = this.conecteaza();
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



    public List<lucrare> read(int id) throws SQLException { //, String client, String material, String laminare, int dimx, int dimy) {

      //  List<lucrare> lucrari = listaLucrari();

        List<lucrare> lucrari = new ArrayList<lucrare>();
        Connection conn = this.conecteaza();
       // int id = 1;
        String sql ="";

        if (id == 0){

            sql = "SELECT id, client, material, laminare, dimx, dimy FROM Lucrare";
            //  pstmt.setInt(1, id);

        } else {
            sql = "SELECT id, client, material, laminare, dimx, dimy FROM Lucrare WHERE id = ?";
        }
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (id != 0){

            pstmt.setInt(1, id);

        }



        try (

                ResultSet rs = pstmt.executeQuery()
        )
        {
          //  System.out.println(lucrari.get(0).client + " inainte de next");
            while (rs.next()) {
                lucrari.add(new lucrare(

                        rs.getInt("id"),
                        rs.getString("client"),
                        rs.getString("material"),
                        rs.getString("laminare"),
                        Integer.toString(rs.getInt("dimx")),
                        Integer.toString(rs.getInt("dimy"))));
               // System.out.println(rs.getString("client")+ " - din rsget");
            }
        }




        return lucrari;





    }


    /*public List<lucrare> listaLucrari() throws SQLException {
        //
    }*/


    public  void main(String[] args) {




    }
}