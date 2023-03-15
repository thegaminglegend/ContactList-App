package edu.oru.cit352.moseszhao.mycontactlist;

//Imports
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

/*
Name: Mengen Zhao
Professor: Dr. Osborne
Program: Contact List app
Date: 2/28/2023
Description: A contact list App that stores user's information. ContactListActivity that has the contact list of user.
*/


public class ContactListActivity extends AppCompatActivity {

    //Instance Variables
    ArrayList<Contact> contacts;
    ContactAdapter contactAdapter;
    RecyclerView contactList;

    //Listener for opening main activity when the contact is selected
    private View.OnClickListener onItemClickerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)  view.getTag();
            //Get which position of contact is clicked
            int position = viewHolder.getAdapterPosition();
            //Get the contact
            int contactId = contacts.get(position).getContactID();
            //Set up intent
            Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
            //get the contact id put in intent
            intent.putExtra("contactId", contactId);
            //Open Main activity with the intent
            startActivity(intent);
        }
    };

    @Override
    //Method that initializes the activity when was navigated
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        //methods to initialize the Buttons
        initListButton();
        initMapButton();
        initSettingsButton();
        initAddContactButton();
        initDeleteSwitch();
    }


    //On Resume method
    public void onResume() {
        super.onResume();

        String sortBy = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortfield", "contactname");
        String sortOrder = getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE).getString("sortorder", "ASC");


        //To get the data from database and display it
        //Instance variable
        ContactDataSource ds = new ContactDataSource(this);

        try {
            //Open DB get contacts and close DB
            ds.open();
            contacts = ds.getContacts(sortBy, sortOrder);
            ds.close();

            //
            if(contacts.size() > 0) {
                //Find view with ID
                contactList = findViewById(R.id.rvContacts);
                //Instantiate layoutManager and set the layout manager
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((this));
                contactList.setLayoutManager(layoutManager);
                //Instantiate contactAdapter and set the contactAdapter
                contactAdapter = new ContactAdapter(contacts, this);
                contactAdapter.setOnItemClickListener(onItemClickerListener);
                contactList.setAdapter(contactAdapter);
            }
            else{
                Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        //If something wrong show error text
        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }
    }

    //Method to initialize List Button to disable it
    private void initListButton(){
        //Get the view with ID and disable it
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setEnabled(false);
        //Animation to darken the imageButton
        ibList.animate().alpha(0.6f).setDuration(500);
    }

    //Method to initialize Map Button
    private void initMapButton(){
        //Get the view with ID
        ImageButton ibMap = findViewById(R.id.imageButtonMap);
        ibMap.setOnClickListener(new View.OnClickListener() {
            //Method to navigate to map activity when view is clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactListActivity.this, ContactMapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //Method to initialize Settings Button
    private void initSettingsButton(){
        //Get the view with ID
        ImageButton ibSet = findViewById(R.id.imageButtonSettings);
        ibSet.setOnClickListener(new View.OnClickListener() {
            //Method to navigate to settings activity when view is clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactListActivity.this, ContactSettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //Method to start a listener for add button
    private void initAddContactButton(){
        //Find view with ID
        Button newContact = findViewById(R.id.buttonAddContact);
        //Open main activity when clicked
        newContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    //Method to initialize delete switch
    private void initDeleteSwitch(){
        //Find view with ID
        Switch s = findViewById(R.id.switchDelete);
        //Set listener so that if it is checked to turn on delete mode
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean status = compoundButton.isChecked();
                contactAdapter.setDelete(status);
                contactAdapter.notifyDataSetChanged();
            }
        });
    }

}