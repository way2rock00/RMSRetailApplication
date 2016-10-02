package com.deloitte.rmsapp.service.lov;

import com.deloitte.rmsapp.Supplier.lov.SupplierLOV;
import com.deloitte.rmsapp.utility.RestURIs;
import com.deloitte.rmsapp.utility.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;


public class SupplierLovService {

    private static List<SupplierLOV> SupplierList = new ArrayList<SupplierLOV>();

    public SupplierLovService() {
        super();
    }

    public SupplierLOV[] getSupplierLov() {
        SupplierLOV[] SupplierArray = null;
        ServiceManager serviceManager = new ServiceManager();

        String loginNumber = "-1";
        String loginType = AdfmfJavaUtilities.getELValue("#{applicationScope.loginType}").toString();

        if (loginType.equals("BUYER")) {
            loginNumber = AdfmfJavaUtilities.getELValue("#{applicationScope.loginBuyer}").toString();
        }

        String url = RestURIs.getSupplierLovURI(loginNumber);
        System.out.println("SupplierLov url:" + url);
        String jsonArrayAsString = serviceManager.invokeREAD(url);

        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONArray nodeArray = jsonObject.getJSONArray("GetSuppLov_BSOutput");

            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String supplierNumber = null;
                if (temp.getString("SUPPLIER") != null)
                    supplierNumber = temp.getString("SUPPLIER");

                String supplierName = null;
                if (temp.getString("BUYER_NAME") != null)
                    supplierName = temp.getString("SUP_NAME");


                SupplierLOV SupplierLovRec = new SupplierLOV(supplierNumber, supplierName);

                SupplierList.add(SupplierLovRec);


            }
            SupplierArray = SupplierList.toArray(new SupplierLOV[SupplierList.size()]);
        } catch (Exception e) {
            e.getMessage();
        }
        return SupplierArray;
    }

}
