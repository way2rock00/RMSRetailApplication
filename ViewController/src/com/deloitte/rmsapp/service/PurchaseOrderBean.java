package com.deloitte.rmsapp.service;

import com.deloitte.rmsapp.Supplier.POLine;

import com.deloitte.rmsapp.utility.RestURIs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import java.util.Set;
import java.util.Date;

import com.deloitte.rmsapp.utility.RestURIs;
import com.deloitte.rmsapp.utility.ServiceManager;

import java.text.SimpleDateFormat;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.bindings.DataControl;
import oracle.adfmf.bindings.OperationBinding;
import oracle.adfmf.bindings.dbf.AmxAccessorIteratorBinding;
import oracle.adfmf.bindings.dbf.AmxTreeBinding;
import oracle.adfmf.bindings.dbf.AmxIteratorBinding;
import oracle.adfmf.bindings.dbf.AmxBindingContainer;
import oracle.adfmf.bindings.dbf.AmxBindingContext;
import oracle.adfmf.bindings.dbf.AmxCollectionModel;
import oracle.adfmf.bindings.dbf.AmxTreeBinding.AmxTreeNodeDefinitionAccessor;
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;
import oracle.adfmf.util.GenericType;

public class PurchaseOrderBean {
    public PurchaseOrderBean() {
    }

    public void approveClicked(ActionEvent actionEvent) {
        System.out.println("approveClicked");
        buyerActions("APPROVED");
    }

    public void rejectClicked(ActionEvent actionEvent) {
        System.out.println("rejectClicked");
        buyerActions("REJECTED");
    }

