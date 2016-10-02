package com.deloitte.rmsapp.Supplier.lov;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SupplierLOV {


    private String supplierNumber;
    private String supplierName;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public SupplierLOV() {
        super();
    }

    public SupplierLOV(String supplierNumber, String supplierName) {
        setSupplierNumber(supplierNumber);
        setSupplierName(supplierName);
    }

    public void setSupplierNumber(String supplierNumber) {
        String oldsupplierNumber = this.supplierNumber;
        this.supplierNumber = supplierNumber;
        propertyChangeSupport.firePropertyChange("supplierNumber", oldsupplierNumber, supplierNumber);
    }

    public void setSupplierName(String supplierName) {
        String oldsupplierName = this.supplierName;
        this.supplierName = supplierName;
        propertyChangeSupport.firePropertyChange("supplierName", oldsupplierName, supplierName);
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierNumber() {
        return supplierNumber;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
