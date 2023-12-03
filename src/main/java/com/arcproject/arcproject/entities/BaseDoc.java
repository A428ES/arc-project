package com.arcproject.arcproject.entities;
import org.springframework.data.annotation.Id;

import com.arcproject.arcproject.util.CommonTools;
import java.util.UUID;

public abstract class BaseDoc {
    
    @Id
    private String id;

    private String uuid;
    private String ip_address;
    private double created_timestamp_ms;
    private double modified_timestamp_ms;
    private boolean is_deleted;

    public BaseDoc() {
        if (this.uuid == null) {
        this.uuid = UUID.randomUUID().toString();
        double currentTimestamp = getCurrentUnixTime();
        this.created_timestamp_ms = currentTimestamp;
        this.modified_timestamp_ms = currentTimestamp;
        this.is_deleted = false;
        this.ip_address = "127.0.0.1";
        }
    }

    private double getCurrentUnixTime() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis / 1000.0;
    }

    public String getuuid() {
        return uuid;
    }

    public String getIpAddress() {
        return ip_address;
    }

    public double getCreatedTimestampMs() {
        return created_timestamp_ms;
    }

    public double getModifiedTimestampMs() {
        return modified_timestamp_ms;
    }

    public String getDate(){
        return CommonTools.convertStamp(this.created_timestamp_ms);
    }

    public boolean isDeleted() {
        return this.is_deleted;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setIpAddress(String ipAddress) {
        this.ip_address = ipAddress;
    }

    public void setCreatedTimestampMs(long createdTimestampMs) {
        this.created_timestamp_ms = createdTimestampMs;
    }

    public void setModifiedTimestampMs(long modifiedTimestampMs) {
        this.modified_timestamp_ms = modifiedTimestampMs;
    }

    public void setDeleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}