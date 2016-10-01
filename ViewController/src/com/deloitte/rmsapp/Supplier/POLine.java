package com.deloitte.rmsapp.Supplier;

public class POLine {
    public POLine() {
        super();
    }
    private String poLineNumber;
    private String item;


    private String quantity;
    private String uom;
    private String price;
    private String lineReason;

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setPoLineNumber(String poLineNumber) {
        this.poLineNumber = poLineNumber;
    }

    public String getPoLineNumber() {
        return poLineNumber;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getUom() {
        return uom;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return price;
    }


    public void setLineReason(String lineReason) {
        this.lineReason = lineReason;
    }

    public String getLineReason() {
        return lineReason;
    }

    public POLine(String poLineNumber,String item, String quantity, String uom, String price,String lineReason) {
        this.poLineNumber = poLineNumber;
        this.item = item;
        this.quantity = quantity;
        this.uom = uom;
        this.price = price;
        this.lineReason = lineReason;

    }
}
