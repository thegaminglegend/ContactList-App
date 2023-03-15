package edu.oru.cit352.moseszhao.mycontactlist;

//Imports
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/*
Name: Mengen Zhao
Professor: Dr. Osborne
Program: Contact List app
Date: 3、2/2023
Description: A contact list App that stores user's information.
Class that extends RecyclerView.Adapter to manage the data
*/

public class ContactAdapter extends RecyclerView.Adapter {
    //Instance Variables
    private ArrayList<Contact> contactData;
    private boolean isDeleting;
    private Context parentContext;
    //Listener
    private View.OnClickListener mOnItemClickListener;


    //Embedded class for viewHolder that stores the data of the contact
    public class ContactViewHolder extends RecyclerView.ViewHolder {
        //Reference Variables
        public TextView textViewContact;
        public TextView textPhone;
        public Button deleteButton;

        //Constructor
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            //Find view with ID
            textViewContact = itemView.findViewById(R.id.textContactName);
            textPhone = itemView.findViewById(R.id.textPhoneNumber);
            deleteButton = itemView.findViewById(R.id.buttonDeleteContact);
            //Tag to know which item was clicked
            itemView.setTag(this);
            //Set Listener
            itemView.setOnClickListener(mOnItemClickListener);
        }

        //Getters
        public TextView getContactTextView() {
            return textViewContact;
        }
        public TextView getPhoneTextView() {
            return textPhone;
        }
        public Button getDeleteButton() {
            return deleteButton;
        }
    }

    //Constructor
    public ContactAdapter(ArrayList<Contact> arrayList, Context context) {
        contactData = arrayList;
        parentContext = context;
    }

    //Method to set the Listener
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    //Initialize view holder
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ContactViewHolder(v);
    }

    //Get the data give it to viewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ContactViewHolder cvh = (ContactViewHolder) holder;
        //Get the Name and Number in the relevant position and set it to viewHolder
        cvh.getContactTextView().setText(contactData.get(position).getContactName());
        cvh.getPhoneTextView().setText(contactData.get(position).getPhoneNumber());

        //Check if is in delete mode
        if(isDeleting){
            //Make delete button to be visible
            cvh.getDeleteButton().setVisibility(View.VISIBLE);
            //Set listener to call method to delete the contact
            cvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(position);
                }
            });
        } else {
            cvh.getDeleteButton().setVisibility(View.INVISIBLE);
        }
    }

    //Method to get the number of Items
    @Override
    public int getItemCount() {
        return contactData.size();
    }

    //Method to delete Item
    private void deleteItem(int position) {
        //Get contact to delete with position
        Contact contact = contactData.get(position);
        //Instance of ContactDataSource
        ContactDataSource ds = new ContactDataSource(parentContext);
        try {
            //Open DB call method to delete the contact record and closeDB
            ds.open();
            boolean didDelete = ds.deleteContact(contact.getContactID());
            ds.close();
            //If successfully deleted remove the contact from the ArrayLIst and reload the display
            if (didDelete) {
                contactData.remove(position);
                notifyDataSetChanged();
            }
            //If not show fail message
            else {
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e) {
            //Do nothing if fails
        }
    }

    //Method to set isDeleting to true or false
    public void setDelete(boolean b) {
        isDeleting = b;
    }


}
