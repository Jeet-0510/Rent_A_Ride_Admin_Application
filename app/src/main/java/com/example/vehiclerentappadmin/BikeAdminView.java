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

public class BikeAdminView extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    String mobileNumber;
    ImageView bikeaView_image;
    TextView bikeaView_name,bikeaView_desc,bikeaView_location,bikeaView_price;
    String key="";
    String imageUrl="";
    FloatingActionButton delete,edit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_admin_view);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mobileNumber = sharedPreferences.getString("mobileNumber", "");

        bikeaView_image=findViewById(R.id.bikeadminv_image);
        bikeaView_name=findViewById(R.id.bikeadminv_name);
        bikeaView_desc=findViewById(R.id.bikeadminv_desc);
        bikeaView_location=findViewById(R.id.bikeadminv_location);
        bikeaView_price=findViewById(R.id.bikeadminv_rupees);
        delete=findViewById(R.id.bikeadminv_delete);
        edit=findViewById(R.id.bikeadminv_edit);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            Glide.with(this).load(bundle.getString("Bike_Image")).into(bikeaView_image);
            bikeaView_name.setText(bundle.getString("Bike_Name"));
            bikeaView_desc.setText(bundle.getString("Bike_Desc"));
            bikeaView_location.setText(bundle.getString("Bike_Location"));
            bikeaView_price.setText(bundle.getString("Bike_Price"));
            key=bundle.getString("Bike_Key");
            imageUrl=bundle.getString("Bike_Image");
            // carView_name.setText(bundle.getString("Car_Name"));

        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Admin").child(mobileNumber).child("BIKE").child(key);

                databaseReference.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                        Intent intent = new Intent(getApplicationContext(), HomeAdmin.class);
                        intent.putExtra("Fragment", "Bike");
                        startActivity(intent);
                        finish();
                        Toast.makeText(BikeAdminView.this, "Delete Successfully!!!", Toast.LENGTH_SHORT).show();
                    }
                });
                //FirebaseStorage storage=FirebaseStorage.getInstance();

                //StorageReference storageReference=storage.getReferenceFromUrl(imageUrl);
                /*storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        databaseReference.child(key).removeValue();
                        Toast.makeText(BikeAdminView.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeAdmin.class);
                        intent.putExtra("Fragment", "Bike");
                        startActivity(intent);
                        finish();
                    }
                });*/
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(BikeAdminView.this,BikeUpdate.class)
                        .putExtra("Bike_Name",bikeaView_name.getText().toString())
                        .putExtra("Bike_Location",bikeaView_location.getText().toString())
                        .putExtra("Bike_Desc",bikeaView_desc.getText().toString())
                        .putExtra("Bike_Price",bikeaView_price.getText().toString())
                        .putExtra("Bike_Image",imageUrl)
                        .putExtra("Bike_Key",key);
                startActivity(intent);
            }
        });
    }
}