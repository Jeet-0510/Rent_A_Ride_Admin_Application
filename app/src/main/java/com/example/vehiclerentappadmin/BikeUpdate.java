package com.example.vehiclerentappadmin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class BikeUpdate extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    ImageView updateBikeImage;
    Button updateBikeButton;
    EditText updateBikeDesc, updateBikeName, updateBikeLocation,updateBikePrice;
    String BikeName,BikeDesc,BikeLocation,BikePrice;
    String imageUrl;
    String BikeKey, oldImageURL;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String mobileNumber;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_update);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mobileNumber = sharedPreferences.getString("mobileNumber", "");

        updateBikeImage=findViewById(R.id.bikeupdate_image);
        updateBikeButton=findViewById(R.id.bikeupdate_btn);
        updateBikeDesc=findViewById(R.id.bikeupdate_desc);
        updateBikeName=findViewById(R.id.bikeupdate_name);
        updateBikeLocation=findViewById(R.id.bikeupdate_location);
        updateBikePrice=findViewById(R.id.bikeupdate_rupees);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            updateBikeImage.setImageURI(uri);
                            imageUrl = String.valueOf(uri);
                        } else {
                            Toast.makeText(BikeUpdate.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            Glide.with(this).load(bundle.getString("Bike_Image")).into(updateBikeImage);
            updateBikeName.setText(bundle.getString("Bike_Name"));
            updateBikeDesc.setText(bundle.getString("Bike_Desc"));
            updateBikeLocation.setText(bundle.getString("Bike_Location"));
            updateBikePrice.setText(bundle.getString("Bike_Price"));
            BikeKey=bundle.getString("Bike_Key");
            oldImageURL=bundle.getString("Bike_Image");
            uri = Uri.parse(oldImageURL);
            imageUrl= String.valueOf(uri);
            // carView_name.setText(bundle.getString("Car_Name"));

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child(mobileNumber).child("BIKE").child(BikeKey);
        updateBikeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        updateBikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
                Intent intent = new Intent(BikeUpdate.this, HomeAdmin.class);
                intent.putExtra("Fragment", "Bike");
                startActivity(intent);
            }
        });
    }

    public void updateData(){
        BikeName=updateBikeName.getText().toString().trim();
        BikeDesc=updateBikeDesc.getText().toString().trim();
        BikePrice=updateBikePrice.getText().toString().trim();
        BikeLocation=updateBikeLocation.getText().toString().trim();
        BikeData dataClass = new BikeData(BikeName,BikeDesc,BikeLocation,BikePrice, imageUrl);
        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(BikeUpdate.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BikeUpdate.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}