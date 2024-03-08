package com.example.vehiclerentappadmin;

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

public class BicycleAdminView extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    String mobileNumber;
    ImageView bicycleaView_image;
    TextView bicycleaView_name, bicycleaView_desc, bicycleaView_location, bicycleaView_price;
    String key = "";
    String imageUrl = "";
    FloatingActionButton delete, edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle_admin_view);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mobileNumber = sharedPreferences.getString("mobileNumber", "");

        bicycleaView_image = findViewById(R.id.bicycleadminv_image);
        bicycleaView_name = findViewById(R.id.bicycleadminv_name);
        bicycleaView_desc = findViewById(R.id.bicycleadminv_desc);
        bicycleaView_location = findViewById(R.id.bicycleadminv_location);
        bicycleaView_price = findViewById(R.id.bicycleadminv_rupees);
        delete = findViewById(R.id.bicycleadminv_delete);
        edit = findViewById(R.id.bicycleadminv_edit);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Glide.with(this).load(bundle.getString("Bicycle_Image")).into(bicycleaView_image);
            bicycleaView_name.setText(bundle.getString("Bicycle_Name"));
            bicycleaView_desc.setText(bundle.getString("Bicycle_Desc"));
            bicycleaView_location.setText(bundle.getString("Bicycle_Location"));
            bicycleaView_price.setText(bundle.getString("Bicycle_Price"));
            key = bundle.getString("Bicycle_Key");
            imageUrl = bundle.getString("Bicycle_Image");
            // carView_name.setText(bundle.getString("Car_Name"));

        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child(mobileNumber).child("BICYCLE").child(key);

                databaseReference.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                        Intent intent = new Intent(getApplicationContext(), HomeAdmin.class);
                        intent.putExtra("Fragment", "Bicycle");
                        startActivity(intent);
                        finish();
                        Toast.makeText(BicycleAdminView.this, "Delete Successfully!!!", Toast.LENGTH_SHORT).show();
                    }
                });
//                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
//                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        databaseReference.child(key).removeValue();
//                        Toast.makeText(BicycleAdminView.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), HomeAdmin.class);
//                        intent.putExtra("Fragment", "Bicycle");
//                        startActivity(intent);
//                        finish();
//                    }
//                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BicycleAdminView.this, BicycleUpdate.class)
                        .putExtra("Bicycle_Name", bicycleaView_name.getText().toString())
                        .putExtra("Bicycle_Location", bicycleaView_location.getText().toString())
                        .putExtra("Bicycle_Desc", bicycleaView_desc.getText().toString())
                        .putExtra("Bicycle_Price", bicycleaView_price.getText().toString())
                        .putExtra("Bicycle_Image", imageUrl)
                        .putExtra("Bicycle_Key", key);
                startActivity(intent);
            }
        });
    }
}
