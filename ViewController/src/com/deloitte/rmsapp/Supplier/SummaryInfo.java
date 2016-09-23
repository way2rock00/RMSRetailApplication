package com.deloitte.rmsapp.Supplier;

public class SummaryInfo {
    public SummaryInfo() {
        super();
    }
    private String recordId;
    private String status;
    private String status_count;

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus_count(String status_count) {
        this.status_count = status_count;
    }

    public String getStatus_count() {
        return status_count;
    }
    
    public SummaryInfo(String recordId, String status, String status_count){
        this.recordId = recordId;
        this.status = status;
        this.status_count = status_count;
    }
    
}
