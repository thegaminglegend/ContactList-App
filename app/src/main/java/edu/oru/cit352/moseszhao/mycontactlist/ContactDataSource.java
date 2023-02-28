package edu.oru.cit352.moseszhao.mycontactlist;

//Imports
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLException;

/*
Name: Mengen Zhao
Professor: Dr. Osborne
Program: Contact List app
Date: 2/20/2023
Description: A contact list App that stores user's information. Class to manage database
*/

public class ContactDataSource {
    //Create instance variables
    private SQLiteDatabase database;
    private ContactDBHelper dbHelper;

    //Constructor
    public ContactDataSource(Context context) {
        dbHelper = new ContactDBHelper(context);
    }

    //Method to open the database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //Method to close database
    public void close() {
        dbHelper.close();
    }

    //Method to insert contact
    public boolean insertContact(Contact c) {
        //declare variables
        boolean didSucceed = false;
        try {
            //Create contentValues object instance
            ContentValues initialValues = new ContentValues();

            //Key-Value store data in initialValues
            initialValues.put("contactname", c.getContactName());
            initialValues.put("streetaddress", c.getStreetAddress());
            initialValues.put("city", c.getCity());
            initialValues.put("state", c.getState());
            initialValues.put("zipcode", c.getZipCode());
            initialValues.put("phonenumber", c.getPhoneNumber());
            initialValues.put("cellnumber", c.getCellNumber());
            initialValues.put("email", c.geteMail());
            initialValues.put("birthday", String.valueOf(c.getBirthday().getTimeInMillis()));

            //Insert everything into the database and get true if successfully inserted
            didSucceed = database.insert("contact", null, initialValues) > 0;
        }
        catch (Exception e){
            //Do nothing if there is an exception false would be returned
        }
        return didSucceed;
    }

    //Method to update contact
    public boolean updateContact(Contact c) {
        //declare variables
        boolean didSucceed = false;
        try {
            //Get contact ID to know which row to update
            Long rowId = (long)c.getContactID();
            //Create contentValues object instance
            ContentValues updateValues = new ContentValues();

            //Key-Value store data in updateValues
            updateValues.put("contactname", c.getContactName());
            updateValues.put("streetaddress", c.getStreetAddress());
            updateValues.put("city", c.getCity());
            updateValues.put("state", c.getState());
            updateValues.put("zipcode", c.getZipCode());
            updateValues.put("phonenumber", c.getPhoneNumber());
            updateValues.put("cellnumber", c.getCellNumber());
            updateValues.put("email", c.geteMail());
            updateValues.put("birthday", String.valueOf(c.getBirthday().getTimeInMillis()));

            //Update everything into the database and get true if successfully updated
            didSucceed = database.update("contact", updateValues, "_id=" + rowId, null) > 0;
        }
        catch (Exception e){
            //Do nothing if there is an exception false would be returned
        }
        return didSucceed;
    }

    //Method to get the Id of the last inserted contact in the database
    public int getLastContactID(){
        //declare variables
        int lastId;

        try {
            //Get the last contact id
            String query = "Select MAX(_id) from contact";
            //Pointer to points to the last row of the database
            Cursor cursor = database.rawQuery(query, null);
            //Point cursor to the first row after the query
            cursor.moveToFirst();
            //Get the first column to get Id
            lastId = cursor.getInt(0);
            cursor.close();
        }
        //If exception set lastId as -1
        catch (Exception e){
            lastId = -1;
        }
        return lastId;
    }

}
