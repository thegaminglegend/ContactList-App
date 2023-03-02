package edu.oru.cit352.moseszhao.mycontactlist;

//Imports
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.Calendar;

/*
Name: Mengen Zhao
Professor: Dr. Osborne
Program: Contact List app
Date: 2/28/2023
Description: A contact list App that stores user's information. The main activity of the app which initializes everything.
*/

public class MainActivity extends AppCompatActivity implements DatePickerDialog.SaveDateListener{
    //Reference variable
    private Contact currentContact;

    @Override
    //Method that initializes the app when starts
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //methods to initialize the Buttons
        initListButton();
        initMapButton();
        initSettingsButton();
        initToggleButton();
        initChangeDateButton();
        initSaveButton();
        //Initialize text edits to be disabled
        setForEditing(false);
        //Create instance of Contact object
        currentContact = new Contact();
        initTextChangeEvents();
    }

    //Method to change the text in contact to User's change
    private void initTextChangeEvents() {
        //Find view with ID
        final EditText etContactName = findViewById(R.id.editName);
        //When text is changed by User change the relevant field in the Contact object
        etContactName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not needed in this situation
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not needed in this situation
            }

            //After text is changed by user update contact object
            @Override
            public void afterTextChanged(Editable editable) {
                currentContact.setContactName(etContactName.getText().toString());
            }
        });

        //Find view with ID
        final EditText etStreetAddress = findViewById(R.id.editAddress);
        //When text is changed by User change the relevant field in the Contact object
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            //After text is changed by user update contact object
            public void afterTextChanged(Editable s) {
                currentContact.setStreetAddress(etStreetAddress.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //Not needed in this situation
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Not needed in this situation
            }
        });

        //Find view with ID
        final EditText etCity = findViewById(R.id.editCity);
        //When text is changed by User change the relevant field in the Contact object
        etCity.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
                //Not needed in this situation
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not needed in this situation
            }

            // After text is changed by user update contact object
            public void afterTextChanged(Editable editable) {
                currentContact.setCity(etCity.getText().toString());
            }
        });

        //Find view with ID
        final EditText etState = findViewById(R.id.editState);
        //When text is changed by User change the relevant field in the Contact object
        etState.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
                //Not needed in this situation
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not needed in this situation
            }
            //Not needed in this situation
            public void afterTextChanged(Editable editable) {
                currentContact.setState(etState.getText().toString());
            }
        });

        //Find view with ID
        final EditText etZip = findViewById(R.id.editZipcode);
        //When text is changed by User change the relevant field in the Contact object
        etZip.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not needed in this situation
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not needed in this situation
            }
            //Not needed in this situation
            public void afterTextChanged(Editable editable) {
                currentContact.setZipCode(etZip.getText().toString());
            }
        });

        //Find view with ID
        final EditText etPhone = findViewById(R.id.editHome);
        //When text is changed by User change the relevant field in the Contact object
        etPhone.addTextChangedListener(new TextWatcher() {
            //Not needed in this situation
            public void afterTextChanged(Editable s) {
                currentContact.setPhoneNumber(etPhone.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //Not needed in this situation
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Not needed in this situation
            }
        });

        //Find view with ID
        final EditText etCell = findViewById(R.id.editCell);
        //When text is changed by User change the relevant field in the Contact object
        etCell.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not needed in this situation
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not needed in this situation
            }
            //Not needed in this situation
            public void afterTextChanged(Editable editable) {
                currentContact.setCellNumber(etCell.getText().toString());
            }
        });

        //Find view with ID
        final EditText etEMail = findViewById(R.id.editEmail);
        //When text is changed by User change the relevant field in the Contact object
        etEMail.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not needed in this situation
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Not needed in this situation
            }
            //Not needed in this situation
            public void afterTextChanged(Editable editable) {
                currentContact.seteMail(etEMail.getText().toString());
            }
        });

        //Call for formatting phone numbers
        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        etCell.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    //Method to initialize List Button
    private void initListButton(){
        //Get the view with ID
        ImageButton ibList = findViewById(R.id.imageButtonList);
        ibList.setOnClickListener(new View.OnClickListener() {
            //Method to navigate to contact list activity when view is clicked
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
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
                Intent intent = new Intent(MainActivity.this, ContactMapActivity.class);
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
                Intent intent = new Intent(MainActivity.this, ContactSettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    //Method to initialize Toggle Button
    private void initToggleButton(){
        //Get the view with ID
        final ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
        editToggle.setOnClickListener(new View.OnClickListener() {
            //Method to call setForEditing as True when view is clicked to be Checked
            @Override
            public void onClick(View view) {
                setForEditing(editToggle.isChecked());
            }
        });
    }

    //Method to enable or disable the editText and buttons
    private void setForEditing(boolean enabled) {
        //Find the views by ID
        EditText editName = (EditText) findViewById(R.id.editName);
        EditText editAddress = (EditText) findViewById(R.id.editAddress);
        EditText editCity = (EditText) findViewById(R.id.editCity);
        EditText editState = (EditText) findViewById(R.id.editState);
        EditText editZipCode = (EditText) findViewById(R.id.editZipcode);
        EditText editPhone = (EditText) findViewById(R.id.editHome);
        EditText editCell = (EditText) findViewById(R.id.editCell);
        EditText editEmail = (EditText) findViewById(R.id.editEmail);
        Button buttonChange = (Button) findViewById(R.id.buttonBirthday);
        Button buttonSave = (Button) findViewById(R.id.buttonSave);

        //enable or disable the editTexts and buttons
        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipCode.setEnabled(enabled);
        editPhone.setEnabled(enabled);
        editCell.setEnabled(enabled);
        editEmail.setEnabled(enabled);
        buttonChange.setEnabled(enabled);
        buttonSave.setEnabled(enabled);

        //If True focus on editName first
        if (enabled) {
            editName.requestFocus();
        }
        //Otherwise do not focus
        else {
            ScrollView s = (ScrollView) findViewById(R.id.scrollViewContact);
            s.fullScroll(ScrollView.FOCUS_UP);
            s.clearFocus();
        }
    }

    //Method to set the Birthday text
    @Override
    public void didFinishDatePickerDialog(Calendar selectedTime) {
        //find view with ID
        TextView birthDay = findViewById(R.id.textBirthday);
        //set birth date text to the formatted selected time
        birthDay.setText(DateFormat.format("MM/dd/yyyy", selectedTime));
        //Set birthday to selectedTime in the Contact class instance
        currentContact.setBirthday(selectedTime);
    }

    //Method to initialize Change Date Button
    private void initChangeDateButton(){
        //Find the view by ID
        Button changeDate = findViewById(R.id.buttonBirthday);
        changeDate.setOnClickListener(new View.OnClickListener() {
            //Method to change the date when clicked with datePickerDialog class
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.show(fm, "DatePick");
            }
        });
    }

    //Method to initialize save button
    private void initSaveButton() {
        //Find the view by ID
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            //Method to save the contact information into DB when save clicked
            @Override
            public void onClick(View view) {
                //Declare variable
                boolean wasSuccessful;
                //Call hideKeyboard method
                hideKeyboard();
                //instantiate class to manage database
                ContactDataSource ds = new ContactDataSource(MainActivity.this);
                try {
                    //Open DB
                    ds.open();
                    //Check if the contact is new if true insert into DB
                    if (currentContact.getContactID() == -1) {
                        wasSuccessful = ds.insertContact(currentContact);
                        //If inserted update the contactID
                        if(wasSuccessful){
                            int newID = ds.getLastContactID();
                            currentContact.setContactID(newID);
                        }
                    }
                    //If false update in DB
                    else {
                        wasSuccessful = ds.updateContact(currentContact);
                    }
                    //close DB
                    ds.close();
                }
                //If unable to insert or update set wasSuccessful to false
                catch (Exception e) {
                    wasSuccessful = false;
                }

                //If inserted or updated
                if (wasSuccessful) {
                    //Find view by ID
                    ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
                    //Disable all edit text
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });
    }

    //Method to hide keyboard
    private void hideKeyboard() {
        //Get all the view with ID and hide the keyboard on those views when it is called
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = findViewById(R.id.editName);
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
        EditText editAddress = findViewById(R.id.editAddress);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
        EditText et = findViewById(R.id.editCity);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editState);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editZipcode);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editHome);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editCell);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editEmail);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }
}