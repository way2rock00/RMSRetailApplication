package com.deloitte.rmsapp.setup;

public class SpringboardItem {
    String key;
    String value;
    String featureId;    

    public SpringboardItem() {
        super();
    }

    SpringboardItem(String key, String value, String featureId) {
        setKey(key);
        setValue(value);
        setValue(featureId);
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

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getFeatureId() {
        return featureId;
    }
}
