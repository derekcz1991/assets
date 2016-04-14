package com.derek.assetscontrol.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

/**
 * Created by derek on 16/4/9.
 */
public class Location implements Serializable {
    @JSONField(name = "location_id") private String locationId;
    @JSONField(name = "location") private String locationName;

    public Location() {
    }

    public Location(String locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
