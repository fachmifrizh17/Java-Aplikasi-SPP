/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;

import Entity.EntSiswa;
import Entity.EntSpp;
import Entity.EntTransaksi;
import Interfaces.IntfSiswa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import koneksiDB.Koneksi;



/**
 *
 * @author amwulandesign
 */
public class ImpSiswa implements IntfSiswa{
    private String query;
    private Koneksi koneksidb;
    public boolean status;
    private ResultSet rsSiswa;
    private List<EntSiswa>listSiswa;
    private List<EntSpp>listSpp;
    
    public ImpSiswa(){
        koneksidb=new Koneksi();
        koneksidb.getKoneksi();
    }

    @Override
    public boolean insertSiswa(EntSiswa s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        status = false;
        query = "insert into siswa values('"
                +s.getNisn()+"','"
                +s.getNama()+"','"
                +s.getJk()+"','"
                +s.getAlamat()+"','"
                +s.getKode()+"')";
        status = koneksidb.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public boolean updateSiswa(EntSiswa s) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        status = false;
        query = "update siswa set namasiswa = '"+s.getNama()+"',jeniskelamin = '"+s.getJk()+"',alamat = '"+s.getAlamat()
                +"'where nisn = '"+s.getNisn()+"'";
        status = koneksidb.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public boolean deleteSiswa(String nisn) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        status = false;
        query="delete from siswa where nisn='"+nisn+"'";
        status = koneksidb.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public List selectSiswa(String nisn, String nama) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        query="select nisn, namasiswa, jeniskelamin, alamat, kode_angkatan from siswa where nisn like '%"+nisn+"&' or namasiswa like '%"+nama+"%'";
        status=koneksidb.eksekusiQuery(query, true);
        if(status){
            rsSiswa=koneksidb.getRs();
            listSiswa=new ArrayList<EntSiswa>();
            try {
                while(rsSiswa.next()){
                    EntSiswa siswa = new EntSiswa();
                    siswa.setNisn(rsSiswa.getString(1));
                    siswa.setNama(rsSiswa.getString(2));
                    siswa.setJk(rsSiswa.getString(3));
                    siswa.setAlamat(rsSiswa.getString(4));
                    siswa.setKode(rsSiswa.getString(5));
                    listSiswa.add(siswa);
                }
                rsSiswa.close();
                return listSiswa;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
}

}
