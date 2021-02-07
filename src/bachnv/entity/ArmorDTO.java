/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bachnv.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ngvba
 */
public class ArmorDTO implements Serializable{
    private String ArmorId;
    private String Classification;
    private String Description;
    private String Status;
    private Date TimeOfCreate;
    private int Defense;

    public ArmorDTO(String ArmorId, String Classification, String Description, String Status, Date TimeOfCreate, int Defense) {
        this.ArmorId = ArmorId;
        this.Classification = Classification;
        this.Description = Description;
        this.Status = Status;
        this.TimeOfCreate = TimeOfCreate;
        this.Defense = Defense;
    }

    /**
     * @return the ArmorId
     */
    public String getArmorId() {
        return ArmorId;
    }

    /**
     * @param ArmorId the ArmorId to set
     */
    public void setArmorId(String ArmorId) {
        this.ArmorId = ArmorId;
    }

    /**
     * @return the Classification
     */
    public String getClassification() {
        return Classification;
    }

    /**
     * @param Classification the Classification to set
     */
    public void setClassification(String Classification) {
        this.Classification = Classification;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * @return the TimeOfCreate
     */
    public Date getTimeOfCreate() {
        return TimeOfCreate;
    }

    /**
     * @param TimeOfCreate the TimeOfCreate to set
     */
    public void setTimeOfCreate(Date TimeOfCreate) {
        this.TimeOfCreate = TimeOfCreate;
    }

    /**
     * @return the Defense
     */
    public int getDefense() {
        return Defense;
    }

    /**
     * @param Defense the Defense to set
     */
    public void setDefense(int Defense) {
        this.Defense = Defense;
    }
    
    
}
