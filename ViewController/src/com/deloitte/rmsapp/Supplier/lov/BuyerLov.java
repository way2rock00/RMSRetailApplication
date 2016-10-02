package com.deloitte.rmsapp.Supplier.lov;


import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class BuyerLov {


    private String buyerNumber;
    private String buyerName;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public BuyerLov() {
        super();
    }

    public BuyerLov(String buyerNumber,String buyerName) {
        setBuyerNumber(buyerNumber);
        setBuyerName(buyerName);
    }

    public void setBuyerName(String buyerName) {
        String oldbuyerName = this.buyerName;
        this.buyerName = buyerName;
        propertyChangeSupport.firePropertyChange("buyerName", oldbuyerName, buyerName);
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerNumber(String buyerNumber) {
        String oldsupplierNumber = this.buyerNumber;
        this.buyerNumber = buyerNumber;
        propertyChangeSupport.firePropertyChange("buyerNumber", oldsupplierNumber, buyerNumber);
    }

    public String getBuyerNumber() {
        return buyerNumber;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
