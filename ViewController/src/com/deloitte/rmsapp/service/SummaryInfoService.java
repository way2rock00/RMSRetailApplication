package com.deloitte.rmsapp.service;


import com.deloitte.rmsapp.Supplier.POHeaders;
import com.deloitte.rmsapp.Supplier.SummaryInfo;
import com.deloitte.rmsapp.utility.RestURIs;
import com.deloitte.rmsapp.utility.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONObject;

public class SummaryInfoService {
    public SummaryInfoService() {
        super();
    }

    private static List<SummaryInfo> summaryList = new ArrayList<SummaryInfo>();

    public SummaryInfo[] getSummaryData() {
        SummaryInfo[] summaryArray = null;
        summaryList = new ArrayList<SummaryInfo>();
        ServiceManager serviceManager = new ServiceManager();

        String url = RestURIs.getPoSummaryURI();
        System.out.println("url:" + url);
        String jsonArrayAsString = serviceManager.invokeREAD(url);

        try {
            JSONObject jsonObject = new JSONObject(jsonArrayAsString);
            JSONObject parent = jsonObject.getJSONObject("P_PO_SUMMARY_TAB");
            JSONArray nodeArray = parent.getJSONArray("P_PO_SUMMARY_TAB_ITEM");

            int size = nodeArray.length();
            for (int i = 0; i < size; i++) {
                JSONObject temp = nodeArray.getJSONObject(i);

                String recordId = null;
                if (temp.getString("RECORD_ID") != null)
                    recordId = temp.getString("RECORD_ID");

                String status = null;
                if (temp.getString("STATUS") != null)
                    status = temp.getString("STATUS");

                String status_count = null;
                if (temp.getString("STATUS_COUNT") != null)
                    status_count = temp.getString("STATUS_COUNT");

                SummaryInfo summaryRec = new SummaryInfo(recordId, status, status_count);

                summaryList.add(summaryRec);
                
                System.out.println("summary servie:"+status);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        summaryArray = summaryList.toArray(new SummaryInfo[summaryList.size()]);
        return summaryArray;

    }
}
