package com.deloitte.rmsapp.mobile;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfException;

public class pageControl {
    private boolean validated = false;
    public pageControl() {
    }

    public void LoginIn_buttonClick(ActionEvent actionEvent) {
        //Supplier Check
        System.out.println("LoginIn_buttonClick start");
        String userName = AdfmfJavaUtilities.getELValue("#{pageFlowScope.userName}").toString();
        String password = AdfmfJavaUtilities.getELValue("#{pageFlowScope.password}").toString();
        System.out.println("user:" + userName);
        System.out.println("password:" + password);
        if (userName == null || password == null) {

        }
        if (userName.equals("Sup01") && password.equals("Sup123")) {
            System.out.println("valid:");
            this.validated = true;
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LoginMessage}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.loginType}", "SUPPLIER");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.loginSuppier}", "2500");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchSupplier}", "2500");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.showExpand}", "true");
            System.out.println("loginType:" + "SUPPLIER");
            System.out.println("loginSuppier:" + "2500");
            
            AdfmfJavaUtilities.setELValue("#{applicationScope.userName}", "Coca Cola - Chicago");
            AdfmfJavaUtilities.setELValue("#{applicationScope.loginType}", "SUPPLIER");
            AdfmfJavaUtilities.setELValue("#{applicationScope.loginSuppier}", "2500");
            AdfmfJavaUtilities.setELValue("#{applicationScope.searchSupplier}", "2500");
            
            
            
        } else if (userName.equals("Buy01") && password.equals("Buy123")) {
            this.validated = true;
            System.out.println("valid:");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LoginMessage}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.loginType}", "BUYER");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.loginBuyer}", "305");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchBuyer}", "305");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.showExpand}", "true");
            System.out.println("loginType:" + "BUYER");
            System.out.println("loginSuppier:" + "305");
            AdfmfJavaUtilities.setELValue("#{applicationScope.userName}", "Henry Roden");
            AdfmfJavaUtilities.setELValue("#{applicationScope.loginType}", "BUYER");
            AdfmfJavaUtilities.setELValue("#{applicationScope.loginBuyer}", "305");
            AdfmfJavaUtilities.setELValue("#{applicationScope.searchBuyer}", "305");
            
        } else {
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LoginMessage}", "Invalid Credentials Provided.");
        }
        System.out.println("LoginIn_buttonClick end");
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
        System.out.println("landingNavigation start");
        String pageNav;
       
       if (this.validated) {
            
            pageNav = "LandingPage";
            System.out.println("validated true");
        }
        else {
            pageNav = "LandingPage";
            pageNav = null;
        }
        System.out.println("landingNavigation end");
        return pageNav;
    }


    public void fromPOChanged(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchOrderFrom}", valueChangeEvent.getNewValue());
    }

    public void toPOChanged(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchOrderTo}", valueChangeEvent.getNewValue());
    }

    public void buyerChanged(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchBuyer}", valueChangeEvent.getNewValue());
    }

    public void supplierChanged(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchSupplier}", valueChangeEvent.getNewValue());
    }

    public void fromDateChanged(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchFromDate}", valueChangeEvent.getNewValue());
    }

    public void toDateChanged(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchToDate}", valueChangeEvent.getNewValue());
    }

    public void statusChanged(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchStatus}", valueChangeEvent.getNewValue());
    }

    public void itemChanged(ValueChangeEvent valueChangeEvent) {
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.searchItem}", valueChangeEvent.getNewValue());
    }


    public void navClick(ActionEvent actionEvent) {
        String currentFeature = AdfmfJavaUtilities.getELValue("#{pageFlowScope.Nav}").toString();
        try {
           System.out.println("inside try " +currentFeature);
            AdfmfContainerUtilities.gotoFeature(currentFeature);
        } catch (Exception e) {
            System.out.println("inside exception " +currentFeature);
            e.printStackTrace();
        }
    }
}
