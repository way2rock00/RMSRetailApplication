package com.deloitte.rmsapp.mobile;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;

public class pageControl {
    public pageControl() {
    }

    public void LoginIn_buttonClick(ActionEvent actionEvent) {
        //Supplier Check
        String userName = AdfmfJavaUtilities.getELValue("#{pageFlowScope.userName}").toString();
        String password = AdfmfJavaUtilities.getELValue("#{pageFlowScope.password}").toString();
        System.out.println("user:"+userName);
        System.out.println("password:"+password);
        if(userName == null || password == null){
            
        }
        if(userName.equals("Sup01") && password.equals("Sup123")){
            System.out.println("valid:");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LoginMessage}", "");                             
        }else{
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LoginMessage}", "Invalid Credentials Provided.");    
        }
    }

    public void passwordChanged(ValueChangeEvent valueChangeEvent) {
        
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.password}", valueChangeEvent.getNewValue());
        
    }


    public void usernameChanged(ValueChangeEvent valueChangeEvent) {
        
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.userName}", valueChangeEvent.getNewValue());
    }

    public void rememberMeChanged(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.rememberMe}", valueChangeEvent.getNewValue());
    }

    public String landingNavigation() {
        // Add event code here...
        String pageNav;
        String userName = AdfmfJavaUtilities.getELValue("#{pageFlowScope.userName}").toString();
        String password = AdfmfJavaUtilities.getELValue("#{pageFlowScope.password}").toString();       
        if(userName == null || password == null){
            pageNav = null;
        }
        if(userName.equals("Sup01") && password.equals("Sup123")){
            pageNav = "LandingPage";            
        }else{
            pageNav = null;
        }
        
        return pageNav;
    }
}
