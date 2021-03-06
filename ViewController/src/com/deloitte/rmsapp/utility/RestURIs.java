package com.deloitte.rmsapp.utility;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class RestURIs {
    public RestURIs() {
        super();
    }

    private static String PO_SUMMARY = "/XxRpmGetPoSummary/GetPoSummary_RS";//http://app101111.glbsnet.com/XxRpmGetPoSummary/GetPoSummary_RS/-999/305
    private static String PO_HEADERS = "/XxRpmPoHeaderDetails/GetPOHeaderRS";
    private static String PO_DETAILS = "/XxRpmGetPODetailsPrj/GetPODetailsService";//http://app101111.glbsnet.com/XxRpmGetPODetailsPrj/GetPODetailsService/1703
    private static String NotifcationsList = "/XxRpmPONotification/PONotificationRS";
    private static String PO_BY_STATUS = "/XxRpmPosByStatus/PoByStatusPS";//http://app101111.glbsnet.com/XxRpmPosByStatus/PoByStatusPS/SUPPLIER/2500/NEW
    private static String PO_NUMBER_LOV = "/XxRpmGetPoLov/RestService";
    private static String SupplierLOV = "/XxRpmGetSuppLov/GetSuppLov_RS";
    private static String BuyerLOV = "/XxRpmGetBuyerLov/GetBuyerLov_RS";
    private static String LoadPO = "/XxRpmLoadPurchaseOrders/LoadPurchaseOrderRS";
    private static String Approvals = "/XxRpmModifyPoByBuyer/POModifyRS";
    private static String LoginURL = "/XxRpmValidateUser/ValidateUser_RS";
    private static String NotificationURL = "/NotificationAppModel/resources/notificationservice/sendnotification";
    
    public static String getPushNotificationURL(String userType,String from,String to,String appId,String message){
        return NotificationURL+"/"+userType+"/"+from+"/"+to+"/"+appId+"/"+convertString2URLFormat(message);
    }
    
    public static String getLoginURL(String userName,String password,String deviceId,
                                     String loginType,String appId){
        return LoginURL+"/"+userName+"/"+password+"/"+deviceId+"/"+loginType+"/"+appId;
    }
    public static String getPONumberLovURI(String loginType, String loginNumber) {
        return PO_NUMBER_LOV + "/" + loginType + "/" + loginNumber;
    }

    public static String getLoadPOURI() {
        return LoadPO;
    }

    public static String getApprovalURI() {
        return Approvals;
    }

    public static String getSupplierLovURI(String buyerNumber) {
        return SupplierLOV + "/" + buyerNumber;
    }

    public static String getBuyerLovURI(String supplierNumber) {
        return BuyerLOV + "/" + supplierNumber;
    }

    public static String getPoSummaryURI(String Supplier, String Buyer) {
        return PO_SUMMARY + "/" + Supplier + "/" + Buyer;
    }

    public static String getPObyStatus(String loginType, String loginNumber, String status) {
        return convertString2URLFormat(PO_BY_STATUS + "/" + loginType + "/" + loginNumber + "/" + status);
    }

    public static String getNotificationURI(String strType, String strNumber) {
        return NotifcationsList + "/" + strType + "/" + strNumber;
    }

    public static String getPOHeaderURI(String strOrderFromDate, String strOrderToDate, String strSupplier,
                                        String strBuyer, String strFromDate, String strToDate, String strStatus,
                                        String strType) {
        return convertString2URLFormat(PO_HEADERS + "/" + strOrderFromDate + "/" + strOrderToDate + "/" + strSupplier +
                                       "/" + strBuyer + "/" + strFromDate + "/" + strToDate + "/" + strStatus + "/" +
                                       strType);
    }

    public static String getPoLineURI(String strPONubmer) {
        return PO_DETAILS + "/" + strPONubmer;
    }


    public static String convertString2URLFormat(String strInput) {
        String strOutput = "";
        for (int i = 0; i < strInput.length(); i++) {
            char c = strInput.charAt(i);
            if (32 == (int) c) {
                strOutput = strOutput + "%20";
            } else {
                strOutput = strOutput + c;
            }
        }
        return strOutput;
    }


}
