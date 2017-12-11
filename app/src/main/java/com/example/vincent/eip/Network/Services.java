
package com.example.vincent.eip.Network;
import java.io.Serializable;

public class Services implements Serializable{
    private int idService;
    private String nameService;
    private String sector;
    private double price;
    private String hours;

    public Services() {
    }

    public Services(int idService, String NameService, String Sector, double Price, String Hours) {
        this.idService = idService;
        this.nameService = NameService;
        this.sector = Sector;
        this.price = Price;
        this.hours = Hours;
    }

    public void setIdService(int IdService) {
        this.idService = IdService;
    }

    public int getIdService() {
        return (idService);
    }

    public void setNameService(String NameService) {
        this.nameService = NameService;
    }

    public String getNameService() {
        return (nameService);
    }

    public void setSector(String Sector) {
        this.sector = Sector;
    }

    public String getSector() {
        return (sector);
    }

    public void setPrice(double Price) {
        this.price = Price;
    }

    public double getPrice() {
        return (price);
    }

    public void setHours(String Hours) {
        this.hours = Hours;
    }

    public String getHours() {
        return (hours);
    }
}
