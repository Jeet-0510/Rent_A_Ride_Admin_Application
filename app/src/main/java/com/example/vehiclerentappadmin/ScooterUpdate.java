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

public class ScooterUpdate extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    String mobileNumber;
    ImageView updateScooterImage;
    Button updateScooterButton;
    EditText updateScooterDesc, updateScooterName, updateScooterLocation,updateScooterPrice;
    String ScooterName,ScooterDesc,ScooterLocation,ScooterPrice;
    String imageUrl;
    String ScooterKey, oldImageURL;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scooter_update);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mobileNumber = sharedPreferences.getString("mobileNumber", "");

        updateScooterImage=findViewById(R.id.scooterupdate_image);
        updateScooterButton=findViewById(R.id.scooterupdate_btn);
        updateScooterDesc=findViewById(R.id.scooterupdate_desc);
        updateScooterName=findViewById(R.id.scooterupdate_name);
        updateScooterLocation=findViewById(R.id.scooterupdate_location);
        updateScooterPrice=findViewById(R.id.scooterupdate_rupees);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            updateScooterImage.setImageURI(uri);
                            imageUrl = String.valueOf(uri);
                        } else {
                            Toast.makeText(ScooterUpdate.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            Glide.with(this).load(bundle.getString("Scooter_Image")).into(updateScooterImage);
            updateScooterName.setText(bundle.getString("Scooter_Name"));
            updateScooterDesc.setText(bundle.getString("Scooter_Desc"));
            updateScooterLocation.setText(bundle.getString("Scooter_Location"));
            updateScooterPrice.setText(bundle.getString("Scooter_Price"));
            ScooterKey=bundle.getString("Scooter_Key");
            oldImageURL=bundle.getString("Scooter_Image");
            uri = Uri.parse(oldImageURL);
            imageUrl= String.valueOf(uri);
            // carView_name.setText(bundle.getString("Car_Name"));

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child(mobileNumber).child("SCOOTER").child(ScooterKey);
        updateScooterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        updateScooterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
                Intent intent = new Intent(ScooterUpdate.this, HomeAdmin.class);
                intent.putExtra("Fragment", "Scooter");
                startActivity(intent);
            }
        });
    }

    public void updateData(){
        ScooterName=updateScooterName.getText().toString().trim();
        ScooterDesc=updateScooterDesc.getText().toString().trim();
        ScooterPrice=updateScooterPrice.getText().toString().trim();
        ScooterLocation=updateScooterLocation.getText().toString().trim();
        ScooterData dataClass = new ScooterData(ScooterName,ScooterDesc,ScooterLocation,ScooterPrice, imageUrl);
        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(ScooterUpdate.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ScooterUpdate.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}