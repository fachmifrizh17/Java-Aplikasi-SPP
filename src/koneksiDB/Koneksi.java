/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksiDB;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author amwulandesign
 */
public class Koneksi {
    private Connection koneksi;
    private ResultSet rs;
    private PreparedStatement ps;

public Connection getKoneksi(){
    if (koneksi == null){
        //memanggil driver untuk koneksi ke MySQL
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //memanggil koneksi database MySQL
            try {
                String url = "jdbc:mysql://localhost:3306/spp";
                koneksi = (Connection) DriverManager.getConnection(url, "root", "");
                System.out.println("Koneksi Sukses");
            } catch (SQLException se) {
                System.out.println("Koneksi Gagal" + se);
                System.exit(0);
            }
            
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Class tidak ditemukan" + cnfe);
            System.exit(0);
        }
    }
    return koneksi;
}

public boolean eksekusiQuery(String query, boolean baris){
    try {
        ps =  (PreparedStatement) koneksi.prepareStatement(query);
        if (baris){
            rs = ps.executeQuery();
        }else{
            ps.executeUpdate();
        }
        return true;
    } catch (SQLException e) {
        return false;
    }
}
    public static void main(String[] args) {
        new Koneksi().getKoneksi();
    }

    public ResultSet getRs() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return rs;
    }
    
}

