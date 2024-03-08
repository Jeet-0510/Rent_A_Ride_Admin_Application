package com.example.vehiclerentappadmin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ScooterAdminView extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    String mobileNumber;
    ImageView scooteraView_image;
    TextView scooteraView_name,scooteraView_desc,scooteraView_location,scooteraView_price;
    String key="";
    String imageUrl="";
    FloatingActionButton delete,edit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scooter_admin_view);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mobileNumber = sharedPreferences.getString("mobileNumber", "");

        scooteraView_image=findViewById(R.id.scooteradminv_image);
        scooteraView_name=findViewById(R.id.scooteradminv_name);
        scooteraView_desc=findViewById(R.id.scooteradminv_desc);
        scooteraView_location=findViewById(R.id.scooteradminv_location);
        scooteraView_price=findViewById(R.id.scooteradminv_rupees);
        delete=findViewById(R.id.scooteradminv_delete);
        edit=findViewById(R.id.scooteradminv_edit);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            Glide.with(this).load(bundle.getString("Scooter_Image")).into(scooteraView_image);
            scooteraView_name.setText(bundle.getString("Scooter_Name"));
            scooteraView_desc.setText(bundle.getString("Scooter_Desc"));
            scooteraView_location.setText(bundle.getString("Scooter_Location"));
            scooteraView_price.setText(bundle.getString("Scooter_Price"));
            key=bundle.getString("Scooter_Key");
            imageUrl=bundle.getString("Scooter_Image");
            // carView_name.setText(bundle.getString("Car_Name"));

        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Admin").child(mobileNumber).child("SCOOTER").child(key);

                databaseReference.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                        Intent intent = new Intent(getApplicationContext(), HomeAdmin.class);
                        intent.putExtra("Fragment", "Scooter");
                        startActivity(intent);
                        finish();
                        Toast.makeText(ScooterAdminView.this, "Delete Successfully!!!", Toast.LENGTH_SHORT).show();
                    }
                });
//                StorageReference storageReference=storage.getReferenceFromUrl(imageUrl);
//                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        databaseReference.child(key).removeValue();
//                        Toast.makeText(ScooterAdminView.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), HomeAdmin.class);
//                        intent.putExtra("Fragment", "Scooter");
//                        startActivity(intent);
//                        finish();
//                    }
//                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ScooterAdminView.this,ScooterUpdate.class)
                        .putExtra("Scooter_Name",scooteraView_name.getText().toString())
                        .putExtra("Scooter_Location",scooteraView_location.getText().toString())
                        .putExtra("Scooter_Desc",scooteraView_desc.getText().toString())
                        .putExtra("Scooter_Price",scooteraView_price.getText().toString())
                        .putExtra("Scooter_Image",imageUrl)
                        .putExtra("Scooter_Key",key);
                startActivity(intent);
            }
        });
    }
}