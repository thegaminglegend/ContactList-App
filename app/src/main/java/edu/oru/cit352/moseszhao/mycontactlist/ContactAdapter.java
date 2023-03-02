package edu.oru.cit352.moseszhao.mycontactlist;

//Imports
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
    //Instance variable Arraylist of contact object
    private ArrayList<Contact> contactData;

    private View.OnClickListener mOnItemClickListener;

    //Embedded class
    public class ContactViewHolder extends RecyclerView.ViewHolder {
        //Reference Variables
        public TextView textViewContact;
        public TextView textPhone;
        public Button deleteButton;

        //
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
        public TextView getPhoneTextView() {                                    //6
            return textPhone;                                                   //
        }                                                                       //
        public Button getDeleteButton() {                                       //
            return deleteButton;                                                //
        }                                                                       //
    }


    public ContactAdapter(ArrayList<Contact> arrayList) {
        contactData = arrayList;
    }

    //Method to set the Listener
    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ContactViewHolder(v);
    }

    //Get the data give it to viewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ContactViewHolder cvh = (ContactViewHolder) holder;
        //Get the Name and Number in the relevant position and set it to viewHolder
        cvh.getContactTextView().setText(contactData.get(position).getContactName());
        cvh.getPhoneTextView().setText(contactData.get(position).getPhoneNumber());
    }

    //Method to get the number of Items
    @Override
    public int getItemCount() {
        return contactData.size();
    }
}
