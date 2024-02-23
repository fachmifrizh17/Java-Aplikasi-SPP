/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;

import Entity.EntStaff;
import Interfaces.IntfStaff;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import koneksiDB.Koneksi;

/**
 *
 * @author amwulandesign
 */
public class ImpStaff implements IntfStaff {
    private String query;
    private Koneksi koneksidb;
    public boolean status;
    private ResultSet rsStaff;
    private List<EntStaff>listStaff;
    
    public ImpStaff(){
        koneksidb=new Koneksi();
        koneksidb.getKoneksi();
    }

    @Override
    public boolean insertStaff(EntStaff st) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        status = false;
        query = "insert into staff values('"
                +st.getId()+"','"
                +st.getNama()+"','"
                +st.getPassword()+"','"
                +st.getJk()+"')";
        status = koneksidb.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public boolean updateStaff(EntStaff st) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        status = false;
        query = "update staff set namastaff = '"+st.getNama()+"',password = '"+st.getPassword()+"',jeniskelamin = '"+st.getJk()
                +"'where idstaff = '"+st.getId()+"'";
        status = koneksidb.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public boolean deleteStaff(String id) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        status = false;
        query="delete from staff where idstaff='"+id+"'";
        status = koneksidb.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public List selectStaff() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        query="select * from staff";
        status=koneksidb.eksekusiQuery(query, true);
        if(status){
            rsStaff=koneksidb.getRs();
            listStaff=new ArrayList<EntStaff>();
            try {
                while(rsStaff.next()){
                    EntStaff staff = new EntStaff();
                    staff.setId(rsStaff.getString(1));
                    staff.setNama(rsStaff.getString(2));
                    staff.setPassword(rsStaff.getString(3));
                    staff.setJk(rsStaff.getString(4));
                    listStaff.add(staff);
                }
                rsStaff.close();
                return listStaff;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public String buatStaff() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int id = 0;
        String idBaru = "";
        String query = "SELECT MAX(RIGHT(idstaff,3)) FROM staff";
        status = koneksidb.eksekusiQuery(query, true);
        if (status){
            rsStaff = koneksidb.getRs();
            try {
                rsStaff.next();
                id = Integer.parseInt(rsStaff.getString(1));
                if (id<=0){
                    idBaru = "STA001";
                }else{
                    if (id<10){
                        idBaru = "STA00"+(id+1);
                    }else if(id<100){
                        idBaru = "STA0"+(id+1);
                    }else{
                        idBaru = "STA"+(id+1);
                    }
                }
            } catch (SQLException ex) {
                idBaru = "";
            }
        }
        return idBaru;
    }

    
}
