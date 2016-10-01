package com.deloitte.rmsapp.service;

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
import oracle.adfmf.util.GenericType;

public class TestMB {
    public TestMB() {
    }

    public void clicked(ActionEvent actionEvent) {

        AmxIteratorBinding ib = null;
        int alertCount = 0;

        ib = (AmxIteratorBinding) AdfmfJavaUtilities.getELValue("#{bindings.PODetailsDataIterator}");
        ib.getIterator().first();
        for (int i = 0; i < ib.getIterator().getTotalRowCount(); i++) {
            GenericType row = (GenericType) ib.getIterator().getCurrentRow();
            System.out.println(row.getAttribute("poDate").toString());
        }

        ib = (AmxIteratorBinding) AdfmfJavaUtilities.getELValue("#{bindings.poLinesIterator}");
        //ib.getIterator().first();
        for (int i = 0; i < ib.getIterator().getTotalRowCount(); i++) {
            ib.getIterator().next();
            GenericType row = (GenericType) ib.getIterator().getCurrentRow();
            System.out.println(row.getAttribute("item").toString());
            System.out.println(row.getAttribute("quantity").toString());
            System.out.println(row.getAttribute("price").toString());
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
