package com.deloitte.rmsapp.Supplier;

public class NotificationDetails {
    public NotificationDetails() {
        super();
    }

    private String recordId;
    private String poNumber;
    private String poOrderType;
    private String poRevision;
    private String poBuySupName;
    private String poDate;

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoOrderType(String poOrderType) {
        this.poOrderType = poOrderType;
    }

    public String getPoOrderType() {
        return poOrderType;
    }

    public void setPoRevision(String poRevision) {
        this.poRevision = poRevision;
    }

    public String getPoRevision() {
        return poRevision;
    }

    public void setPoBuySupName(String poBuySupName) {
        this.poBuySupName = poBuySupName;
    }

    public String getPoBuySupName() {
        return poBuySupName;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    public String getPoDate() {
        return poDate;
    }

    public NotificationDetails(String recordId, String poNumber, String poOrderType, String poRevision,
                               String poBuySupName, String poDate) {

        this.recordId = recordId;
        this.poNumber = poNumber;
        this.poOrderType = poOrderType;
        this.poRevision = poRevision;
        this.poBuySupName = poBuySupName;
        this.poDate = poDate;
    }

}
