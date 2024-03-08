package com.example.vehiclerentappadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BookingAdmin extends AppCompatActivity {

    RecyclerView recyclerView;
    String mobileNumber, admin;
    List<BookingData> data;
    List<String> adminNumber, adminNumber1, vehicleCatagory;
    //    DatabaseReference databaseReference;
//    ValueEventListener eventListener;
    DatabaseReference databaseReference2, databaseReference0, databaseReference1, databaseReference3, databaseReference4;
    ValueEventListener eventListener2, eventListener0, eventListener1, eventListener3, eventListener4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_admin);

        recyclerView = findViewById(R.id.bookingRecycleView);
        mobileNumber = getIntent().getStringExtra("AdminNumber");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        data = new ArrayList<>();
        adminNumber = new ArrayList<>();
//        adminNumber1 = new ArrayList<>();
        vehicleCatagory = new ArrayList<>();
//        BookingAdapter adapter = new BookingAdapter(getApplicationContext(),data);
//        recyclerView.setAdapter(adapter);

        databaseReference1 = FirebaseDatabase.getInstance().getReference("Booking").child("BICYCLE").child(mobileNumber);
        databaseReference2 = FirebaseDatabase.getInstance().getReference("Booking").child("CAR").child(mobileNumber);
        databaseReference3 = FirebaseDatabase.getInstance().getReference("Booking").child("SCOOTER").child(mobileNumber);
        databaseReference4 = FirebaseDatabase.getInstance().getReference("Booking").child("BIKE").child(mobileNumber);

        ReadData(new FirebaseCall() {
            @Override
            public void onCalledBack(List<BookingData> dataList) {
                //data.addAll(dataList);
                Log.d("Data1", dataList.toString());
                BookingAdapter adapter = new BookingAdapter(getApplicationContext(), dataList);
                recyclerView.setAdapter(adapter);
            }
        });
        //1
        /*databaseReference = FirebaseDatabase.getInstance().getReference("Booking").child("BICYCLE").child(adminNumber);
        //dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Booking bookingData = itemSnapshot.getValue(Booking.class);
                    bookingData.setKey(itemSnapshot.getKey());
                    data.add(bookingData);
                }
                adapter.notifyDataSetChanged();
                //dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //dialog.dismiss();
            }
        });

        //2
        databaseReference = FirebaseDatabase.getInstance().getReference("Booking").child("CAR").child(adminNumber);
        //dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Booking bookingData = itemSnapshot.getValue(Booking.class);
                    bookingData.setKey(itemSnapshot.getKey());
                    data.add(bookingData);
                }
                adapter.notifyDataSetChanged();
                //dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //dialog.dismiss();
            }
        });

        //3
        databaseReference = FirebaseDatabase.getInstance().getReference("Booking").child("SCOOTER").child(adminNumber);
        //dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Booking bookingData = itemSnapshot.getValue(Booking.class);
                    bookingData.setKey(itemSnapshot.getKey());
                    data.add(bookingData);
                }
                adapter.notifyDataSetChanged();
                //dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //dialog.dismiss();
            }
        });

        //4
        databaseReference = FirebaseDatabase.getInstance().getReference("Booking").child("BIKE").child(adminNumber);
        //dialog.show();
        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Booking bookingData = itemSnapshot.getValue(Booking.class);
                    bookingData.setKey(itemSnapshot.getKey());
                    data.add(bookingData);
                }
                adapter.notifyDataSetChanged();
                //dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //dialog.dismiss();
            }
        });*/
    }

    private void ReadData(FirebaseCall firebaseCall) {

        //1
        eventListener1 = databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        BookingData dataclass = itemSnapshot.getValue(BookingData.class);
                        if (dataclass != null) {
                            dataclass.setKey(itemSnapshot.getKey());
                            data.add(dataclass);
                        }
                    }
                    firebaseCall.onCalledBack(data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //databaseReference0.addValueEventListener(eventListener);

        //2
        eventListener2 = databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        BookingData dataclass = itemSnapshot.getValue(BookingData.class);
                        if (dataclass != null) {
                            dataclass.setKey(itemSnapshot.getKey());
                            data.add(dataclass);

                        }
                    }
                    firebaseCall.onCalledBack(data);
//                                adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //3
        eventListener3 = databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        BookingData dataclass = itemSnapshot.getValue(BookingData.class);
                        if (dataclass != null) {
                            dataclass.setKey(itemSnapshot.getKey());
                            data.add(dataclass);

                        }
                    }
                    firebaseCall.onCalledBack(data);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //4
        eventListener4 = databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        BookingData dataclass = itemSnapshot.getValue(BookingData.class);
                        if (dataclass != null) {
                            dataclass.setKey(itemSnapshot.getKey());
                            data.add(dataclass);

                        }
                    }
                    firebaseCall.onCalledBack(data);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private interface FirebaseCall {
        void onCalledBack(List<BookingData> dataList);
    }
}