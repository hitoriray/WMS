package org.example.warehouse.dao;

public class PermissionDao {
    private String name;
    private String IDnumber;
    private String Inquire;
    private String Inbound;
    private String Outbound;
    private String Manager;
    private String File;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String IDnumber) {
        this.IDnumber = IDnumber;
    }

    public String getInquire() {
        return Inquire;
    }

    public void setInquire(String inquire) {
        Inquire = inquire;
    }

    public String getInbound() {
        return Inbound;
    }

    public void setInbound(String inbound) {
        Inbound = inbound;
    }

    public String getOutbound() {
        return Outbound;
    }

    public void setOutbound(String outbound) {
        Outbound = outbound;
    }

    public String getManager() {
        return Manager;
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public String getFile() {
        return File;
    }

    public void setFile(String file) {
        File = file;
    }

    @Override
    public String toString() {
        return "PermissionDao{" +
                "name='" + name + '\'' +
                ", IDnumber='" + IDnumber + '\'' +
                ", Inquire='" + Inquire + '\'' +
                ", Inbound='" + Inbound + '\'' +
                ", Outbound='" + Outbound + '\'' +
                ", Manager='" + Manager + '\'' +
                ", File='" + File + '\'' +
                '}';
    }
}
