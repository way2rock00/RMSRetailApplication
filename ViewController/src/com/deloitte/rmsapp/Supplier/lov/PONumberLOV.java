package com.deloitte.rmsapp.Supplier.lov;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class PONumberLOV {


    private String poNumberlov;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PONumberLOV() {
        super();
    }

    public PONumberLOV(String poNumber) {
        setPoNumber(poNumber);
    }

    public void setPoNumber(String poNumber) {
        String oldPoNumber = this.poNumberlov;
        this.poNumberlov = poNumber;
        propertyChangeSupport.firePropertyChange("poNumber", oldPoNumber, poNumberlov);
    }

    public String getPoNumber() {
        return poNumberlov;
    private String poNumber;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public PONumberLOV() {
        super();
    }

    public PONumberLOV(String poNumber) {
        setPoNumber(poNumber);
    }

    public void setPoNumber(String poNumber) {
        String oldPoNumber = this.poNumber;
        this.poNumber = poNumber;
        propertyChangeSupport.firePropertyChange("poNumber", oldPoNumber, poNumber);
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
