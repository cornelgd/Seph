package Seph;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public  class WriteDb {

    public static int howmany, howmany1 ;
    public static int isLastId;
   // public int isLast2;

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



    public  void insert(int nrcrt, int id, String client, String material, String laminare, int dimx, int dimy ) {
        String sql = "INSERT INTO Lucrare(id,client,material,laminare,dimx,dimy) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.conecteaza();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nrcrt);
            pstmt.setInt(2, id);
            pstmt.setString(3, client);
            pstmt.setString(4, material);
            pstmt.setString(5, laminare);
            pstmt.setInt(6, dimx);
            pstmt.setInt(7, dimy);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public  void update(int nrcrt, int id, String client, String material, String laminare, int dimx, int dimy) {
        String sql = "UPDATE Lucrare WHERE id = ? , "
                + "SET client = ? , material = ? , laminare = ? , dimx = ? , dimy = ?";
       /* String sql = "UPDATE Lucrare SET id = ? , "
                + "client = ? "
                + "WHERE id = ?";*/

        try (Connection conn = this.conecteaza();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, nrcrt);
            pstmt.setInt(2, id);
            pstmt.setString(3, client);
            pstmt.setString(4, material);
            pstmt.setString(5, laminare);
            pstmt.setInt(6, dimx);
            pstmt.setInt(7, dimy);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


public    List<lucrare> lucrariCuId = new ArrayList<>();

    public List<lucrare> read(int id) throws SQLException { //, String client, String material, String laminare, int dimx, int dimy) {
        isLastId = 0;
        howmany = 0;
        Connection conn = this.conecteaza();
        String sql ="";
        if (id == 0){
            sql = "SELECT nrcrt, id, client, material, laminare, dimx, dimy FROM Lucrare";
        } else {
            sql = "SELECT nrcrt, id, client, material, laminare, dimx, dimy FROM Lucrare WHERE id = ?";
        }
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (id != 0){
            pstmt.setInt(1, id);
        }

        try (

                ResultSet rs = pstmt.executeQuery()

        )
        {


            if (!rs.next() ){
                    isLastId = 1;
            }
            else
                {

                do {
                   isLastId = 0;
                    howmany++;
                    lucrariCuId.add(new lucrare(
                            rs.getInt("nrcrt"),
                            rs.getInt("id"),
                            rs.getString("client"),
                            rs.getString("material"),
                            rs.getString("laminare"),
                            Integer.toString(rs.getInt("dimx")),
                            Integer.toString(rs.getInt("dimy"))));
                  //  System.out.println(lucrari.get(1).id);
                  //  System.out.println(lucrari.get(2).id);

                } while (rs.next());
            }
        }

        return lucrariCuId;
    }

    public    List<lucrare> fisiere = new ArrayList<>();
    public List<lucrare> readfis(int id) throws SQLException { //, String client, String material, String laminare, int dimx, int dimy) {
        isLastId = 0;
        howmany1 = 0;

        Connection conn = this.conecteaza();
        String sql = "SELECT nrcrt, id, client, material, laminare, dimx, dimy FROM Lucrare WHERE id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (id != 0){
            pstmt.setInt(1, id);
        }

        try (

                ResultSet rs = pstmt.executeQuery()

        )
        {



                do {
                    howmany1++;
                    fisiere.add(new lucrare(
                            rs.getInt("nrcrt"),
                            rs.getInt("id"),
                            rs.getString("client"),
                            rs.getString("material"),
                            rs.getString("laminare"),
                            Integer.toString(rs.getInt("dimx")),
                            Integer.toString(rs.getInt("dimy"))));

                } while (rs.next());
            }
                return fisiere;
    }





    public int dbsize() throws SQLException {
        Connection conn = this.conecteaza();
        String sql ="select count(*) from Lucrare";
        PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                int dbsize = rs.getInt("count(*)");
               return dbsize;
    }

    public int fissize(int id) throws SQLException {
        Connection conn = this.conecteaza();
        String sql ="select count(id) from Lucrare WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        int fissize = rs.getInt("count(id)");
        return fissize;
    }
    /*public List<lucrare> listaLucrari() throws SQLException {
        //
    }*/


    public  void main(String[] args) {




    }
}