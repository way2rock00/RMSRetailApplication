package com.deloitte.rmsapp.service;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfInvocationException;

public class TestMB {
    public TestMB() {
    }

    public void clicked(ActionEvent actionEvent) {
        // Add event code here...
        List pnames1 = new ArrayList();
        List params1 = new ArrayList();
        List ptypes1 = new ArrayList();

        try {
            AdfmfJavaUtilities.invokeDataControlMethod("Test", null, "getListOfData", pnames1, params1, ptypes1);
        } catch (AdfInvocationException e) {
        }
    }
}
