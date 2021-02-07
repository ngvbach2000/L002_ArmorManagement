/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.service;

import bachnv.entity.ArmorDTO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ngvba
 */
public class ArmorServer extends UnicastRemoteObject implements ArmorInterface {

    String fileName;
    List<ArmorDTO> armor = new ArrayList<>();
    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ArmorServer(String fileName) throws RemoteException {
        super();
        this.fileName = fileName;
    }

    @Override
    public boolean createArmor(ArmorDTO dto) throws RemoteException {
        try {
            if (dto != null) {
                armor.add(dto);
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(ArmorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArmorDTO findByArmorID(String id) throws RemoteException {
        try {
            if (id != null) {
                for (int i = 0; i < armor.size(); i++) {
                    if (armor.get(i).getArmorId().equals(id)) {
                        return armor.get(i);
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ArmorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ArmorDTO> findAllArmor() throws RemoteException {
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        armor.clear();
        try {
            f = new File(fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String line;
            StringTokenizer stk;
            while ((line = br.readLine()) != null) {
                stk = new StringTokenizer(line, "|");
                String armorId = stk.nextToken();
                String classification = stk.nextToken();
                String description = stk.nextToken().replace(">n<", "\n");
                String status = stk.nextToken();
                Date TOC = formatter.parse(stk.nextToken());
                int defense = Integer.parseInt(stk.nextToken());
                ArmorDTO dto = new ArmorDTO(armorId, classification, description, status, TOC, defense);
                armor.add(dto);
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArmorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArmorServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ArmorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return armor;
    }

    @Override
    public boolean removeArmor(String id) throws RemoteException {
        try {
            if (id != null) {
                for (int i = 0; i < armor.size(); i++) {
                    if (armor.get(i).getArmorId().equals(id)) {
                        armor.remove(i);
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ArmorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateArmor(ArmorDTO dto) throws RemoteException {
        try {
            if (dto != null) {
                for (int i = 0; i < armor.size(); i++) {
                    if (armor.get(i).getArmorId().equals(dto.getArmorId())) {
                        armor.set(i, dto);
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ArmorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean saveToFile() throws RemoteException {
        File f = null;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            f = new File(fileName);
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);
            for (int i = 0; i < armor.size(); i++) {
                String TOC = formatter.format(armor.get(i).getTimeOfCreate());
                String s = armor.get(i).getArmorId()
                        + "|"
                        + armor.get(i).getClassification()
                        + "|"
                        + armor.get(i).getDescription().replace("\n", ">n<")
                        + "|"
                        + armor.get(i).getStatus()
                        + "|"
                        + TOC
                        + "|"
                        + armor.get(i).getDefense();
                pw.println(s);
            }
            pw.close();
            fw.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ArmorServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
