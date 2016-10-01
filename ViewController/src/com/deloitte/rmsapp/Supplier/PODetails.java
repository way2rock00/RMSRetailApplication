package com.deloitte.rmsapp.Supplier;

import java.util.List;

public class PODetails {
    public PODetails() {
        super();
    }
    private String recordId;
    private String poNumber;
    private String orderType;
    private String poDate;
    private String buyer;
    private String status;
    private String pickUpDate;
    private String notAfterDate;
    private String poTotal;
    private String headerReason;
    private POLine[] poLines;

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

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    public String getPoDate() {
        return poDate;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setNotAfterDate(String notAfterDate) {
        this.notAfterDate = notAfterDate;
    }

    public String getNotAfterDate() {
        return notAfterDate;
    }

    public void setPoTotal(String poTotal) {
        this.poTotal = poTotal;
    }

    public String getPoTotal() {
        return poTotal;
    }


    public void setPoLines(POLine[] poLines) {
        this.poLines = poLines;
    }

    public POLine[] getPoLines() {
        return poLines;
    }

    public void setHeaderReason(String headerReason) {
        this.headerReason = headerReason;
    }

    public String getHeaderReason() {
        return headerReason;
    }


    public PODetails (String recordId
                    ,String poNumber
                    ,String orderType
                    ,String poDate
                    ,String buyer   
                    ,String status
                    ,String pickUpDate
                    ,String notAfterDate
                    ,String poTotal
                    ,String headerReason
                    ,POLine[] poLines
                      )
    {
        this.recordId = recordId;
        this.poNumber = poNumber;
        this.orderType = orderType;
        this.poDate = poDate;
        this.buyer = buyer;
        this.status = status;
        this.pickUpDate = pickUpDate;
        this.notAfterDate = notAfterDate;
        this.poTotal = poTotal;
        this.headerReason = headerReason;
        this.poLines = poLines;
    }
}
