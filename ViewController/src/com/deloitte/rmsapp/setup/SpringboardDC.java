package com.deloitte.rmsapp.setup;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class SpringboardDC {
    private List<SpringboardItem> springboardItems;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public void setSpringboardItems(List<SpringboardItem> springboardItems) {
        this.springboardItems = springboardItems;
    }

    public List<SpringboardItem> getSpringboardItems() {
        return springboardItems;
    }


    public SpringboardDC() {
        springboardItems = new ArrayList<SpringboardItem>();
//        springboardItems.add(new SpringboardItem("Home", "icon-3-financials fa-lg"));
        springboardItems.add(new SpringboardItem("Notifications", "fa fa-bell-o fa-1x", "com.deloitte.rmsapp.Notification"));
        
        springboardItems.add(new SpringboardItem("Settings", "fa fa-cog fa-1x", "com.deloitte.rmsapp.settings"));
//        springboardItems.add(new SpringboardItem("Settings", "icon-3-financials fa-lg",""));
        springboardItems.add(new SpringboardItem("Logout", "fa fa-sign-out fa-1x","Logout"));        
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
