package com.example.vehiclerentappadmin;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ScooterAdmin extends Fragment {

    public static final String SHARED_PREFS = "sharedPrefs";
    String mobileNumber;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    List<ScooterData> datalist;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    //  Button btn;
    SearchView searchView;
   ScooterAdapterAdmin adepter;

    public ScooterAdmin() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scooter_admin, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mobileNumber = sharedPreferences.getString("mobileNumber", "");

        fab = view.findViewById(R.id.scooter_fab);
        recyclerView = view.findViewById(R.id.scooter_RecycleView);
        // btn=findViewById(R.id.home);
        searchView = view.findViewById(R.id.scooter_search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setView(R.layout.progress);
        AlertDialog dialog = builder.create();
        //dialog.show();

        datalist = new ArrayList<>();

        adepter = new ScooterAdapterAdmin(getContext(), datalist);
        recyclerView.setAdapter(adepter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child(mobileNumber).child("SCOOTER");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                datalist.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    ScooterData dataclass = itemSnapshot.getValue(ScooterData.class);
                    dataclass.setScooterKey(itemSnapshot.getKey());
                    datalist.add(dataclass);
                }
                adepter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ScooterDetail.class);
                startActivity(intent);
            }
        });

        return view;
    }

    public void searchList(String text) {
        ArrayList<ScooterData> searchList = new ArrayList<>();
        for (ScooterData dataclass : datalist) {
            if (dataclass.getScooterName().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataclass);
            }
        }
        adepter.searchDataList(searchList);
        // adepter.notifyDataSetChanged();
        // recyclerView.setAdapter(adepter);
    }
}