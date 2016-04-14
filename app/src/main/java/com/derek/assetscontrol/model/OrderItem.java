package com.derek.assetscontrol.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

/**
 * Created by derek on 16/4/9.
 */
public class OrderItem extends Item implements Serializable {
    @JSONField(name = "track_id") private long trickId;
    @JSONField(name = "sent_to") private String sentTo;
    @JSONField(name = "sent_from_name") private String sentFromName;
    @JSONField(name = "current_location") private String curLocation;
    @JSONField(name = "current_location_name") private String curLocationName;
    @JSONField(name = "target_location") private String targetLocation;
    @JSONField(name = "target_location_name") private String targetLocationName;
    @JSONField(name = "status") private String status;
    @JSONField(name = "flag") private int flag;
    @JSONField(name = "tagged_time") private String taggedTime;

    public long getTrickId() {
        return trickId;
    }

    public void setTrickId(long trickId) {
        this.trickId = trickId;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getSentFromName() {
        return sentFromName;
    }

    public void setSentFromName(String sentFromName) {
        this.sentFromName = sentFromName;
    }

    public String getCurLocation() {
        return curLocation;
    }

    public void setCurLocation(String curLocation) {
        this.curLocation = curLocation;
    }

    public String getCurLocationName() {
        return curLocationName;
    }

    public void setCurLocationName(String curLocationName) {
        this.curLocationName = curLocationName;
    }

    public String getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(String targetLocation) {
        this.targetLocation = targetLocation;
    }

    public String getTargetLocationName() {
        return targetLocationName;
    }

    public void setTargetLocationName(String targetLocationName) {
        this.targetLocationName = targetLocationName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTaggedTime() {
        return taggedTime;
    }

    public void setTaggedTime(String taggedTime) {
        this.taggedTime = taggedTime;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
