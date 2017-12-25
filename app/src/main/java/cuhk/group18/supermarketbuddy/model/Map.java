
package cuhk.group18.supermarketbuddy.model;

import java.util.HashMap;
import java.util.List;

public class Map {

    private List<Location> locations = null;
    private java.util.Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public java.util.Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
