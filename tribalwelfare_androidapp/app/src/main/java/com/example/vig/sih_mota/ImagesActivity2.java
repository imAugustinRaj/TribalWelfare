package com.example.vig.sih_mota;



import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.webkit.WebView;
import android.widget.ProgressBar;

import android.widget.Toast;


import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;


import java.util.ArrayList;
import java.util.List;

public class ImagesActivity2 extends AppCompatActivity implements ImageAdapter2.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ImageAdapter2 mAdapter;

    private ProgressBar mProgressCircle;

    private FirebaseStorage mStorage;


    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    DatabaseReference mDatabase_ecommerce = FirebaseDatabase.getInstance().getReference("sale");

    private List<Download> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images2);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProgressCircle = findViewById(R.id.progress_circle);

        mUploads = new ArrayList<>();

        mAdapter = new ImageAdapter2(ImagesActivity2.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(ImagesActivity2.this);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("sale");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Download upload = postSnapshot.getValue(Download.class);
                    upload.setKey(postSnapshot.getKey());
                    mUploads.add(upload);
                }

                mAdapter.notifyDataSetChanged();

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImagesActivity2.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);



            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "Item Number " + position + " Added To Cart", Toast.LENGTH_SHORT).show();
        LottieAnimationView lottieAnimationView = (LottieAnimationView)findViewById(R.id.animation_view);
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LottieAnimationView lottieAnimationView = (LottieAnimationView)findViewById(R.id.animation_view);
                lottieAnimationView.cancelAnimation();
                lottieAnimationView.setVisibility(View.INVISIBLE);
            }
        }, 2000);
    }

    @Override
    public void onWhatEverClick(int position) {
        Toast.makeText(this, "Whatever click at position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDatabaseRef.removeEventListener(mDBListener);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
