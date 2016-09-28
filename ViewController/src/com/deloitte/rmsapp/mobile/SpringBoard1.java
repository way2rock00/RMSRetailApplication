package com.deloitte.rmsapp.mobile;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SpringBoard1 {
    //String currentFeature;
    private String currentFeature1;
    private String currentFeature2;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public SpringBoard1() {
    }

    public void springBoardNavi(ActionEvent actionEvent) {
        System.out.println("tatata");
        //String currentFeature = AdfmfJavaUtilities.getELValue("#{pageFlowScope.Nav}").toString();
        try {
           System.out.println("inside try " +currentFeature2);
            AdfmfContainerUtilities.gotoFeature(currentFeature2);
       } catch (Exception e) {
            System.out.println("inside exception " +currentFeature2);
            e.printStackTrace();
        }
    }

    public void gotoItem(ActionEvent actionEvent) {
        String currentFeature = AdfmfJavaUtilities.getELValue("#{pageFlowScope.Nav}").toString();
        try {
           System.out.println("inside try " +currentFeature);
            AdfmfContainerUtilities.gotoFeature(currentFeature);
       } catch (Exception e) {
            System.out.println("inside exception " +currentFeature);
            e.printStackTrace();
        }
    }

    public void setCurrentFeature1(String currentFeature1) {
        String oldCurrentFeature1 = this.currentFeature1;
        this.currentFeature1 = currentFeature1;
        _propertyChangeSupport.firePropertyChange("currentFeature1", oldCurrentFeature1, currentFeature1);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        _propertyChangeSupport.removePropertyChangeListener(l);
    }

    public String getCurrentFeature1() {
        return currentFeature1;
    }

    public void setCurrentFeature2(String currentFeature2) {
        String oldCurrentFeature2 = this.currentFeature2;
        this.currentFeature2 = currentFeature2;
        _propertyChangeSupport.firePropertyChange("currentFeature2", oldCurrentFeature2, currentFeature2);
    }

    public String getCurrentFeature2() {
        return currentFeature2;
    }
}
