package com.codewizards.opportunitpo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {
    TextView name,email,address,mobile,rollNo,branch,semester,xRollNo,xSchool,xPercentage,xiiRollNo,xiiSchool,xiiPercentage,profileTxt;
    CircleImageView profilePic,img;
    Uri imgUri;

    Button updateBtn;
    String userIdStr,profilePicStr,nameTxt,emailTxt,addressTxt,mobileTxt,rollNoTxt,branchTxt,semesterTxt,xRollNoTxt,xiiSchoolTxt,xSchoolTxt,xPercentageTxt,xiiRollNoTxt,xiiPercentageTxt;
    final static int IMG_REQ_CODE=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        Intent intent=getIntent();
        userIdStr=intent.getStringExtra("id");
        profilePicStr=intent.getStringExtra("profilePic");
        nameTxt=intent.getStringExtra("name");
        emailTxt=intent.getStringExtra("email");
        addressTxt=intent.getStringExtra("address");
        mobileTxt=intent.getStringExtra("mobile");
        rollNoTxt=intent.getStringExtra("rollNo");
        branchTxt=intent.getStringExtra("branch");
        semesterTxt=intent.getStringExtra("semester");
        xRollNoTxt=intent.getStringExtra("xRollNo");
        xSchoolTxt=intent.getStringExtra("xSchool");
        xPercentageTxt=intent.getStringExtra("xPercentage");
        xiiRollNoTxt=intent.getStringExtra("xiiRollNo");
        xiiSchoolTxt=intent.getStringExtra("xiiSchool");
        xiiPercentageTxt=intent.getStringExtra("xiiPercentage");

//        profileTxt=findViewById(R.id.profile_txt_vw);
        profilePic=findViewById(R.id.profile_img);
        name=findViewById(R.id.name_txt);
        email=findViewById(R.id.email_txt);
        address=findViewById(R.id.address_txt);
        mobile=findViewById(R.id.mobile_txt);
        rollNo=findViewById(R.id.roll_txt);
        branch=findViewById(R.id.branch_txt);
        semester=findViewById(R.id.semester_txt);
        xSchool=findViewById(R.id.x_school_txt);
        xRollNo=findViewById(R.id.x_roll_txt);
        xPercentage=findViewById(R.id.x_percentage_txt);
        xiiRollNo=findViewById(R.id.xii_roll_txt);
        xiiSchool=findViewById(R.id.xii_school_txt);
        xiiPercentage=findViewById(R.id.xii_percentage_txt);
        updateBtn=findViewById(R.id.update_btn);
        updateBtn.setVisibility(View.VISIBLE);

        Picasso.get().load(profilePicStr).into(profilePic);
        name.setText(nameTxt);
        email.setText(emailTxt);
        address.setText(addressTxt);
        mobile.setText(mobileTxt);
        rollNo.setText(rollNoTxt);
        branch.setText(branchTxt);
        semester.setText(semesterTxt);
        xRollNo.setText(xRollNoTxt);
        xSchool.setText(xSchoolTxt);
        xPercentage.setText(xPercentageTxt);
        xiiRollNo.setText(xiiRollNoTxt);
        xiiSchool.setText(xiiSchoolTxt);
        xiiPercentage.setText(xiiPercentageTxt);


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iUpdate=new Intent(UserProfile.this,UpdateProfile.class);
                startActivity(iUpdate);
                finish();
            }
        });
        
    }
}