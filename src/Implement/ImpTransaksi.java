/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implement;

import Entity.EntSiswa;
import Entity.EntTransaksi;
import Interfaces.IntfTransaksi;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import koneksiDB.Koneksi;

/**
 *
 * @author amwulandesign
 */
public class ImpTransaksi implements IntfTransaksi{
    
    private String query;
    private Koneksi koneksidb;
    public boolean status;
    private ResultSet rsTransaksi;
    private List<EntTransaksi>listTransaksi;
    private List<EntSiswa>listSiswa;
    
    public ImpTransaksi(){
        koneksidb=new Koneksi();
        koneksidb.getKoneksi();
    }
    
    @Override
    public boolean insertTransaksi(EntTransaksi t) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        status = false;
        query = "insert into transaksi_pembayaran values('"
                +t.getNotransaksi()+"','"
                +t.getNisn()+"','"
                +t.getTgl()+"','"
                +t.getBulanawal()+"','"
                +t.getBulanakhir()+"','"
                +t.getJumlah()+"')";
        status = koneksidb.eksekusiQuery(query, false);
        return status;
    }

    @Override
    public List selectTransaksi(String notransaksi, String nisn) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        query="select no_transaksi, nisn, tgl_bayar, bulan_awal, bulan_akhir,jumlah_bayar from transaksi_pembayaran where no_transaksi like '%"+notransaksi+"&' or nisn like '%"+nisn+"%'";
        status=koneksidb.eksekusiQuery(query, true);
        if(status){
            rsTransaksi=koneksidb.getRs();
            listTransaksi=new ArrayList<EntTransaksi>();
            try {
                while(rsTransaksi.next()){
                    EntTransaksi tr = new EntTransaksi();
                    tr.setNotransaksi(rsTransaksi.getString(1));
                    tr.setNisn(rsTransaksi.getString(2));
                    tr.setTgl(rsTransaksi.getString(3));
                    tr.setBulanawal(rsTransaksi.getString(4));
                    tr.setBulanakhir(rsTransaksi.getString(5));
                    tr.setJumlah(rsTransaksi.getString(6));
                    listTransaksi.add(tr);
                }
                rsTransaksi.close();
                return listTransaksi;
            } catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public String buatTransaksi() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int notransaksi = 0;
        String notransaksiBaru = "";
        String query = "SELECT MAX(RIGHT(no_transaksi,3)) FROM transaksi_pembayaran";
        status = koneksidb.eksekusiQuery(query, true);
        if (status){
            rsTransaksi = koneksidb.getRs();
            try {
                rsTransaksi.next();
                notransaksi = Integer.parseInt(rsTransaksi.getString(1));
                if (notransaksi<=0){
                    notransaksiBaru = "TRX001";
                }else{
                    if (notransaksi<10){
                        notransaksiBaru = "TRX00"+(notransaksi+1);
                    }else if(notransaksi<100){
                        notransaksiBaru = "TRX0"+(notransaksi+1);
                    }else{
                        notransaksiBaru = "TRX"+(notransaksi+1);
                    }
                }
            } catch (SQLException ex) {
                notransaksiBaru = "";
            }
        }
        return notransaksiBaru;
    }
    
}
