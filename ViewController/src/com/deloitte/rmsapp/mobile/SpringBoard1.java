package com.deloitte.rmsapp.mobile;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.exception.AdfException;

public class SpringBoard1 {
    //String currentFeature;
    private String currentFeature1;
    private String currentFeature2;
    private PropertyChangeSupport _propertyChangeSupport = new PropertyChangeSupport(this);

    public SpringBoard1() {
    }

    public void springBoardNavi(ActionEvent actionEvent) {
        System.out.println("Test1");
        try {
            String currentFeature = AdfmfJavaUtilities.getELValue("#{pageFlowScope.Nav}").toString();
            System.out.println("Current Feature1 :" + currentFeature);
            if (currentFeature.equals("com.deloitte.rmsapp.Notification")) {
                // AdfmfContainerUtilities.resetApplication(currentFeature);
                AdfmfContainerUtilities.gotoFeature(currentFeature);
            } else if (currentFeature.equals("Logout")) {
                //AdfmfJavaUtilities.logout();
                
                // AdfmfContainerUtilities.gotoDefaultFeature();
                AdfmfContainerUtilities.resetApplication("moving to login page");
                AdfmfContainerUtilities.resetFeature("com.deloitte.rmsapp.Springboard", false);
                AdfmfContainerUtilities.resetFeature("com.deloitte.rmsapp.Login", true);
                AdfmfContainerUtilities.gotoFeature("com.deloitte.rmsapp.Login");
                System.out.println("Current Feature :" + currentFeature);

            }
            //AdfmfContainerUtilities.gotoFeature(currentFeature);
            System.out.println("Current Feature :" + currentFeature);
        } catch (Exception e) {
            System.out.println("inside exception ");
            System.out.println(e);
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
