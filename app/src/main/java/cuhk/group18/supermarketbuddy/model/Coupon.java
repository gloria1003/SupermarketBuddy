
package cuhk.group18.supermarketbuddy.model;

import java.util.HashMap;
import java.util.Map;

public class Coupon {

    private String id;
    private String details;
    private String expirydate;
    private String redempturl;
    
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getExpirydate() {

        if (expirydate!=null && expirydate.length()>=10){
            return expirydate.substring(0,10);
        } else {


            return expirydate;
        }
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getRedempturl() {
        return redempturl;
    }

    public void setRedempturl(String redempturl) {
        this.redempturl = redempturl;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
