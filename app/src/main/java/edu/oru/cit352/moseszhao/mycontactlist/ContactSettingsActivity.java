package edu.oru.cit352.moseszhao.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ContactSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);
        initListButton();
        initMapButton();
        initSettingsButton();
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
        ImageButton ibmap = findViewById(R.id.imageButtonMap);
        ibmap.setOnClickListener(new View.OnClickListener() {
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
        //Get the view with ID and diable it
        ImageButton ibSet = findViewById(R.id.imageButtonSettings);
        ibSet.setEnabled(false);
    }
}