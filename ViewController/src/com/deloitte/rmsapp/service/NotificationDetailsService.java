package com.deloitte.rmsapp.service;

import com.deloitte.rmsapp.Supplier.NotificationDetails;
import com.deloitte.rmsapp.Supplier.POHeaders;
import com.deloitte.rmsapp.utility.RestURIs;
import com.deloitte.rmsapp.utility.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class NotificationDetailsService {
    public NotificationDetailsService() {
        super();
    }

    private static List<NotificationDetails> notificationsList = new ArrayList<NotificationDetails>();

    public NotificationDetails[] getNotifications() {
        NotificationDetails[] notificationArray = null;
        notificationsList = new ArrayList<NotificationDetails>();
        ServiceManager serviceManager = new ServiceManager();


        String notificationType = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.notificationType}");
        String loginType = (String) AdfmfJavaUtilities.getELValue("#{applicationScope.loginType}").toString();
        String supBuyNumber = null;
        if (loginType.equals("SUPPLIER")) {
            supBuyNumber = (String) AdfmfJavaUtilities.getELValue("#{applicationScope.loginSuppier}").toString();
        } else {
            supBuyNumber = (String) AdfmfJavaUtilities.getELValue("#{applicationScope.loginBuyer}").toString();
        }


        String url = RestURIs.getNotificationURI(loginType, supBuyNumber);

        System.out.println("url:" + url);
        String jsonArrayAsString = serviceManager.invokeREAD(url);


        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parent = jsonObject.getJSONObject("P_PO_NOTIFY_TAB");
            JSONArray nodeArray = parent.getJSONArray("P_PO_NOTIFY_TAB_ITEM");

            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String poNumber = null;
                if (temp.getString("PO_NUMBER") != null)
                    poNumber = temp.getString("PO_NUMBER");

                String poOrderType = null;
                if (temp.getString("PO_ORDER_TYPE") != null)
                    poOrderType = temp.getString("PO_ORDER_TYPE");

                String poDate = null;
                if (temp.getString("PO_DATE") != null)
                    poDate = temp.getString("PO_DATE");

                String supBuyer = null;
                if (temp.getString("PO_SUP_BUY_NAME") != null)
                    supBuyer = temp.getString("PO_SUP_BUY_NAME");

                String poRevision = null;
                if (temp.getString("PO_REVISION") != null)
                    supBuyer = temp.getString("PO_REVISION");

                String recordId = null;
                if (temp.getString("RECORD_ID") != null)
                    recordId = temp.getString("RECORD_ID");


                NotificationDetails notificationRecord =
                    new NotificationDetails(recordId, poNumber, poOrderType, poRevision, supBuyer, poDate);

                notificationsList.add(notificationRecord);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        notificationArray = notificationsList.toArray(new NotificationDetails[notificationsList.size()]);
        return notificationArray;
    }

}
