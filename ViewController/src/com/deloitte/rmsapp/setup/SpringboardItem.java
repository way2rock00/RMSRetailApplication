package com.deloitte.rmsapp.setup;

public class SpringboardItem {
    String key;
    String value;

    public SpringboardItem() {
        super();
    }

    SpringboardItem(String key, String value) {
        setKey(key);
        setValue(value);
    }


    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
