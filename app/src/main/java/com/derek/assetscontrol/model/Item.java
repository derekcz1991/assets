package com.derek.assetscontrol.model;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

/**
 * Created by derek on 16/4/9.
 */
public class Item implements Serializable{
    @JSONField(name = "item_id") private long itemId;
    @JSONField(name = "nfc_id") private String nfcId;
    @JSONField(name = "item_name") private String itemName;
    @JSONField(name = "item_cat_name") private String itemCatName;
    @JSONField(name = "location") private String location;
    @JSONField(name = "item_image") private String itemImage;
    @JSONField(name = "issue_date") private String issueDate;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getNfcId() {
        return nfcId;
    }

    public void setNfcId(String nfcId) {
        this.nfcId = nfcId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCatName() {
        return itemCatName;
    }

    public void setItemCatName(String itemCatName) {
        this.itemCatName = itemCatName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
}
