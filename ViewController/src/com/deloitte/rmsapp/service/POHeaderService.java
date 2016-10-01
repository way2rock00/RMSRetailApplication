package com.deloitte.rmsapp.service;

import com.deloitte.rmsapp.utility.ConnectionFactory;


import com.deloitte.rmsapp.Supplier.POHeaders;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.deloitte.rmsapp.utility.RestURIs;
import com.deloitte.rmsapp.utility.ServiceManager;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
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


        String strOrderFrom = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchOrderFrom}");
        System.out.println("strOrderFrom" + strOrderFrom);
        String strOrderTo = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchOrderTo}");
        System.out.println("strOrderTo" + strOrderTo);
        String strSupplier = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchSupplier}");
        String strBuyer = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchBuyer}");

        String strFromDate = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchFromDate}");
        System.out.println("strOrderTo" + strFromDate);
        String strToDate = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchToDate}");
        System.out.println("strOrderTo" + strToDate);
        String strStatus = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchStatus}");
        String strType = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.searchItem}");

        strOrderFrom = strOrderFrom == null ? "-999" : strOrderFrom;
        strOrderFrom = strOrderFrom.length() == 0 ? "-999" : strOrderFrom;
        
        strOrderTo = strOrderTo == null ? "-999" : strOrderTo;
        strOrderTo = strOrderTo.length() == 0 ? "-999" : strOrderTo;
        
        strSupplier = strSupplier == null ? "-999" : strSupplier;
        strBuyer = strBuyer == null ? "-999" : strBuyer;
        strStatus = strStatus == null ? "-999" : strStatus;
        strType = strType == null ? "-999" : strType;

        System.out.println("strOrderFrom" + strOrderFrom);
        System.out.println("strOrderTo" + strOrderTo);
        System.out.println("strFromDate" + strFromDate);
        System.out.println("strToDate" + strToDate);

        if (strFromDate == null) {
            System.out.println("strFromDate Date : is null");

        } else {
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            SimpleDateFormat destinationFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = null;
            try {
                date = sourceFormat.parse(strFromDate);
                System.out.println(date);
                System.out.println(destinationFormat.format(date));

                strFromDate = destinationFormat.format(date);
                System.out.println("Date : " + strFromDate);
            } catch (Exception ex) {
                System.out.println("Error " + ex.getLocalizedMessage());
            }
        }

        if (strToDate == null) {
            System.out.println("strToDate Date : is null");

        } else {
            SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            SimpleDateFormat destinationFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = null;
            try {
                date = sourceFormat.parse(strToDate);
                System.out.println(date);
                System.out.println(destinationFormat.format(date));

                strToDate = destinationFormat.format(date);
                System.out.println("Date : " + strToDate);
            } catch (Exception ex) {
                System.out.println("Error " + ex.getLocalizedMessage());
            }
        }

        strFromDate = strFromDate == null ? "-999" : strFromDate;
        strToDate = strToDate == null ? "-999" : strToDate;


        String url =
            RestURIs.getPOHeaderURI(strOrderFrom, strOrderTo, strSupplier, strBuyer, strFromDate, strToDate, strStatus,
                                    strType);
        System.out.println("po header url:" + url);
        String jsonArrayAsString = serviceManager.invokeREAD(url);
        String strDebug = (String) AdfmfJavaUtilities.getELValue("#{pageFlowScope.arrayVal}");
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.arrayVal}", strDebug + "::" + "New");
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
                    notAfterDate = temp.getString("NOT_AFTER_DATE");

                String poTotal = null;
                if (temp.getString("PO_TOTAL") != null)
                    poTotal = temp.getString("PO_TOTAL");

                String recordId = null;
                if (temp.getString("RECORD_ID") != null)
                    recordId = temp.getString("RECORD_ID");


                POHeaders poHeader =
                    new POHeaders(recordId, poNumber, poOrderType, poDate, buyer, status, pickUpDate, notAfterDate,
                                  poTotal);
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
