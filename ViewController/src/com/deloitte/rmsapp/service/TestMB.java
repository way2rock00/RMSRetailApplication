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
import oracle.adfmf.bindings.iterator.BasicIterator;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfInvocationException;

public class TestMB {
    public TestMB() {
    }

    public void clicked(ActionEvent actionEvent) {
        String strDebug="S";
        try{
            AmxBindingContainer amxBindingContainer = AdfmfJavaUtilities.getBindingContainer();
            if(amxBindingContainer!=null)
            {
                strDebug=strDebug+":1";
                try{
                Map bindings = amxBindingContainer.getControlBindings();
                    strDebug=strDebug+":SizeBin:"+bindings.size();
                    if(bindings.size()>0 ){
                    Set keys = bindings.keySet();
                    Iterator itr = keys.iterator();
                    
                    String key;
                    //                String value;
                    while(itr.hasNext())
                    {
                        key = (String)itr.next();
                        Object value = bindings.get(key);
                        strDebug =strDebug+":("+key + " - "+ value+")"+"::"+value.getClass();
                    }
                    }
                }
                catch(Exception e1){
                    strDebug=strDebug+"Except:"+e1.getMessage();
                }
                Map definitions = amxBindingContainer.getTreeDefinitions("PODetailsData");
                strDebug=strDebug+":Size:"+definitions.size();
                if(definitions.size()>0 ){
                Set keys = definitions.keySet();
                Iterator itr = keys.iterator();
                
                String key;
//                String value;
                while(itr.hasNext())
                {
                    key = (String)itr.next();
                    if("poLines".equalsIgnoreCase(key))
                    {
                    try{
                        strDebug=strDebug+"TreBind";
                      AmxTreeBinding a = (AmxTreeBinding)definitions.get("poLines");
                        strDebug =  strDebug + "::" + a.getRangeSize() + "::" + a.getLabel() + "::" + a.getName();
                        }
                    catch(Exception e2){
                        strDebug=strDebug+"ETre:"+e2.getMessage(); 
                    }
                    }
                    if("PODetailsData".equalsIgnoreCase(key))
                    {
                    try{
                        strDebug=strDebug+"LineBind";
                      AmxIteratorBinding a = (AmxIteratorBinding)definitions.get("PODetailsData");
                       strDebug = strDebug + "::" + a.getRangeSize() + "::"+a.getName();
                        }
                    catch(Exception e2){
                        strDebug=strDebug+"ETre:"+e2.getMessage(); 
                    }
                    }
                    Object value = definitions.get(key);
                    strDebug =strDebug+":("+key + " - "+ value+")"+"::"+value.getClass();
                }
            }
        }
        }
            catch(Exception e){
                strDebug=strDebug+":Error:"+e.getMessage();
            }
        try{
            Object o = AdfmfJavaUtilities.getELValue("#{bindings.PODetailsData.collectionModel}");
            strDebug =strDebug+"::::"+o+":::::"+o.getClass();
            o = AdfmfJavaUtilities.getELValue("#{bindings.PODetailsData.iteratorBinding}");
            strDebug =strDebug+"::::"+o+":::::"+o.getClass();
            AmxCollectionModel a = (AmxCollectionModel)AdfmfJavaUtilities.getELValue("#{bindings.PODetailsData.collectionModel}");
            if(a!=null)
            strDebug=strDebug+":col:"+a.toJSON()+":"+a.getRangeSize()+":"+a.getTotalSize();
            AmxAccessorIteratorBinding it = (AmxAccessorIteratorBinding)AdfmfJavaUtilities.getELValue("#{bindings.PODetailsData.iteratorBinding}");
            if(it!=null){
            BasicIterator basicIterator = it.getIterator();
            if(basicIterator!=null){
                strDebug=strDebug+":iter:"+basicIterator.getTotalRowCount();
                    Object currentRow = basicIterator.getCurrentRow();
                    strDebug=strDebug+":rowCur:"+currentRow.getClass();
                }
            
            }
            
        }
        catch(Exception e) {
            
        }
        AdfmfJavaUtilities.setELValue("#{pageFlowScope.debugVal}", strDebug);
        
        DataControl byId = AdfmfJavaUtilities.getDataControlById("PODetailsService");
        //        // Add event code here...
//        List pnames1 = new ArrayList();
//        List params1 = new ArrayList();
//        List ptypes1 = new ArrayList();
//
//        try {
//            AdfmfJavaUtilities.invokeDataControlMethod("Test", null, "getListOfData", pnames1, params1, ptypes1);
//        } catch (AdfInvocationException e) {
//        }
    }
}
