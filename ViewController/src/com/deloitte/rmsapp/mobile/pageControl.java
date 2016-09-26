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
        System.out.println("user:" + userName);
        System.out.println("password:" + password);
        if (userName == null || password == null) {

        }
        if (userName.equals("Sup01") && password.equals("Sup123")) {
            System.out.println("valid:");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LoginMessage}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.loginType}", "SUPPLIER");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.loginSuppier}", "3030");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.showExpand}", "true");
        } else if (userName.equals("Buy01") && password.equals("Buy123")) {
            System.out.println("valid:");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.LoginMessage}", "");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.loginType}", "BUYER");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.loginBuyer}", "305");
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.showExpand}", "true");
        } else {
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
        if (userName == null || password == null) {
            pageNav = null;
        }
        if (userName.equals("Sup01") && password.equals("Sup123")) {
            pageNav = "LandingPage";
        } else {
            pageNav = null;
        }

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


}
