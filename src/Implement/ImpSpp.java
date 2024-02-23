/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;

import Entity.EntSpp;
import Interfaces.IntfSpp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import koneksiDB.Koneksi;

/**
 *
 * @author amwulandesign
 */
public class ImpSpp implements IntfSpp{
    
    private String query;
    private Koneksi koneksidb;
    private boolean status;
    private ResultSet rsSpp;
    private List<EntSpp>listSpp;
    
    public ImpSpp(){
        koneksidb=new Koneksi();
        koneksidb.getKoneksi();
    }

    @Override
    public boolean insertSpp(EntSpp spp) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        status = false;
        query = "insert into spp values('"
                +spp.getKode()+"','"
                +spp.getBiaya()+"')";
        status = koneksidb.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public boolean updateSpp(EntSpp spp) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        status = false;
        query = "update spp set biaya_spp = '"+spp.getBiaya()
                +"'where kode_angkatan = '"+spp.getKode()+"'";
        status = koneksidb.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public boolean deleteSpp(String kode) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        status = false;
        query="delete from spp where kode_angkatan='"+kode+"'";
        status = koneksidb.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public List selectSpp(String kode) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       query="select kode_angkatan, biaya_spp from spp where kode_angkatan like '%"+kode+"%'";
        status=koneksidb.eksekusiQuery(query, true);
        if(status){
            rsSpp=koneksidb.getRs();
            listSpp=new ArrayList<EntSpp>();
            try {
                while(rsSpp.next()){
                    EntSpp spp = new EntSpp();
                    spp.setKode(rsSpp.getString(1));
                    spp.setBiaya(rsSpp.getString(2));
                    listSpp.add(spp);
                }
                rsSpp.close();
                return listSpp;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }
    
}
