package com.deloitte.rmsapp.service;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class Test {
    public Test() {
        super();
    }
    
    public String[] getListOfData()
    {
        String strCalled=(String)AdfmfJavaUtilities.getELValue("#{pageFlowScope.clickTime}");
        AdfmfJavaUtilities.setELValue("elExpression", strCalled+"::One::");
        String s[]={"112","1234","2342"};
        return s;
    }
}
