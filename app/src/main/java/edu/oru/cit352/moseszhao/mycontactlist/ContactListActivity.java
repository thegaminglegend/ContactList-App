package edu.oru.cit352.moseszhao.mycontactlist;

//Imports
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/*
Name: Mengen Zhao
Professor: Dr. Osborne
Program: Contact List app
Date: 2/20/2023
Description: A contact list App that stores user's information. ContactListActivity that has the contact list of user.
*/


public class ContactListActivity extends AppCompatActivity {

    @Override
    //Method that initializes the activity when was navigated
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        //methods to initialize the Buttons
        initListButton();
        initMapButton();
        initSettingsButton();
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
}