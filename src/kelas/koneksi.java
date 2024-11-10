package kelas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {

    private Connection konekSQL;
    private String host = "localhost";
    private String db = "tokokita_202357201028";
    private String user = "root";
    private String password = "";
    private String port = "3306";
    private String url = "jdbc:mysql://"+host+":"+port+"/"+db;
    
    public Connection konekDB () throws SQLException{
        try {
            konekSQL = DriverManager.getConnection(url, user, password);
            System.out.println("Koneksi Berhasil");
        } catch (SQLException sQLException) {
            System.out.println("Koneksi Gagal");
        } 
        return konekSQL;
    
    }
}