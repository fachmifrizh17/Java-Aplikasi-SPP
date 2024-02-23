/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.EntStaff;
import java.util.List;

/**
 *
 * @author amwulandesign
 */
public interface IntfStaff {
    boolean insertStaff(EntStaff st);
    boolean updateStaff(EntStaff st);
    boolean deleteStaff(String id);
    List selectStaff();
    String buatStaff();
}
