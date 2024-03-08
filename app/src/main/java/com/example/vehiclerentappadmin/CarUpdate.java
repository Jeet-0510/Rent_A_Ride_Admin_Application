package com.example.vehiclerentappadmin;

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

public class CarUpdate extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    String mobileNumber;
    ImageView updateCarImage;
    Button updateCarButton;
    EditText updateCarDesc, updateCarName, updateCarLocation,updateCarPrice;
    String CarName,CarDesc,CarLocation,CarPrice;
    String imageUrl;
    String CarKey, oldImageURL;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_update);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        mobileNumber = sharedPreferences.getString("mobileNumber", "");

        updateCarImage=findViewById(R.id.carupdate_image);
        updateCarButton=findViewById(R.id.carupdate_btn);
        updateCarDesc=findViewById(R.id.carupdate_desc);
        updateCarName=findViewById(R.id.carupdate_name);
        updateCarLocation=findViewById(R.id.carupdate_location);
        updateCarPrice=findViewById(R.id.carupdate_rupees);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            updateCarImage.setImageURI(uri);
                            imageUrl = String.valueOf(uri);
                        } else {
                            Toast.makeText(CarUpdate.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null)
        {
            Glide.with(this).load(bundle.getString("Car_Image")).into(updateCarImage);
            updateCarName.setText(bundle.getString("Car_Name"));
            updateCarDesc.setText(bundle.getString("Car_Desc"));
            updateCarLocation.setText(bundle.getString("Car_Location"));
            updateCarPrice.setText(bundle.getString("Car_Price"));
            CarKey=bundle.getString("Car_Key");
            oldImageURL=bundle.getString("Car_Image");
            uri = Uri.parse(oldImageURL);
            imageUrl= String.valueOf(uri);
            // carView_name.setText(bundle.getString("Car_Name"));

        }
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child(mobileNumber).child("CAR").child(CarKey);
        updateCarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        updateCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
                Intent intent = new Intent(CarUpdate.this, HomeAdmin.class);
                intent.putExtra("Fragment", "Car");
                startActivity(intent);
            }
        });
    }
    public void updateData(){
        CarName=updateCarName.getText().toString().trim();
        CarDesc=updateCarDesc.getText().toString().trim();
        CarPrice=updateCarPrice.getText().toString().trim();
        CarLocation=updateCarLocation.getText().toString().trim();
        CarData dataClass = new CarData(CarName,CarDesc,CarLocation,CarPrice, imageUrl);
        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                    reference.delete();
                    Toast.makeText(CarUpdate.this, "Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CarUpdate.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
