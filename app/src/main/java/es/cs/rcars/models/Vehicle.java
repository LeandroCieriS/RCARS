package es.cs.rcars.models;

import com.google.gson.annotations.SerializedName;

import java.util.Currency;
import java.util.Date;

public class Vehicle {
    @SerializedName("numberPlate")
    private String NumberPlate;
    @SerializedName("make")
    private String Make;
    @SerializedName("model")
    private String Model;
    @SerializedName("year")
    private int Year;
    @SerializedName("purchaseKms")
    private int PurchaseKms;
    @SerializedName("fuel")
    private String Fuel;
    @SerializedName("transmission")
    private String Transmission;
    @SerializedName("inspectionDate")
    private Date InspectionDate;
    @SerializedName("titleOwner")
    private String TitleOwner;
    private Currency SellingPrice;
    private Currency PurchasePrice;
    @SerializedName("purchaseDate")
    private Date PurchaseDate;
    @SerializedName("secondKey")
    private boolean SecondKey;
    @SerializedName("manual")
    private boolean Manual;
    private Currency ServiceCost;
    private Currency BodyworkCost;
    private Currency CleaningCost;
    private Currency TotalCost;

    public String getNumberPlate() {
        return NumberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        NumberPlate = numberPlate;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getPurchaseKms() {
        return PurchaseKms;
    }

    public void setPurchaseKms(int purchaseKms) {
        PurchaseKms = purchaseKms;
    }

    public String getFuel() {
        return Fuel;
    }

    public void setFuel(String fuel) {
        Fuel = fuel;
    }

    public String getTransmission() {
        return Transmission;
    }

    public void setTransmission(String transmission) {
        Transmission = transmission;
    }

    public Date getInspectionDate() {
        return InspectionDate;
    }

    public void setInspectionDate(Date inspectionDate) {
        InspectionDate = inspectionDate;
    }

    public String getTitleOwner() {
        return TitleOwner;
    }

    public void setTitleOwner(String titleOwner) {
        TitleOwner = titleOwner;
    }

    public Currency getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(Currency sellingPrice) {
        SellingPrice = sellingPrice;
    }

    public Currency getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(Currency purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public Date getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    public boolean hasSecondKey() {
        return SecondKey;
    }

    public void setSecondKey(boolean secondKey) {
        SecondKey = secondKey;
    }

    public boolean hasManual() {
        return Manual;
    }

    public void setManual(boolean manual) {
        Manual = manual;
    }

    public Currency getServiceCost() {
        return ServiceCost;
    }

    public void setServiceCost(Currency serviceCost) {
        ServiceCost = serviceCost;
    }

    public Currency getBodyworkCost() {
        return BodyworkCost;
    }

    public void setBodyworkCost(Currency bodyworkCost) {
        BodyworkCost = bodyworkCost;
    }

    public Currency getCleaningCost() {
        return CleaningCost;
    }

    public void setCleaningCost(Currency cleaningCost) {
        CleaningCost = cleaningCost;
    }

    public Currency getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(Currency totalCost) {
        TotalCost = totalCost;
    }
}
