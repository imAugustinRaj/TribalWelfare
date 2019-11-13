package com.example.vig.sih_mota;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ImagesActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private FirebaseStorage mStorage;


    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    DatabaseReference mDatabase_ecommerce = FirebaseDatabase.getInstance().getReference("sale");

    private List<Upload> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        mAdapter = new ImageAdapter(ImagesActivity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(ImagesActivity.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("uploads");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImagesActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Item Number " + mUploads.get(position) + " Added To Cart", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(this, "Whatever click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        Upload selectedItem = mUploads.get(position);
        final String selectedKey = selectedItem.getKey();



        StorageReference imageRef = mStorage.getReferenceFromUrl(selectedItem.getImageUrl());
        DatabaseReference link = FirebaseDatabase.getInstance().getReference().child("uploads").child(selectedKey).child("imageUrl");
        DatabaseReference name = FirebaseDatabase.getInstance().getReference().child("uploads").child(selectedKey).child("name");

        TextView vname = (TextView)findViewById(R.id.t2);
        TextView vlink = (TextView)findViewById(R.id.t1);

        link.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                TextView vlink = (TextView)findViewById(R.id.t1);
                String value1 = dataSnapshot.getValue(String.class);
                vlink.setText(value1);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ImagesActivity.this, "error in approval link", Toast.LENGTH_SHORT).show();

            }
        });

        name.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                TextView vname = (TextView)findViewById(R.id.t2);
                String  value1 = dataSnapshot.getValue(String.class);
                vname.setText(value1);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ImagesActivity.this, "error in approval name", Toast.LENGTH_SHORT).show();
            }
        });
        showRadioButtonDialog(selectedKey);

        final ProgressBar progressBar =(ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(20);






        //Toast.makeText(this, vname.getText(), Toast.LENGTH_SHORT).show();






        /*imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(ImagesActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }

    private void showRadioButtonDialog(final String selectedKey) {

        // custom dialog
        final String[] cate = new String[1];
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radiobutton_dialog);
        final List<String> stringList=new ArrayList<>();  // here is list

        stringList.add("Bamboo");
        stringList.add("Textiles");
        stringList.add("Needle work");
        stringList.add("Jewelry");
        stringList.add("Bell Metal");
        stringList.add("Wood Craft");
        stringList.add("Paintings");
        stringList.add("Jute Craft");
        stringList.add("Stone Craft");
        stringList.add("Lac Craft");

        RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.radio_group);

        for(int i=0;i<stringList.size();i++){
            RadioButton rb=new RadioButton(this); // dynamically creating RadioButton and adding to RadioGroup.
            rb.setText(stringList.get(i));
            rg.addView(rb);
        }
        dialog.show();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                cate[0] = stringList.get(checkedId-1);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        TextView vname = (TextView)findViewById(R.id.t2);
                        TextView vlink = (TextView)findViewById(R.id.t1);



                        String nm = (String) vname.getText();
                        String lk = (String) vlink.getText();
                        if(nm.isEmpty() || lk.isEmpty()){

                            Toast.makeText(ImagesActivity.this, "wait data not buffered..", Toast.LENGTH_SHORT).show();

                        }else {


                            //dialog alert to give the price
                            final TextView pric = (TextView)findViewById(R.id.t3);


                            String pricee =pric.getText().toString();
                            price( nm,  lk,  cate[0],    selectedKey);

                        }

                    }
                }, 1000);

                dialog.dismiss();
            }
        });


    }

    public void price(final String nm, final String lk, final String cate, final String selectedKey){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(getApplicationContext());
        edittext.setTextColor(Color.parseColor("#000000"));
        alert.setMessage("ENTER THE PRICE");
        alert.setTitle("Give your price here...");
        final TextView pric = (TextView)findViewById(R.id.t3);

        alert.setView(edittext);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //What ever you want to do with the value

                //OR
                String YouEditTextValue = edittext.getText().toString();
                pric.setText(YouEditTextValue);

                Upload upload = new Upload(nm,lk,cate,pric.getText().toString());
                pric.setText(" ");
                String uploadId = mDatabase_ecommerce.push().getKey();
                mDatabase_ecommerce.child(uploadId).setValue(upload);
                TextView vname = (TextView)findViewById(R.id.t2);
                TextView vlink = (TextView)findViewById(R.id.t1);
                vname.setText("");
                vlink.setText("");

                mDatabaseRef.child(selectedKey).removeValue();
                Toast.makeText(ImagesActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();


            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // what ever you want to do with No option.
            }
        });


        alert.show();
    }

    }
