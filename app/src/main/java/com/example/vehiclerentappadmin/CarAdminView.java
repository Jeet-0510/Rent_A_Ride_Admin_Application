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

public class CarAdminView extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    String mobileNumber;
    ImageView caraView_image;
    TextView caraView_name,caraView_desc,caraView_location,caraView_price;
    String key="";
    String imageUrl="";
    FloatingActionButton delete,edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_admin_view);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mobileNumber = sharedPreferences.getString("mobileNumber", "");

        caraView_image=findViewById(R.id.caradminv_image);
        caraView_name=findViewById(R.id.caradminv_name);
        caraView_desc=findViewById(R.id.caradminv_desc);
        caraView_location=findViewById(R.id.caradminv_location);
        caraView_price=findViewById(R.id.caradminv_rupees);
        delete=findViewById(R.id.caradminv_delete);
        edit=findViewById(R.id.caradminv_edit);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            Glide.with(this).load(bundle.getString("Car_Image")).into(caraView_image);
            caraView_name.setText(bundle.getString("Car_Name"));
            caraView_desc.setText(bundle.getString("Car_Desc"));
            caraView_location.setText(bundle.getString("Car_Location"));
            caraView_price.setText(bundle.getString("Car_Price"));
            key=bundle.getString("Car_Key");
            imageUrl=bundle.getString("Car_Image");
            // carView_name.setText(bundle.getString("Car_Name"));

        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Admin").child(mobileNumber).child("CAR").child(key);

                databaseReference.removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                        Intent intent = new Intent(getApplicationContext(), HomeAdmin.class);
                        intent.putExtra("Fragment", "Car");
                        startActivity(intent);
                        finish();
                        Toast.makeText(CarAdminView.this, "Delete Successfully!!!", Toast.LENGTH_SHORT).show();
                    }
                });
//                StorageReference storageReference=storage.getReferenceFromUrl(imageUrl);
//                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        databaseReference.child(key).removeValue();
//                        Toast.makeText(CarAdminView.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), HomeAdmin.class);
//                        intent.putExtra("Fragment", "Car");
//                        startActivity(intent);
//                        finish();
//                    }
//                });
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CarAdminView.this,CarUpdate.class)
                        .putExtra("Car_Name",caraView_name.getText().toString())
                        .putExtra("Car_Location",caraView_location.getText().toString())
                        .putExtra("Car_Desc",caraView_desc.getText().toString())
                        .putExtra("Car_Price",caraView_price.getText().toString())
                        .putExtra("Car_Image",imageUrl)
                        .putExtra("Car_Key",key);
                startActivity(intent);
            }
        });
    }
}
