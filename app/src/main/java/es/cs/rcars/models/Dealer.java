package es.cs.rcars.models;

import com.google.gson.annotations.SerializedName;

public class Dealer {

    @SerializedName("dealerName")
    private String Name;
    @SerializedName("address")
    private String Address;
    @SerializedName("phoneNumber")
    private String PhoneNumber;
    @SerializedName("island")
    private String Island;

    public String getName() { return Name; }

    public String getAddress() { return Address; }

    public String getPhoneNumber() { return PhoneNumber; }

    public String getIsland() { return Island; }
}

