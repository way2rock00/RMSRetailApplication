package com.deloitte.rmsapp.service;

import com.deloitte.rmsapp.Supplier.POLine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import java.util.Set;

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
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;
import oracle.adfmf.util.GenericType;

public class PurchaseOrderBean {
    public PurchaseOrderBean() {
    }

    public void clicked(ActionEvent actionEvent) {

        AmxIteratorBinding ib = null;
        String strDebug = "S";
        int alertCount = 0;
        /*
        try
        {
            JSONObject jsonObject = new JSONObject();
            POLine p1 = new POLine("1", "L1", "10", "EACH", "100", "TestLineReason1");
            POLine p2 = new POLine("2", "L2", "20", "EACH", "200", "TestLineReason2");
            List liTest = new ArrayList();
            liTest.add(p1);
            liTest.add(p2);
            JSONArray jsonArray = new JSONArray(liTest);
        jsonObject.put("Test1", "Hello1");
        jsonObject.put("Test2", "Hello2");
            jsonObject.put("TestList",jsonArray);
            strDebug= strDebug+jsonObject.toString();
        }
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.debugVal}", strDebug);
        catch(Exception e)
        {
            strDebug= strDebug+"Error:"+e.getMessage();
        }*/
        try {
            JSONObject poHeaderParentObject = new JSONObject();

            JSONObject poObject = new JSONObject();
            strDebug = strDebug + ":1:";
            ib = (AmxIteratorBinding) AdfmfJavaUtilities.getELValue("#{bindings.PODetailsDataIterator}");
            ib.getIterator().first();
            for (int i = 0; i < ib.getIterator().getTotalRowCount(); i++) {
                GenericType row = (GenericType) ib.getIterator().getCurrentRow();
                System.out.println(row.getAttribute("poDate").toString());
                strDebug = strDebug + ":2:";
                poObject.put("PO_HDR_ID",
                             row.getAttribute("recordId") == null ? "" : row.getAttribute("recordId").toString());
                poObject.put("ORDER_NO",
                             row.getAttribute("poNumber") == null ? "" : row.getAttribute("poNumber").toString());
                poObject.put("VERSION_NO", ""); //This needs to be included
                poObject.put("HEADER_REASON",
                             row.getAttribute("headerReason") == null ? "" :
                             row.getAttribute("headerReason").toString());
                poObject.put("PICKUP_DATE",
                             row.getAttribute("pickUpDate") == null ? "" : row.getAttribute("pickUpDate").toString());
                poObject.put("STATUS", row.getAttribute("status") == null ? "" : row.getAttribute("status").toString());
                poObject.put("CREATED_BY", "");
                poObject.put("CREATION_DATE", "");
                poObject.put("LAST_UPDATED_BY", "");
                poObject.put("LAST_UPDATE_DATE", "");
                poObject.put("LAST_UPDATE_LOGIN", "");
            }

            ib = (AmxIteratorBinding) AdfmfJavaUtilities.getELValue("#{bindings.poLinesIterator}");
            //ib.getIterator().first();
            List poLineList = new ArrayList();
            for (int i = 0; i < ib.getIterator().getTotalRowCount(); i++) {
                ib.getIterator().next();
                JSONObject poLineObject = new JSONObject();
                GenericType row = (GenericType) ib.getIterator().getCurrentRow();
                System.out.println(row.getAttribute("item").toString());
                System.out.println(row.getAttribute("quantity").toString());
                System.out.println(row.getAttribute("price").toString());
                strDebug = strDebug + ":3:";
                poLineObject.put("PO_LINE_ID",
                                 row.getAttribute("poLineNumber") == null ? "" :
                                 row.getAttribute("poLineNumber").toString());
                poLineObject.put("ORDER_NO",
                                 row.getAttribute("poLineNumber") == null ? "" :
                                 row.getAttribute("poLineNumber").toString());
                poLineObject.put("ITEM", row.getAttribute("item") == null ? "" : row.getAttribute("item").toString());
                poLineObject.put("QUANTITY",
                                 row.getAttribute("quantity") == null ? "" : row.getAttribute("quantity").toString());
                poLineObject.put("PRICE",
                                 row.getAttribute("price") == null ? "" : row.getAttribute("price").toString());
                poLineObject.put("UOM", row.getAttribute("uom") == null ? "" : row.getAttribute("uom").toString());
                poLineObject.put("LOCATION",
                                 row.getAttribute("locationName") == null ? "" :
                                 row.getAttribute("locationName").toString());
                poLineObject.put("LINE_REASON",
                                 row.getAttribute("lineReason") == null ? "" :
                                 row.getAttribute("lineReason").toString());
                poLineObject.put("CREATED_BY", "");
                poLineObject.put("CREATION_DATE", "");
                poLineObject.put("LAST_UPDATED_BY", "");
                poLineObject.put("LAST_UPDATE_DATE", "");
                poLineObject.put("LAST_UPDATE_LOGIN", "");
                poLineList.add(poLineObject);
            }

            poObject.put("P_LINE_TBL_OBJ", poLineList);
            poHeaderParentObject.put("P_PO_HDR_DATA_TAB", poObject);
            strDebug = strDebug + ":4:";
            strDebug = strDebug + poHeaderParentObject.toString();
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.debugVal}", strDebug);
        } catch (Exception e) {
            strDebug = strDebug + ":5:";
            AdfmfJavaUtilities.setELValue("#{pageFlowScope.debugVal}", strDebug);
        }


    }


    //        String strDebug="S";
    ////        try{
    ////            AmxBindingContainer amxBindingContainer = AdfmfJavaUtilities.getBindingContainer();
    ////            if(amxBindingContainer!=null)
    ////            {
    ////                strDebug=strDebug+":1";
    ////                try{
    ////                Map bindings = amxBindingContainer.getControlBindings();
    ////                    strDebug=strDebug+":SizeBin:"+bindings.size();
    ////                    if(bindings.size()>0 ){
    ////                    Set keys = bindings.keySet();
    ////                    Iterator itr = keys.iterator();
    ////
    ////                    String key;
    ////                    //                String value;
    ////                    while(itr.hasNext())
    ////                    {
    ////                        key = (String)itr.next();
    ////                        Object value = bindings.get(key);
    ////                        strDebug =strDebug+":("+key + " - "+ value+")"+"::"+value.getClass();
    ////                    }
    ////                    }
    ////                }
    ////                catch(Exception e1){
    ////                    strDebug=strDebug+"Except:"+e1.getMessage();
    ////                }
    ////                Map definitions = amxBindingContainer.getTreeDefinitions("PODetailsData");
    ////                strDebug=strDebug+":Size:"+definitions.size();
    ////                if(definitions.size()>0 ){
    ////                Set keys = definitions.keySet();
    ////                Iterator itr = keys.iterator();
    ////
    ////                String key;
    //////                String value;
    ////                while(itr.hasNext())
    ////                {
    ////                    key = (String)itr.next();
    ////                    if("poLines".equalsIgnoreCase(key))
    ////                    {
    ////                    try{
    ////                        strDebug=strDebug+"TreBind";
    ////                      AmxTreeBinding a = (AmxTreeBinding)definitions.get("poLines");
    ////                        strDebug =  strDebug + "::" + a.getRangeSize() + "::" + a.getLabel() + "::" + a.getName();
    ////                        }
    ////                    catch(Exception e2){
    ////                        strDebug=strDebug+"ETre:"+e2.getMessage();
    ////                    }
    ////                    }
    ////                    if("PODetailsData".equalsIgnoreCase(key))
    ////                    {
    ////                    try{
    ////                        strDebug=strDebug+"LineBind";
    ////                      AmxIteratorBinding a = (AmxIteratorBinding)definitions.get("PODetailsData");
    ////                       strDebug = strDebug + "::" + a.getRangeSize() + "::"+a.getName();
    ////                        }
    ////                    catch(Exception e2){
    ////                        strDebug=strDebug+"ETre:"+e2.getMessage();
    ////                    }
    ////                    }
    ////                    Object value = definitions.get(key);
    ////                    strDebug =strDebug+":("+key + " - "+ value+")"+"::"+value.getClass();
    ////                }
    ////            }
    ////        }
    ////        }
    ////            catch(Exception e){
    ////                strDebug=strDebug+":Error:"+e.getMessage();
    ////            }
    //        try{
    //            Object o = AdfmfJavaUtilities.getELValue("#{bindings.PODetailsData.collectionModel}");
    //            strDebug =strDebug+"::::"+o+":::::"+o.getClass();
    //            o = AdfmfJavaUtilities.getELValue("#{bindings.PODetailsData.iteratorBinding}");
    //            strDebug =strDebug+"::::"+o+":::::"+o.getClass();
    //            AmxCollectionModel a = (AmxCollectionModel)AdfmfJavaUtilities.getELValue("#{bindings.PODetailsData.collectionModel}");
    //            if(a!=null)
    //            {
    ////            strDebug=strDebug+":col:"+a.toJSON()+":"+a.getRangeSize()+":"+a.getTotalSize();
    //                Map binding = a.getTreeBinding();
    //                AmxTreeNodeDefinitionAccessor accessor = a.getColumnAttributes();
    //                if(accessor!=null)
    //                    strDebug=strDebug+":ACCESSOR:"+accessor.toJSON();
    //                AmxTreeNodeDefinitionAccessor amxTreeNodeDefinitionAccessor = a.getColumnAttributes();
    //                if(amxTreeNodeDefinitionAccessor!=null)
    //                    strDebug=strDebug+":AMXACCESSOR:"+amxTreeNodeDefinitionAccessor.toJSON();
    //                Object[] keys1 = a.getKeys();
    //                for(int i=0;i<keys1.length;i++)
    //                {
    //                    strDebug=strDebug+":KEY:"+keys1[i];
    //                }
    ////                strDebug=strDebug+":TreeSize:"+binding.size();
    //                if(binding!=null)
    //                {
    //                if(binding.size()>0 ){
    //                Set keys = binding.keySet();
    //                Iterator itr = keys.iterator();
    //
    //                String key;
    //                //                String value;
    //                while(itr.hasNext())
    //                {
    //                    key = (String)itr.next();
    //                    Object value = binding.get(key);
    //                    strDebug =strDebug+":("+key + " - "+ value+")"+"::"+value.getClass();
    //                }
    //                }
    //                }
    //
    //                Object bindings = a.getTreeNodeBindings();
    //                if(bindings!=null)
    //                strDebug =strDebug+":bindings:"+bindings.getClass();
    //            }
    //            AmxAccessorIteratorBinding it = (AmxAccessorIteratorBinding)AdfmfJavaUtilities.getELValue("#{bindings.PODetailsData.iteratorBinding}");
    //            if(it!=null){
    //            BasicIterator basicIterator = it.getIterator();
    //            if(basicIterator!=null){
    //                strDebug=strDebug+":iter:"+basicIterator.getTotalRowCount();
    //                        Object currentRow = basicIterator.getCurrentRow();
    //                        strDebug=strDebug+":rowCur:"+currentRow.getClass();
    //                    GenericType genType = (GenericType)basicIterator.getCurrentRow();
    //                    if(genType!=null)
    //                    {
    //                        strDebug=strDebug+":GenType:"+genType.getName();
    //                    }
    //                }
    //
    //            }
    //
    //        }
    //        catch(Exception e) {
    //            strDebug=strDebug+":MaiExp:" +e.getMessage();
    //        }
    //        //AdfmfJavaUtilities.setELValue("#{pageFlowScope.debugVal}", strDebug);
    //
    //        DataControl byId = AdfmfJavaUtilities.getDataControlById("PODetailsService");
    //        //        // Add event code here...
    ////        List pnames1 = new ArrayList();
    ////        List params1 = new ArrayList();
    ////        List ptypes1 = new ArrayList();
    ////
    ////        try {
    ////            AdfmfJavaUtilities.invokeDataControlMethod("Test", null, "getListOfData", pnames1, params1, ptypes1);
    ////        } catch (AdfInvocationException e) {
    ////        }


}
