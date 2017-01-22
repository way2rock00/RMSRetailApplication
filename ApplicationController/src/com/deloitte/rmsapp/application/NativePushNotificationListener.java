package com.deloitte.rmsapp.application;

import java.util.HashMap;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.event.Event;
import oracle.adfmf.framework.event.EventListener;
import oracle.adfmf.framework.exception.AdfException;

public class NativePushNotificationListener implements EventListener {
    public NativePushNotificationListener() {
    }

    public void onMessage(Event event) {
        String msg = event.getPayload();
        System.out.println("#### Message from the Server :" + msg);
        
        // Parse the payload of the push notification
        HashMap payload = null;
        String pushMsg = "No message received";
        try
        {
          payload = (HashMap)JSONBeanSerializationHelper.fromJSON(HashMap.class, msg);
          pushMsg = (String)payload.get("alert");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        // Write the push message to app scope to display to the user
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.pushMessage}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), pushMsg);
        AdfmfJavaUtilities.flushDataChangeEvent();
    }

    public void onError(AdfException adfException) {
        System.out.println("#### Error: " + adfException.toString());
        // Write the error into app scope
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.errorMessage}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), adfException.toString());

    }

    public void onOpen(String token) {
        System.out.println("#### Registration token:" + token);
        String strDebug="";
        // Clear error in app scope
        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.errorMessage}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), null);
        strDebug = strDebug + "~1~";
            
        // Write the token into app scope
        ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.deviceToken}", String.class);
        ve.setValue(AdfmfJavaUtilities.getAdfELContext(), token);
        strDebug = strDebug + "~2~";
        
        String strTokenDebug = "";
        strDebug = strDebug + "~3~"+token;
        if(token != null) {
            strDebug = strDebug + "~Not null~";
            char[] array = token.toCharArray();
            strTokenDebug  = strTokenDebug + ":"+array.length+"\n";
            for(int i =0 ; i< array.length ; i++)
            {
                int j = array[i];
                strTokenDebug  = strTokenDebug +":"+array[i]+"::"+j+"\n";
            }
            
        }
        strDebug = strDebug + "~End~";
        AdfmfJavaUtilities.setELValue("#{applicationScope.strDebug}", strDebug);
        AdfmfJavaUtilities.setELValue("#{applicationScope.strTokenDebug}", strTokenDebug);
    }
    
    
}
