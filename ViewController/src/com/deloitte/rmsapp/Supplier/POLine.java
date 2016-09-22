package com.deloitte.rmsapp.Supplier;

public class POLine {
    public POLine() {
        super();
    }
    private String poLineNumber;
    private String quantity;
    private String uom;
    private String price;

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
    
    public POLine(String poLineNumber
                  ,String quantity
                  ,String uom
                  ,String price
                  )
    {
        this.poLineNumber = poLineNumber;
        this.quantity =  quantity;
        this.uom = uom;
        this.price = price;
            
    }
}
