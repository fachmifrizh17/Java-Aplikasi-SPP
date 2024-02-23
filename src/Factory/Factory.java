/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Factory;

import Implement.ImpLogin;
import Implement.ImpSiswa;
import Implement.ImpSpp;
import Implement.ImpStaff;
import Implement.ImpTransaksi;
import Interfaces.IntfLogin;
import Interfaces.IntfSiswa;
import Interfaces.IntfSpp;
import Interfaces.IntfStaff;
import Interfaces.IntfTransaksi;

/**
 *
 * @author amwulandesign
 */
public class Factory {
   
    public static IntfLogin loginDao;
    
    public static IntfLogin getLoginDao(){
        loginDao = new ImpLogin();
        return loginDao;
    }
    
    private static IntfStaff staffDao;
    
    public static IntfStaff getStaffDao(){
        staffDao = new ImpStaff();
        return staffDao;
    }
    
    private static IntfSiswa siswaDao;
    
    public static IntfSiswa getSiswaDao(){
        siswaDao = new ImpSiswa();
        return siswaDao;
    }
    
    private static IntfSpp sppDao;
    public static IntfSpp getSppDao(){
        sppDao = new ImpSpp();
        return sppDao;
    }
    
    private static IntfTransaksi transaksiDao;
    public static IntfTransaksi getTransaksiDao(){
        transaksiDao = new ImpTransaksi();
        return transaksiDao;
    }
}
