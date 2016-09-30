package com.deloitte.rmsapp.utility;

public class RestURIs {
    public RestURIs() {
        super();
    }

    private static String PO_SUMMARY = "/XxRpmGetPoSummary/GetPoSummary_RS";
    private static String PO_HEADERS = "/XxRpmPoHeaderDetails/GetPOHeaderRS";
    private static String PO_DETAILS = "/XxRpmGetPODetailsPrj/GetPODetailsService";
    private static String NotifcationsList = "/XxRpmPONotification/PONotificationRS";

    public static String getPoSummaryURI(String Supplier,String Buyer) {
        return PO_SUMMARY+"/"+Supplier+"/"+Buyer;
    }

    public static String getNotificationURI(String strType, String strNumber) {
        return NotifcationsList + "/" + strType + "/" + strNumber;
    }

    public static String getPOHeaderURI(String strOrderFromDate, String strOrderToDate, String strSupplier,
                                        String strBuyer, String strFromDate, String strToDate, String strStatus,
                                        String strType) {
        return PO_HEADERS + "/" + strOrderFromDate + "/" + strOrderToDate + "/" + strSupplier + "/" + strBuyer + "/" +
               strFromDate + "/" + strToDate + "/" + strStatus + "/" + strType;
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
