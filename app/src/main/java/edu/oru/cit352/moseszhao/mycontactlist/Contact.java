package edu.oru.cit352.moseszhao.mycontactlist;

/*
Name: Mengen Zhao
Professor: Dr. Osborne
Program: Contact List app
Date: 2/20/2023
Description: A contact list App that stores user's information. The class that has properties for a contact
*/

//Import
import java.util.Calendar;

public class Contact {
    //Variables for the properties of a contact
    private int contactID;
    private String contactName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String cellNumber;
    private String eMail;
    private Calendar birthday;

    //Constructor
    public Contact() {
        //New contact would be -1
        contactID = -1;
        birthday = Calendar.getInstance();

    }

    //Getters
    public int getContactID() {
        return contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    //Setters
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }
}