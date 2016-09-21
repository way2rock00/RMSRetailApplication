package com.deloitte.rmsapp.service;

import com.deloitte.rmsapp.utility.ConnectionFactory;

 
import com.deloitte.rmsapp.Supplier.POHeaders;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import com.deloitte.rmsapp.utility.RestURIs;
import com.deloitte.rmsapp.utility.ServiceManager;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class POHeaderService {
    public POHeaderService() {
        super();
    }
    private static List<POHeaders> poHeaderList = new ArrayList<POHeaders>();
    
    public POHeaders[] getPOHeaderData() {
        POHeaders[] poHeadersArray = null;
        poHeaderList = new ArrayList<POHeaders>();
        ServiceManager serviceManager = new ServiceManager();
        String inventoryOrg = null;
        Connection conn = null;
        Statement stmt = null;
     
        String strOrderFromDate= (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.orderFromDate}");
        String strOrderToDate= (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.orderToDate}");
        String strSupplier= (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.supplier}");
        String strBuyer= (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.buyer}");
        String strFromDate= (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.fromDate}");
        String strToDate= (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.toDate}");
        String strStatus= (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.status}");
        String strType= (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.type}");
        String jsonArrayAsString =serviceManager.invokeREAD(RestURIs.getPOHeaderURI(strOrderFromDate, 
                                                                                    strOrderToDate, 
                                                                                    strSupplier, 
                                                                                    strBuyer, 
                                                                                    strFromDate, 
                                                                                    strToDate, 
                                                                                    strStatus, 
                                                                                    strType)
                                                            );
        String strDebug=":"+strOrderFromDate+":"+strOrderToDate+":"+strSupplier+":"+strBuyer+":"+
                        strFromDate+":"+strToDate+":"+strStatus+":"+strType;
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.arrayVal}", strDebug+"::"+jsonArrayAsString);
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parent = jsonObject.getJSONObject("P_PO_HDR_TAB");
            JSONArray nodeArray = parent.getJSONArray("P_PO_HDR_TAB_ITEM");

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

                String buyer = null;
                if (temp.getString("BUYER") != null)
                    buyer = temp.getString("BUYER");

                String status = null;
                if (temp.getString("STATUS") != null)
                    status = temp.getString("STATUS");

                String pickUpDate = null;
                if (temp.getString("PICKUP_DATE") != null)
                    pickUpDate = temp.getString("PICKUP_DATE");   
                
                String notAfterDate = null;
                if (temp.getString("NOT_AFTER_DATE") != null)
                    notAfterDate =temp.getString("NOT_AFTER_DATE");       
  
                String poTotal = null;
                if (temp.getString("PO_TOTAL") != null)
                    poTotal = temp.getString("PO_TOTAL");   
                
                String recordId = null;
                if (temp.getString("RECORD_ID") != null)
                    recordId = temp.getString("RECORD_ID");                   
                

             
                
                    POHeaders poHeader = new POHeaders(recordId, poNumber, poOrderType, poDate, buyer, status, pickUpDate, notAfterDate, poTotal);
                        //new POHeaders(organizationCode, category, item, quantity, valueInUsd, recordId);
                    poHeaderList.add(poHeader);
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        poHeadersArray = poHeaderList.toArray(new POHeaders[poHeaderList.size()]);
        return poHeadersArray;
    }   
    
    
}
