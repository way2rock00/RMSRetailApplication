package com.deloitte.rmsapp.Supplier;

public class POHeaders {
    public POHeaders() {
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
    private String supplierName;
    private String supplierSiteName;
    private String notBeforeDate;

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


    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordId() {
        return recordId;
    }


    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierSiteName(String supplierSiteName) {
        this.supplierSiteName = supplierSiteName;
    }

    public String getSupplierSiteName() {
        return supplierSiteName;
    }

    public void setNotBeforeDate(String notBeforeDate) {
        this.notBeforeDate = notBeforeDate;
    }

    public String getNotBeforeDate() {
        return notBeforeDate;
    }

    public POHeaders(String recordId, String poNumber, String orderType, String poDate, String buyer, String status,
                     String pickUpDate, String notAfterDate, String poTotal,String notBeforeDate,String supplierName,String supplierSiteName) {
        this.recordId = recordId;
        this.poNumber = poNumber;
        this.orderType = orderType;
        this.poDate = poDate;
        this.buyer = buyer;
        this.status = status;
        this.pickUpDate = pickUpDate;
        this.notAfterDate = notAfterDate;
        this.poTotal = poTotal;
        this.notBeforeDate = notBeforeDate;
        this.supplierName = supplierName;
        this.supplierSiteName = supplierSiteName;
    }

}
