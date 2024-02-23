/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;

import Interfaces.IntfLogin;
import java.sql.ResultSet;
import java.sql.SQLException;
import koneksiDB.Koneksi;


/**
 *
 * @author amwulandesign
 */
public class ImpLogin implements IntfLogin{
    private Koneksi koneksiDb;
    private ResultSet rsLogin;
    private String query;
    private boolean status;
   
    @Override
    public boolean Login(String id, String password) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        query="select * from staff where idstaff='"+ id +"'and password='"+ password +"'";
        status=koneksiDb.eksekusiQuery(query, true);
        if(status){
            rsLogin = koneksiDb.getRs();
            try {
                rsLogin.next();
                if(rsLogin==null){
                    status = false;
                }else{
                    status = true;
                }
            } catch (SQLException ex) {
                status = false;
            }
        }
    return status;
    }
    
    public ImpLogin(){
        koneksiDb = new Koneksi();
        koneksiDb.getKoneksi();
    }
}
