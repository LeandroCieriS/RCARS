package es.cs.rcars.models;

import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("id")
    private String Id;
    @SerializedName("firstName")
    private String FirstName;
    @SerializedName("lastName")
    private String LastName;
    @SerializedName("idNumber")
    private String IdNumber;
    @SerializedName("isCompany")
    private boolean IsCompany;
    @SerializedName("email")
    private String Email;
    @SerializedName("address")
    private String Address;
    @SerializedName("phoneNumber")
    private String PhoneNumber;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public boolean isCompany() {
        return IsCompany;
    }

    public void setCompany(boolean company) {
        IsCompany = company;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
