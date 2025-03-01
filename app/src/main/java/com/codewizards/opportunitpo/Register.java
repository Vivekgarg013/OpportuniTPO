package com.codewizards.opportunitpo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class Register extends AppCompatActivity {
    final static int IMG_REQ_CODE=10;
    CircleImageView profileImg;
    EditText nameTxt,emailTxt,phoneTxt,addressTxt,rollnoTxt,branchTxt,passTxt,rePassTxt,semesterTxt,xRollnoTxt,xSchoolTxt,xPercentageTxt,xiiRollnoTxt,xiiSchoolTxt,xiiPercentageTxt;
    Button registerBtn;
    ProgressBar progressbar;

    Uri imgUri;
    String imageUri;
    TextView loginBtnIntent;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    Intent toLogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();

        //btn to move to login window
        loginBtnIntent=findViewById(R.id.login_btn_register);

        //btn to move to main window
        registerBtn=findViewById(R.id.register_btn);

        profileImg=findViewById(R.id.profile_img_registration);
        nameTxt=findViewById(R.id.name_txt_register);
        emailTxt=findViewById(R.id.email_txt_register);
        phoneTxt=findViewById(R.id.mobile_txt_register);
        addressTxt=findViewById(R.id.address_txt_register);
        rollnoTxt=findViewById(R.id.roll_txt_register);
        branchTxt=findViewById(R.id.branch_txt_register);
        semesterTxt=findViewById(R.id.semester_txt_register);
        xRollnoTxt=findViewById(R.id.x_roll_txt_register);
        xSchoolTxt=findViewById(R.id.x_school_txt_register);
        xPercentageTxt=findViewById(R.id.x_percentage_txt_register);
        xiiRollnoTxt=findViewById(R.id.xii_roll_txt_register);
        xiiSchoolTxt=findViewById(R.id.xii_school_txt_register);
        xiiPercentageTxt=findViewById(R.id.xii_percentage_txt_register);
        passTxt=findViewById(R.id.password_txt_register);
        rePassTxt=findViewById(R.id.re_password_txt_register);
        progressbar=findViewById(R.id.progress_registration);

        toLogin=new Intent(Register.this, Login.class);

        //to take img from gallery
        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imgGallery=new Intent(Intent.ACTION_PICK);
                imgGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imgGallery,IMG_REQ_CODE);

            }
        });

        //to go to login activity
        loginBtnIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(toLogin);
                finish();
            }
        });

        //register button
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressbar.setVisibility(View.VISIBLE);
                registerBtn.setClickable(false);

                String name=nameTxt.getText().toString();
                String email=emailTxt.getText().toString();
                String phone=phoneTxt.getText().toString();
                String address=addressTxt.getText().toString();
                String rollNo=rollnoTxt.getText().toString();
                String branch=branchTxt.getText().toString();
                String semester=semesterTxt.getText().toString();
                String xRollno=xRollnoTxt.getText().toString();
                String xSchool=xSchoolTxt.getText().toString();
                String xPercentage=xPercentageTxt.getText().toString();
                String xiiRollno=xiiRollnoTxt.getText().toString();
                String xiiSchool=xiiSchoolTxt.getText().toString();
                String xiiPercentage=xiiPercentageTxt.getText().toString();

                String pass=passTxt.getText().toString();
                String rePass=rePassTxt.getText().toString();
                String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String emailPattern1="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";

                if(TextUtils.isEmpty(name)){
                    registerBtn.setClickable(true);
                    progressbar.setVisibility(View.GONE);
                    nameTxt.setError("Required");
                    Toast.makeText(Register.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(email)){
                    registerBtn.setClickable(true);
                    progressbar.setVisibility(View.GONE);
                    emailTxt.setError("Required");
                    Toast.makeText(Register.this, "Enter E-Mail", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(phone)){
                    registerBtn.setClickable(true);
                    progressbar.setVisibility(View.GONE);
                    phoneTxt.setError("Required");
                    Toast.makeText(Register.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(address)){
                    registerBtn.setClickable(true);
                    progressbar.setVisibility(View.GONE);
                    addressTxt.setError("Required");
                    Toast.makeText(Register.this, "Enter Address", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(rollNo)){
                    registerBtn.setClickable(true);
                    progressbar.setVisibility(View.GONE);
                    rollnoTxt.setError("Required");
                    Toast.makeText(Register.this, "Enter Roll No.", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(branch)){
                    registerBtn.setClickable(true);
                    progressbar.setVisibility(View.GONE);
                    branchTxt.setError("Required");
                    Toast.makeText(Register.this, "Enter branch", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(pass)){
                    registerBtn.setClickable(true);
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(!pass.equals(rePass)){
                    registerBtn.setClickable(true);
                    progressbar.setVisibility(View.GONE);
                    rePassTxt.setError("Password not matched");
                    Toast.makeText(Register.this, "Password not matched", Toast.LENGTH_SHORT).show();
                }
                else if(!email.matches(emailPattern) && !email.matches(emailPattern1)){
                    registerBtn.setClickable(true);
                    progressbar.setVisibility(View.GONE);
                    emailTxt.setError("Enter a valid E-Mail Address");
                    Toast.makeText(Register.this, "Invalid E-Mail", Toast.LENGTH_SHORT).show();
                }
                else if(pass.length()<6){
                    registerBtn.setClickable(true);
                    progressbar.setVisibility(View.GONE);
                    passTxt.setError("Less than Six characters");
                    Toast.makeText(Register.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    //hide keyboard on click
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    //creating user
                    auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String id=task.getResult().getUser().getUid();
                                DatabaseReference databaseRef=database.getReference().child("User").child(id);
                                StorageReference storageRef=storage.getReference().child("Uploads").child(id);

                                if(imgUri!=null){
                                    //adding image to storage
                                    storageRef.putFile(imgUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            if(task.isSuccessful()){
                                                //to take uri of uploaded image
                                                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        imageUri=uri.toString();
                                                        Users user=new Users(id,imageUri,name,email,phone,address,rollNo,branch,semester,xRollno,xSchool,xPercentage,xiiRollno,xiiSchool,xiiPercentage,pass);
                                                        //to add data into realtime database
                                                        databaseRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if(task.isSuccessful()){
                                                                    startActivity(toLogin);
                                                                    Toast.makeText(Register.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                                                    progressbar.setVisibility(View.GONE);
                                                                    registerBtn.setClickable(true);
                                                                    finish();
                                                                }
                                                                else{
                                                                    //error while adding in realtime database
                                                                    progressbar.setVisibility(View.GONE);
                                                                    auth.getCurrentUser().delete();
                                                                    Toast.makeText(Register.this, "Error in creating user ", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                            else{//error while uploading data in storage
                                                registerBtn.setClickable(true);
                                                progressbar.setVisibility(View.GONE);
                                                auth.getCurrentUser().delete();
                                                Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                                //if image is not selected
                                else{
                                    //default image
                                    String imageUri="https://firebasestorage.googleapis.com/v0/b/wedrivers-7d7dd.appspot.com/o/defaultprofie.png?alt=media&token=a0dd7d4b-2fb0-41a1-ba6c-298e4d89b202";
                                    Users user=new Users(id,imageUri,name,email,phone,address,rollNo,branch,semester,xRollno,xSchool,xPercentage,xiiRollno,xiiSchool,xiiPercentage,pass);
                                    databaseRef.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                startActivity(toLogin);
                                                Toast.makeText(Register.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                                                registerBtn.setClickable(true);
                                                progressbar.setVisibility(View.GONE);
                                                finish();
                                            }
                                            else{
                                                registerBtn.setClickable(true);
                                                progressbar.setVisibility(View.GONE);
                                                auth.getCurrentUser().delete();
                                                Toast.makeText(Register.this, "Error in creating user ", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                }
                            }
                            else{//if error occurred while inserting data in firebase
                                registerBtn.setClickable(true);
                                progressbar.setVisibility(View.GONE);
                                Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==IMG_REQ_CODE){//inserting image taken from gallery
                imgUri=data.getData();
                profileImg.setImageURI(imgUri);
            }
        }
    }
}