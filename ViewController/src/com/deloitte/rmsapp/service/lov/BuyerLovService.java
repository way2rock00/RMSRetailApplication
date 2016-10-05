package com.deloitte.rmsapp.service.lov;

import com.deloitte.rmsapp.Supplier.lov.BuyerLov;
import com.deloitte.rmsapp.Supplier.lov.SupplierLOV;
import com.deloitte.rmsapp.utility.RestURIs;
import com.deloitte.rmsapp.utility.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;


public class BuyerLovService {

    private static List<BuyerLov> BuyerList = new ArrayList<BuyerLov>();

    public BuyerLovService() {
        super();
    }

    public BuyerLov[] getBuyerLov() {
        BuyerLov[] BuyerArray = null;
        ServiceManager serviceManager = new ServiceManager();
        
        BuyerLov dummyRec = new BuyerLov(null, "Select Buyer");
        BuyerList.add(dummyRec);
        
        String loginNumber = "-1";
        String loginType = AdfmfJavaUtilities.getELValue("#{applicationScope.loginType}").toString();

        if (loginType.equals("SUPPLIER")) {
            loginNumber = AdfmfJavaUtilities.getELValue("#{applicationScope.loginSuppier}").toString();
        }

        String url = RestURIs.getBuyerLovURI(loginNumber);
        System.out.println("BuyerLov url:" + url);
        String jsonArrayAsString = serviceManager.invokeREAD(url);

        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONArray nodeArray = jsonObject.getJSONArray("GetBuyerLovOutput");

            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String buyerNumber = null;
                if (temp.getString("BUYER") != null)
                    buyerNumber = temp.getString("BUYER");

                String buyerName = null;
                if (temp.getString("BUYER_NAME") != null)
                    buyerName = temp.getString("BUYER_NAME");


                BuyerLov BuyerLovRec = new BuyerLov(buyerNumber, buyerName);

                BuyerList.add(BuyerLovRec);


            }
            BuyerArray = BuyerList.toArray(new BuyerLov[BuyerList.size()]);
        } catch (Exception e) {
            e.getMessage();
        }
        return BuyerArray;
    }

}
