/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.EntSiswa;
import java.util.List;

/**
 *
 * @author amwulandesign
 */
public interface IntfSiswa {
    boolean insertSiswa(EntSiswa s);
    boolean updateSiswa(EntSiswa s);
    boolean deleteSiswa(String nisn);
    List selectSiswa(String nisn, String nama);

}
