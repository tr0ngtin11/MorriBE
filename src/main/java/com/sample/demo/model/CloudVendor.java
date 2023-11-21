package com.sample.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vendor_table")
public class CloudVendor {
    @Id
    private String vendorId;
    private String vendorName;
    private String vendorAdress;
    private String vendorPhoneNumber;
    public String getvendorId() {
        return vendorId;
    }

    public void setvendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getvendorName() {
        return vendorName;
    }

    public void setvendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getvendorAdress() {
        return vendorAdress;
    }

    public void setvendorAdress(String vendorAdress) {
        this.vendorAdress = vendorAdress;
    }

    public String getvendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public void setvendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public CloudVendor() {
    }

    public CloudVendor(String vendorId, String vendorName, String vendorAdress, String vendorPhoneNumber) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorAdress = vendorAdress;
        this.vendorPhoneNumber = vendorPhoneNumber;
    }


}
