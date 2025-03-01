package com.codewizards.opportunitpo;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressWarnings("ALL")
public class UpdateProfile extends AppCompatActivity {
    CircleImageView img;
    Uri imgUri;
    ProgressBar progressBar;
    EditText nameEdt,mobileEdt,addressEdt,rollNoEdt,branchEdt,semesterEdt,xRollNoEdt,xSchoolEdt,xPercentageEdt,xiiRollNoEdt,xiiSchoolEdt,xiiPercentageEdt;
    Button continueBtn;
    final static int IMG_REQ_CODE=1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_details);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        img=findViewById(R.id.profile_img_update);
        nameEdt=findViewById(R.id.name_txt_update);
        mobileEdt=findViewById(R.id.mobile_txt_update);
        addressEdt=findViewById(R.id.address_txt_update);
        rollNoEdt=findViewById(R.id.roll_txt_update);
        branchEdt=findViewById(R.id.branch_txt_update);
        semesterEdt=findViewById(R.id.semester_txt_update);
        xRollNoEdt=findViewById(R.id.x_roll_txt_update);
        xSchoolEdt=findViewById(R.id.x_school_txt_update);
        xPercentageEdt=findViewById(R.id.x_percentage_txt_update);
        xiiRollNoEdt=findViewById(R.id.xii_roll_txt_update);
        xiiSchoolEdt=findViewById(R.id.xii_school_txt_update);
        xiiPercentageEdt=findViewById(R.id.xii_percentage_txt_update);
        continueBtn=findViewById(R.id.update_btn);
        progressBar=findViewById(R.id.progress_update);

        img.setOnClickListener(view -> {
            Intent imgGallery=new Intent(Intent.ACTION_PICK);
            imgGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(imgGallery,IMG_REQ_CODE);
        });

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(nameEdt.getText().toString())){
                    continueBtn.setClickable(true);
                    progressBar.setVisibility(View.GONE);
                    nameEdt.setError("Required");
                    Toast.makeText(UpdateProfile.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(mobileEdt.getText().toString())){
                    continueBtn.setClickable(true);
                    progressBar.setVisibility(View.GONE);
                    mobileEdt.setError("Required");
                    Toast.makeText(UpdateProfile.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(addressEdt.getText().toString())){
                    continueBtn.setClickable(true);
                    progressBar.setVisibility(View.GONE);
                    addressEdt.setError("Required");
                    Toast.makeText(UpdateProfile.this, "Enter Address", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(rollNoEdt.getText().toString())){
                    continueBtn.setClickable(true);
                    progressBar.setVisibility(View.GONE);
                    rollNoEdt.setError("Required");
                    Toast.makeText(UpdateProfile.this, "Enter Roll No", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(semesterEdt.getText().toString())){
                    continueBtn.setClickable(true);
                    progressBar.setVisibility(View.GONE);
                    semesterEdt.setError("Required");
                    Toast.makeText(UpdateProfile.this, "Enter Semester", Toast.LENGTH_SHORT).show();
                }
                else{
                    //hide keyboard on click
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.hideSoftInputFromWindow(view.getWindowToken(), 0);

                    String id= FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                    StorageReference storageRef= FirebaseStorage.getInstance().getReference().child("Uploads").child(id);

                    DatabaseReference ref=database.getReference().child("User").child(id);

                    ref.child("name").setValue(nameEdt.getText().toString());
                    ref.child("mobile").setValue(mobileEdt.getText().toString());
                    ref.child("address").setValue(addressEdt.getText().toString());
                    ref.child("rollNo").setValue(rollNoEdt.getText().toString());
                    ref.child("branch").setValue(branchEdt.getText().toString());
                    ref.child("semester").setValue(semesterEdt.getText().toString());
                    ref.child("xRollNo").setValue(xRollNoEdt.getText().toString());
                    ref.child("xSchool").setValue(xSchoolEdt.getText().toString());
                    ref.child("xPercentage").setValue(xPercentageEdt.getText().toString());
                    ref.child("xiiRollNo").setValue(xiiRollNoEdt.getText().toString());
                    ref.child("xiiSchool").setValue(xiiSchoolEdt.getText().toString());
                    ref.child("xiiPercentage").setValue(xiiPercentageEdt.getText().toString());

                    if(imgUri!=null){
                        storageRef.putFile(imgUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                if(task.isSuccessful()){
                                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            ref.child("profilePic").setValue(uri.toString());
                                        }
                                    });
                                }
                            }
                        });

                    }
                    Toast.makeText(UpdateProfile.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.VISIBLE);
                    onBackPressed();
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
                img.setImageURI(imgUri);
            }
        }
    }
}