    public void buyerActions(String action) {
        String service_msg = null;
        System.out.println("buyerActions start");
        AmxIteratorBinding headerIterator = null;
        AmxIteratorBinding lineIterator = null;
        String strDebug = "S";
        int alertCount = 0;
        JSONObject poHeaderParentObject = new JSONObject();
        JSONObject poObject = new JSONObject();
        try {

            Date sysDate = new Date();
            String order_no = "";
            System.out.println("sysDate" + sysDate);
            headerIterator = (AmxIteratorBinding) AdfmfJavaUtilities.getELValue("#{bindings.PODetailsDataIterator}");
            headerIterator.getIterator().first();
            List poHeaderList = new ArrayList();

            for (int header = 0; header < headerIterator.getIterator().getTotalRowCount(); header++) {
                GenericType headerRow = (GenericType) headerIterator.getIterator().getCurrentRow();
                System.out.println(headerRow.getAttribute("poDate").toString());

                JSONObject poLineObjects = new JSONObject();

                order_no =
                    headerRow.getAttribute("poNumber") == null ? "" : headerRow.getAttribute("poNumber").toString();
                poObject.put("P_ORDER_NO_IN",
                             headerRow.getAttribute("poNumber") == null ? "" :
                             headerRow.getAttribute("poNumber").toString());

                poObject.put("P_REASON_IN",
                             headerRow.getAttribute("headerReason") == null ? "" :
                             headerRow.getAttribute("headerReason").toString());

                String pickupDate = (String) headerRow.getAttribute("pickUpDate");
                if (pickupDate == null) {
                    pickupDate = "";
                } else {
                    SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                    SimpleDateFormat destinationFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date date = null;
                    try {
                        date = sourceFormat.parse(pickupDate);
                        System.out.println(date);
                        System.out.println(destinationFormat.format(date));

                        pickupDate = destinationFormat.format(date);
                        System.out.println("Date : " + pickupDate);
                    } catch (Exception ex) {
                        System.out.println("Error " + ex.getLocalizedMessage());
                    }
                }

                poObject.put("P_PICKUP_DATE_IN", pickupDate);

                poObject.put("P_REQUEST_STATUS", action);
                System.out.println("poObject" + poObject.toString());

                lineIterator = (AmxIteratorBinding) AdfmfJavaUtilities.getELValue("#{bindings.poLinesIterator}");
                lineIterator.getIterator().first();
                List poLineList = new ArrayList();
                for (int child = 0; child < lineIterator.getIterator().getTotalRowCount(); child++) {

                    System.out.println("line loop");

                    JSONObject poLineObject = new JSONObject();
                    GenericType childRow = (GenericType) lineIterator.getIterator().getCurrentRow();


                    poLineObject.put("ORDER_NO", order_no);
                    String itemDesc =
                        childRow.getAttribute("item") == null ? "" : childRow.getAttribute("item").toString();
                    System.out.println("itemDesc" + itemDesc);
                    int indexPos = itemDesc.indexOf(" / ");
                    String itemNum = itemDesc.substring(0, indexPos);
                    poLineObject.put("ITEM", itemNum);
                    poLineObject.put("QTY",
                                     childRow.getAttribute("quantity") == null ? "" :
                                     childRow.getAttribute("quantity").toString());

                    poLineObject.put("LOCATION",
                                     childRow.getAttribute("locationNumber") == null ? "" :
                                     childRow.getAttribute("locationNumber").toString());

                    poLineObject.put("PRICE",
                                     childRow.getAttribute("price") == null ? "" :
                                     childRow.getAttribute("price").toString());

                    poLineObject.put("REASON",
                                     childRow.getAttribute("lineReason") == null ? "" :
                                     childRow.getAttribute("lineReason").toString());

                    poLineList.add(poLineObject);
                    lineIterator.getIterator().next();
                }

                poLineObjects.put("P_ORDLOC_IN_ITEM", poLineList);

                poObject.put("P_ORDLOC_IN", poLineObjects);

                //poHeaderList.add(poObject);
            }

            //JSONObject poHeaderParentObjects = new JSONObject();
            //poHeaderParentObjects.put("P_PO_HDR_DATA_TAB_ITEM", poHeaderList);
            //poHeaderParentObject.put("P_PO_HDR_DATA_TAB", poHeaderParentObjects);
            System.out.println(poObject.toString());
        } catch (Exception e) {
            System.out.println("exception " + e.getLocalizedMessage());
        }

        String url = RestURIs.getApprovalURI();
        System.out.println("loader url:" + url);
        ServiceManager serviceManager = new ServiceManager();
        String jsonArrayAsString = serviceManager.invokeUPDATE(url, poObject.toString());

        System.out.println("jsonArrayAsString " + jsonArrayAsString);
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            String returnStatus = jsonObject.getString("X_STATUS_OUT");            
            if (!returnStatus.equals("SUCCESS"))
                service_msg = "Error while processing the request. Please try again later.";
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.message}", service_msg);
        } catch (Exception ex) {
            System.out.println("exception:" + ex.getLocalizedMessage());
        }
        System.out.println("buyerActions end");
    }

    public void submitClicked(ActionEvent actionEvent) {
        System.out.println("submitClicked start");
        AmxIteratorBinding headerIterator = null;
        AmxIteratorBinding lineIterator = null;
        String strDebug = "S";
        int alertCount = 0;
        String service_msg = null;
        JSONObject poHeaderParentObject = new JSONObject();

        try {

            Date sysDate = new Date();
            String order_no = "";
            System.out.println("sysDate" + sysDate);
            headerIterator = (AmxIteratorBinding) AdfmfJavaUtilities.getELValue("#{bindings.PODetailsDataIterator}");
            headerIterator.getIterator().first();
            List poHeaderList = new ArrayList();
            JSONObject poObject = new JSONObject();
            for (int header = 0; header < headerIterator.getIterator().getTotalRowCount(); header++) {
                GenericType headerRow = (GenericType) headerIterator.getIterator().getCurrentRow();
                System.out.println(headerRow.getAttribute("poDate").toString());

                JSONObject poLineObjects = new JSONObject();

                order_no =
                    headerRow.getAttribute("poNumber") == null ? "" : headerRow.getAttribute("poNumber").toString();
                poObject.put("ORDER_NO",
                             headerRow.getAttribute("poNumber") == null ? "" :
                             headerRow.getAttribute("poNumber").toString());

                poObject.put("HEADER_REASON",
                             headerRow.getAttribute("headerReason") == null ? "" :
                             headerRow.getAttribute("headerReason").toString());

                String pickupDate = (String) headerRow.getAttribute("pickUpDate");
                if (pickupDate == null) {
                    pickupDate = "";
                } else {
                    SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                    SimpleDateFormat destinationFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    Date date = null;
                    try {
                        date = sourceFormat.parse(pickupDate);
                        System.out.println(date);
                        System.out.println(destinationFormat.format(date));

                        pickupDate = destinationFormat.format(date);
                        System.out.println("Date : " + pickupDate);
                    } catch (Exception ex) {
                        System.out.println("Error " + ex.getLocalizedMessage());
                    }
                }

                poObject.put("PICKUP_DATE", pickupDate);

                poObject.put("STATUS",
                             headerRow.getAttribute("status") == null ? "" :
                             headerRow.getAttribute("status").toString());
                poObject.put("CREATED_BY", "-1");
                // poObject.put("CREATION_DATE", sysDate.toString());
                poObject.put("LAST_UPDATED_BY", "-1");
                //poObject.put("LAST_UPDATE_DATE", sysDate.toString());
                poObject.put("LAST_UPDATE_LOGIN", "-1");

                System.out.println("poObject" + poObject.toString());

                lineIterator = (AmxIteratorBinding) AdfmfJavaUtilities.getELValue("#{bindings.poLinesIterator}");
                lineIterator.getIterator().first();
                List poLineList = new ArrayList();
                for (int child = 0; child < lineIterator.getIterator().getTotalRowCount(); child++) {

                    System.out.println("line loop");

                    JSONObject poLineObject = new JSONObject();
                    GenericType childRow = (GenericType) lineIterator.getIterator().getCurrentRow();


                    poLineObject.put("ORDER_NO", order_no);
                    String itemDesc =
                        childRow.getAttribute("item") == null ? "" : childRow.getAttribute("item").toString();
                    System.out.println("itemDesc" + itemDesc);
                    int indexPos = itemDesc.indexOf(" / ");
                    String itemNum = itemDesc.substring(0, indexPos);
                    poLineObject.put("ITEM", itemNum);
                    poLineObject.put("QUANTITY",
                                     childRow.getAttribute("quantity") == null ? "" :
                                     childRow.getAttribute("quantity").toString());
                    poLineObject.put("PRICE",
                                     childRow.getAttribute("price") == null ? "" :
                                     childRow.getAttribute("price").toString());
                    poLineObject.put("UOM",
                                     childRow.getAttribute("uom") == null ? "" :
                                     childRow.getAttribute("uom").toString());
                    /*poLineObject.put("LOCATION",
                                     childRow.getAttribute("locationName") == null ? "" :
                                     childRow.getAttribute("locationName").toString());
                    */poLineObject.put("LINE_REASON",
                                       childRow.getAttribute("lineReason") == null ? "" :
                                       childRow.getAttribute("lineReason").toString());
                    poLineObject.put("CREATED_BY", "-1");
                    //  poLineObject.put("CREATION_DATE", sysDate.toString());
                    poLineObject.put("LAST_UPDATED_BY", "-1");
                    //poLineObject.put("LAST_UPDATE_DATE", sysDate.toString());
                    poLineObject.put("LAST_UPDATE_LOGIN", "-1");
                    System.out.println("poLineObject:" + poLineObject.toString());
                    poLineList.add(poLineObject);
                    lineIterator.getIterator().next();
                }

                poLineObjects.put("P_LINE_TBL_OBJ_ITEM", poLineList);

                poObject.put("P_LINE_TBL_OBJ", poLineObjects);

                poHeaderList.add(poObject);
            }

            JSONObject poHeaderParentObjects = new JSONObject();
            poHeaderParentObjects.put("P_PO_HDR_DATA_TAB_ITEM", poHeaderList);
            poHeaderParentObject.put("P_PO_HDR_DATA_TAB", poHeaderParentObjects);
            System.out.println(poHeaderParentObject.toString());
        } catch (Exception e) {
            System.out.println("exception " + e.getLocalizedMessage());
        }

        String url = RestURIs.getLoadPOURI();
        System.out.println("loader url:" + url);
        ServiceManager serviceManager = new ServiceManager();
        String jsonArrayAsString = serviceManager.invokeUPDATE(url, poHeaderParentObject.toString());

        System.out.println("jsonArrayAsString " + jsonArrayAsString);
        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            String returnStatus = jsonObject.getString("X_RETURN_STATUS");
            if (!returnStatus.equals("S"))
                service_msg = "Error while processing the request. Please try again later.";

            AdfmfJavaUtilities.setELValue("#{pageFlowScope.message}", service_msg);
        } catch (Exception ex) {
            System.out.println("exception:" + ex.getLocalizedMessage());
        }
        System.out.println("submitClicked end");
    }
}
