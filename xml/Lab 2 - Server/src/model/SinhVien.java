package model;

import java.io.Serializable;

public class SinhVien implements Serializable {
    private static final long serialVersionUID = 1L;
    private String stuID;
    private String name;
    private String address;

    public SinhVien() {
    }

    public SinhVien(String stuID, String name, String address) {
        this.stuID = stuID;
        this.name = name;
        this.address = address;
    }

    public String getStuID() {
        return stuID;
    }

    public void setStuID(String stuID) {
        this.stuID = stuID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
