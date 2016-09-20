package com.deloitte.rmsapp.Supplier;

public class POHeaders {
    public POHeaders() {
        super();
    }
    private String poNumber;
    private String orderType;
    private String poDate;
    private String buyer;
    private String status;
    private String pickUpDate;
    private String notAfterDate;
    private String poTotal;


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

}
