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

public class BicycleUpdate extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    ImageView updateBicycleImage;
    Button updateBicycleButton;
    EditText updateBicycleDesc, updateBicycleName, updateBicycleLocation,updateBicyclePrice;
    String BicycleName,BicycleDesc,BicycleLocation,BicyclePrice;
    String imageUrl;
    String BicycleKey, oldImageURL, mobileNumber;
    Uri uri=null;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicycle_update);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mobileNumber = sharedPreferences.getString("mobileNumber", "");

        updateBicycleImage=findViewById(R.id.bicycleupdate_image);
        updateBicycleButton=findViewById(R.id.bicycleupdate_btn);
        updateBicycleDesc=findViewById(R.id.bicycleupdate_desc);
        updateBicycleName=findViewById(R.id.bicycleupdate_name);
        updateBicycleLocation=findViewById(R.id.bicycleupdate_location);
        updateBicyclePrice=findViewById(R.id.bicycleupdate_rupees);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            updateBicycleImage.setImageURI(uri);
                            imageUrl = String.valueOf(uri);
                        } else {
                            Toast.makeText(BicycleUpdate.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            Glide.with(this).load(bundle.getString("Bicycle_Image")).into(updateBicycleImage);
            updateBicycleName.setText(bundle.getString("Bicycle_Name"));
            updateBicycleDesc.setText(bundle.getString("Bicycle_Desc"));
            updateBicycleLocation.setText(bundle.getString("Bicycle_Location"));
            updateBicyclePrice.setText(bundle.getString("Bicycle_Price"));
            BicycleKey=bundle.getString("Bicycle_Key");
            oldImageURL=bundle.getString("Bicycle_Image");
           // imageUrl = oldImageURL;
            uri = Uri.parse(oldImageURL);
            imageUrl= String.valueOf(uri);
            // carView_name.setText(bundle.getString("Car_Name"));

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child(mobileNumber).child("BICYCLE").child(BicycleKey);
        updateBicycleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        updateBicycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveData();
                updateData();
                Intent intent = new Intent(BicycleUpdate.this, HomeAdmin.class);
                intent.putExtra("Fragment", "Bicycle");
                startActivity(intent);
            }
        });
    }
    public void updateData(){
        BicycleName=updateBicycleName.getText().toString().trim();
        BicycleDesc=updateBicycleDesc.getText().toString().trim();
        BicyclePrice=updateBicyclePrice.getText().toString().trim();
        BicycleLocation=updateBicycleLocation.getText().toString().trim();
        BicycleData dataClass = new BicycleData(BicycleName,BicycleDesc,BicycleLocation,BicyclePrice, imageUrl);
        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(BicycleUpdate.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BicycleUpdate.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}