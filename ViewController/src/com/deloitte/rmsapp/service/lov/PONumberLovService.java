package com.deloitte.rmsapp.service.lov;

import com.deloitte.rmsapp.Supplier.lov.PONumberLOV;
import com.deloitte.rmsapp.utility.RestURIs;
import com.deloitte.rmsapp.utility.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;


public class PONumberLovService {

    private static List<PONumberLOV> poNumberList = new ArrayList<PONumberLOV>();

    public PONumberLovService() {
        super();
    }

    public PONumberLOV[] getPoNumber() {
        PONumberLOV[] PoNumberArray = null;
        ServiceManager serviceManager = new ServiceManager();
        PONumberLOV dummyRec = new PONumberLOV("Pick One");
        poNumberList.add(dummyRec);

        String loginNumber;
        String loginType = AdfmfJavaUtilities.getELValue("#{applicationScope.loginType}").toString();

        if (loginType.equals("SUPPLIER")) {
            loginNumber = AdfmfJavaUtilities.getELValue("#{applicationScope.loginSuppier}").toString();
        } else {
            loginNumber = AdfmfJavaUtilities.getELValue("#{applicationScope.loginBuyer}").toString();
        }

        String url = RestURIs.getPONumberLovURI(loginType, loginNumber);
        System.out.println("poNumber Lov url:" + url);
        String jsonArrayAsString = serviceManager.invokeREAD(url);


        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parent = jsonObject.getJSONObject("X_ORDER_NO_TAB");
            JSONArray nodeArray = parent.getJSONArray("X_ORDER_NO_TAB_ITEM");

            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String orderNo = null;
                if (temp.getString("ORDER_NO") != null)
                    orderNo = temp.getString("ORDER_NO");


                PONumberLOV PONumberLOVRec = new PONumberLOV(orderNo);

                poNumberList.add(PONumberLOVRec);


            }
            PoNumberArray = poNumberList.toArray(new PONumberLOV[poNumberList.size()]);
        } catch (Exception e) {
            e.getMessage();
        }
        return PoNumberArray;
    }

}
