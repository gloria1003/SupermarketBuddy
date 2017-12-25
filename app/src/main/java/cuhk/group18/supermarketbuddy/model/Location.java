
package cuhk.group18.supermarketbuddy.model;

import java.util.HashMap;
import java.util.Map;

public class Location {

    private Integer id;
    private String description;
    private Coord coord;
    private String type;
    private Offeritem offeritem;
    private Coupon coupon;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Offeritem getOfferitem() {
        return offeritem;
    }

    public void setOfferitem(Offeritem offeritem) {
        this.offeritem = offeritem;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
