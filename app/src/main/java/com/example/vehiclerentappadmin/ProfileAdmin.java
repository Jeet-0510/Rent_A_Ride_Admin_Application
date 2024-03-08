package com.example.vehiclerentappadmin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class ProfileAdmin extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    ImageView imageView;
    TextInputEditText name,email,phoneNo,address;
    FloatingActionButton floatingActionButton;
    Button confirm;
    Uri uri = null;
    ProfileData dataclass;
    DatabaseReference databaseReference;
    ValueEventListener valueEventListener;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_admin);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        imageView =findViewById(R.id.image_view);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phoneNo=findViewById(R.id.phoneno);
        address=findViewById(R.id.address);
        confirm=findViewById(R.id.update);
        floatingActionButton = findViewById(R.id.floating_action);

        String mobileNo = sharedPreferences.getString("mobileNumber","");

        phoneNo.setText(mobileNo);
        phoneNo.setEnabled(false);

        databaseReference=FirebaseDatabase.getInstance().getReference("Admin").child(mobileNo).child("Profile");
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                        dataclass = itemSnapshot.getValue(ProfileData.class);
                        name.setText(dataclass.getName());
                        email.setText(dataclass.getEmail());
                        address.setText(dataclass.getAddress());
                        Glide.with(getApplicationContext())
                                .load(dataclass.getPhoto())
                                .into(imageView);
                        uri = Uri.parse(dataclass.getPhoto());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        floatingActionButton.setOnClickListener(v -> ImagePicker.with(ProfileAdmin.this).crop().compress(1024).maxResultSize(1080, 1080).start());

//        imageView.setOnClickListener(v -> {
//            // Create a dialog to show the enlarged image
//            AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//            builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.dismiss();
//                }
//            });
//
//            // Create a parent layout to hold the ImageView
//            LinearLayout parentLayout = new LinearLayout(getApplicationContext());
//            parentLayout.setLayoutParams(new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT));
//            parentLayout.setGravity(Gravity.CENTER);
//
//            // Create the ImageView and set its image resource or URI
//            ImageView enlargedImageView = new ImageView(getApplicationContext());
//            enlargedImageView.setLayoutParams(new LinearLayout.LayoutParams(
//                    1000, // Set width in pixels
//                    1000  // Set height in pixels
//            ));
//            if (uri == null) {
//                enlargedImageView.setImageResource(R.drawable.person1);
//                //imageView.setImageResource(R.drawable.person1);
//            } else {
//                enlargedImageView.setImageURI(uri);
//            }
//
//            // Add the ImageView to the parent layout
//            parentLayout.addView(enlargedImageView);
//
//            // Set the parent layout as the view for the dialog
//            builder.setView(parentLayout);
//
//            AlertDialog dialog = builder.create();
//            dialog.show();
//
//        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=name.getText().toString();
                String Email=email.getText().toString();
                String Phone=phoneNo.getText().toString();
                String Address=address.getText().toString();
                String Image=uri.toString();
                if(Name.isEmpty() || Email.isEmpty() || Phone.isEmpty() || Address.isEmpty() || Image.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please fill the required detail",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    save();
                    name.setText(Name);
                    email.setText(Email);
                    phoneNo.setText(Phone);
                    address.setText(Address);
                    Glide.with(getApplicationContext())
                            .load(Image)
                            .into(imageView);
                }

            }
        });
    }
    public void save()
    {
        String uName=name.getText().toString();
        String uEmail=email.getText().toString();
        String uPhone=phoneNo.getText().toString();
        String uAddress=address.getText().toString();
        String uImage= uri.toString();

        ProfileData user=new ProfileData(uName,uImage,uEmail,uPhone,uAddress);
        FirebaseDatabase.getInstance().getReference("Admin").child(uPhone).child("Profile").child("Data")
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Saved Successful",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        uri = data.getData();
        if (uri!=null) {
            imageView.setImageURI(uri);
        }
    }
}