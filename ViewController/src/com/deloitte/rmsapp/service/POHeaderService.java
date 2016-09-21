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
    private static List<POHeaders> invByCatData = new ArrayList<POHeaders>();
    
    public POHeaders[] getPOHeaderData() {
        POHeaders[] invByCatArray = null;
        invByCatData = new ArrayList<POHeaders>();
        ServiceManager serviceManager = new ServiceManager();
        String inventoryOrg = null;
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionFactory.getConnection();
            stmt = conn.createStatement();
            ResultSet result =
                stmt.executeQuery("SELECT DASHBOARD_TYPE,PARAM_NAME,PARAM_VALUE FROM DASHBOARD_PARAMS WHERE DASHBOARD_TYPE='INVENTORY'");

            while (result.next()) {
                String paramName = result.getString("PARAM_NAME");
                String paramValue = result.getString("PARAM_VALUE");
                if ("INVENTORY_ORG".equals(paramName)) {
                    inventoryOrg = paramValue;     
                }
            }
        } catch (Exception e) {
        }      
        String strInvCat= (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.selectedInvCategory}");
        String jsonArrayAsString =serviceManager.invokeREAD(RestURIs.getInvItemURI(inventoryOrg, strInvCat));

        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parent = jsonObject.getJSONObject("X_XXBIO_ITEM_DRILLDOWN_TAB");
            JSONArray nodeArray = parent.getJSONArray("X_XXBIO_ITEM_DRILLDOWN_TAB_ITEM");

            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String organizationCode = null;
                if (temp.getString("ORGANIZATION_CODE") != null)
                    organizationCode = temp.getString("ORGANIZATION_CODE");

                String category = null;
                if (temp.getString("CATEGORY") != null)
                    category = temp.getString("CATEGORY");

                String item = null;
                if (temp.getString("ITEM") != null)
                    item = temp.getString("ITEM");

                BigDecimal quantity = null;
                if (temp.getString("QUANTITY") != null)
                    quantity = new BigDecimal(temp.getString("QUANTITY"));

                BigDecimal valueInUsd = null;
                if (temp.getString("VALUE_IN_USD") != null)
                    valueInUsd = new BigDecimal(temp.getString("VALUE_IN_USD"));

                BigDecimal recordId = null;
                if (temp.getString("RECORD_ID") != null)
                    recordId = new BigDecimal(temp.getString("RECORD_ID"));            

             
                
                    POHeaders invByCat =
                        new POHeaders(organizationCode, category, item, quantity, valueInUsd, recordId);
                    invByCatData.add(invByCat);
                
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        invByCatArray = invByCatData.toArray(new POHeaders[invByCatData.size()]);
        return invByCatArray;
    }   
    
    
}
