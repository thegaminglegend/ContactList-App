package edu.oru.cit352.moseszhao.mycontactlist;

//Imports
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/*
Name: Mengen Zhao
Professor: Dr. Osborne
Program: Contact List app
Date: 2/28/2023
Description: A contact list App that stores user's information. ContactSettingsActivity that has the setting.
*/
public class ContactSettingsActivity extends AppCompatActivity {

    @Override
    //Method that initializes the activity when was navigated
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);
        //methods to initialize the Buttons
        initListButton();
        initMapButton();
        initSettingsButton();
        initSettings();
        initSortByClick();
        initSortOrderClick();
    }

    //Method to initialize List Button
    private void initListButton(){
        //Get the view with ID
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            //Method to navigate to contact list activity when view is clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactSettingsActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //Method to initialize Map Button
    private void initMapButton(){
        //Get the view with ID
        ImageButton ibMap = findViewById(R.id.imageButtonMap);
        ibMap.setOnClickListener(new View.OnClickListener() {
            //Method to navigate to map activity when view is clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactSettingsActivity.this, ContactMapActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //Method to initialize Settings Button to disable it
    private void initSettingsButton(){
        //Get the view with ID and disable it
        ImageButton ibSet = findViewById(R.id.imageButtonSettings);
        ibSet.setEnabled(false);
        //Animation to darken the imageButton
        ibSet.animate().alpha(0.6f).setDuration(500);
    }

    //Method to load and display current preferences
    private void initSettings(){
        //Set the default preference for sort by to contactname
        String sortBy = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortfield", "contactname");
        //Set the default preference for sort order to ascending
        String sortOrder = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortorder", "ASC");

        //Get the view with ID
        RadioButton rbName = findViewById(R.id.radioName);
        RadioButton rbCity = findViewById(R.id.radioCity);
        RadioButton rbBirthDay = findViewById(R.id.radioBirthday);

        //Check which one was selected by User as preference for sort by and set it to checked
        if(sortBy.equalsIgnoreCase("contactname")){
            rbName.setChecked(true);
        }
        else if(sortBy.equalsIgnoreCase("city")){
            rbCity.setChecked(true);
        }
        else {
            rbBirthDay.setChecked(true);
        }

        //Get the view with ID
        RadioButton rbAscending = findViewById(R.id.radioAscending);
        RadioButton rbDescending = findViewById(R.id.radioDescending);

        //Check which one was selected by user as preference for sort order and set it to checked
        if(sortOrder.equalsIgnoreCase("ASC")){
            rbAscending.setChecked(true);
        }
        else{
            rbDescending.setChecked(true);
        }
    }

    //Method to set preference when user change setting for sort by
    private void initSortByClick() {

        //find view with ID
        RadioGroup rgSortBy = findViewById(R.id.radioGroupSortBy);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbName = findViewById(R.id.radioName);
                RadioButton rbCity = findViewById(R.id.radioCity);

                //check which is checked by user and set it as preference
                if (rbName.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortfield", "contactname").apply();
                }
                else if (rbCity.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortfield", "city").apply();
                }
                else {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortfield", "birthday").apply();
                }
            }
        });
    }

    //Method to set preference when user change setting for sort by
    private void initSortOrderClick() {

        //find view with ID
        RadioGroup rgSortOrder = findViewById(R.id.radioGroupSortOrder);
        //If the radio group is changed check the correct radio button
        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                //find view with ID
                RadioButton rbAscending = findViewById(R.id.radioAscending);

                //check which is checked by user and set it as preference
                if (rbAscending.isChecked()) {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortorder", "ASC").apply();
                }
                else {
                    getSharedPreferences("MyContactListPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortorder", "DESC").apply();
                }
            }
        });
    }


}