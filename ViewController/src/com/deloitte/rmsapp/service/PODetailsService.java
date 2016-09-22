package com.deloitte.rmsapp.service;

import com.deloitte.rmsapp.utility.ConnectionFactory;

 
import com.deloitte.rmsapp.Supplier.PODetails;
import com.deloitte.rmsapp.Supplier.POHeaders;
import com.deloitte.rmsapp.Supplier.POLine;
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

public class PODetailsService {
    public PODetailsService() {
        super();
    }
    private static List<PODetails> poDetailsList = new ArrayList<PODetails>();
    
    public PODetails[] getPODetailsData() {
        PODetails[] poDetailsArray = null;
        poDetailsList = new ArrayList<PODetails>();
        ServiceManager serviceManager = new ServiceManager();
        String inventoryOrg = null;
        Connection conn = null;
        Statement stmt = null;
     
        String strSelectedPONum= (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedPONum}");
        String jsonArrayAsString =serviceManager.invokeREAD(RestURIs.getPoLineURI(strSelectedPONum)
                                                            );
        String strDebug=":"+strSelectedPONum;
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.arrayVal}", strDebug+"::"+jsonArrayAsString);
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parent = jsonObject.getJSONObject("P_PO_DETAIL_TAB");
            JSONArray nodeArray = parent.getJSONArray("P_PO_DETAIL_TAB_ITEM");

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
                
                List<POLine> poLineList = new ArrayList<POLine>();
                JSONObject poLineParentObject = temp.getJSONObject("PO_LINE");
                JSONArray poLineArray = poLineParentObject.getJSONArray("PO_LINE_ITEM");
                for(int j=0;j<poLineArray.length();j++ )
                {
                    JSONObject lineTemp = nodeArray.getJSONObject(j);
                    
                    String lineNumber = null;
                    if(lineTemp.getString("LINE_NUMBER")!= null)
                    lineNumber = lineTemp.getString("LINE_NUMBER");
                    
                    String lineQuantity = null;
                    if(lineTemp.getString("QTY")!= null)
                    lineQuantity = lineTemp.getString("QTY");

                    String linePrice = null;
                    if(lineTemp.getString("PRICE")!= null)
                    linePrice = lineTemp.getString("PRICE");

                    String lineUOM = null;
                    if(lineTemp.getString("UOM")!= null)
                    lineUOM = lineTemp.getString("UOM");
                    
                    POLine poLine = new POLine(lineNumber, lineQuantity, lineUOM, linePrice);
                    poLineList.add(poLine);
                    
                }
             
                    PODetails poDetails = new PODetails(recordId, 
                                                       poNumber, 
                                                       poOrderType, 
                                                       poDate, 
                                                       buyer, 
                                                       status, 
                                                       pickUpDate, 
                                                       notAfterDate, 
                                                       poTotal,
                                                       poLineList.toArray(new POLine[poLineList.size()])
                                                       );
                        //new POHeaders(organizationCode, category, item, quantity, valueInUsd, recordId);
                    poDetailsList.add(poDetails);
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        poDetailsArray = poDetailsList.toArray(new PODetails[poDetailsList.size()]);
        return poDetailsArray;
    }   
    
    
}
