package com.codewizards.opportunitpo;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    String id,name,email,address,rollNo,branch,semester,xRollNo,xSchool,xPercentage,xiiRollNo,xiiSchool,xiiPercentage,pic;
    DrawerLayout drawer;
    NavigationView navigation;
    Toolbar tb;
    TextView userScore, usernameNavigation, usernameDashboard, userMobileNumber;
    CircleImageView userProfilePic;
    ProgressBar progressBar;

    CircleImageView upcomingCompaniesBtn, myApplicationBtn, resultsBtn, coursesBtn, queriesBtn, pastCompaniesBtn;
    ImageButton announcementsBtn;
    MenuItem profile, about, logout;

    FirebaseAuth auth;
    FirebaseDatabase database;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);


        tb = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        progressBar = findViewById(R.id.main_window_progress);

        //navigation
        setSupportActionBar(tb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, tb, R.string.open_drawer, R.string.close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        toggle.syncState();

        //user details
        usernameDashboard = findViewById(R.id.user_name_dashboard);

        //finding text view from navigation header
        userMobileNumber = navigation.getHeaderView(0).findViewById(R.id.user_mob_naviagtion);
        usernameNavigation = navigation.getHeaderView(0).findViewById(R.id.user_name_navigation);
        userProfilePic = navigation.getHeaderView(0).findViewById(R.id.user_profile_navigation);
        user = new Users();


        //intent buttons
        announcementsBtn = findViewById(R.id.announcement_btn);
        upcomingCompaniesBtn = findViewById(R.id.upcoming_companies_btn);
        myApplicationBtn = findViewById(R.id.my_application_btn);
        resultsBtn = findViewById(R.id.results_btn);
        pastCompaniesBtn = findViewById(R.id.past_companies_btn);
        queriesBtn = findViewById(R.id.queries_btn);
        coursesBtn = findViewById(R.id.courses_btn);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        //Move to main window if not logged in
        if (auth.getCurrentUser() == null) {
            Intent iLogin = new Intent(MainActivity.this, Login.class);
            startActivity(iLogin);
            finish();
        }


        DatabaseReference databaseRef=database.getReference().child("User").child(auth.getUid());
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MainActivity.this.id=snapshot.child("userId").getValue().toString();
                MainActivity.this.name=snapshot.child("name").getValue().toString();
                MainActivity.this.email=snapshot.child("email").getValue().toString();
                MainActivity.this.address=snapshot.child("address").getValue().toString();
                MainActivity.this.rollNo=snapshot.child("rollNo").getValue().toString();
                MainActivity.this.branch=snapshot.child("branch").getValue().toString();
                MainActivity.this.semester=snapshot.child("semester").getValue().toString();
                MainActivity.this.xRollNo=snapshot.child("xRollNo").getValue().toString();
                MainActivity.this.xSchool=snapshot.child("xSchool").getValue().toString();
                MainActivity.this.xPercentage=snapshot.child("xPercentage").getValue().toString();
                MainActivity.this.xiiRollNo=snapshot.child("xiiRollNo").getValue().toString();
                MainActivity.this.xiiSchool=snapshot.child("xiiSchool").getValue().toString();
                MainActivity.this.xiiPercentage=snapshot.child("xiiPercentage").getValue().toString();
                MainActivity.this.pic=snapshot.child("profilePic").getValue().toString();

                String[] name=snapshot.child("name").getValue().toString().split(" ");

                user.name=MainActivity.this.name;
                user.mobile=snapshot.child("mobile").getValue().toString();
                user.profilePic=MainActivity.this.pic;

                usernameNavigation.setText(user.name);
                userMobileNumber.setText(user.mobile);
                usernameDashboard.setText("Hii "+name[0]+",");
                Picasso.get().load(user.profilePic).into(userProfilePic);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //menu item click event
        profile=navigation.getMenu().findItem(R.id.profile_btn);
        about=navigation.getMenu().findItem(R.id.about_us_btn);
        logout=navigation.getMenu().findItem(R.id.logout_btn);

        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Intent iProfile=new Intent(MainActivity.this,UserProfile.class);
                iProfile.putExtra("id",id);
                iProfile.putExtra("name",name);
                iProfile.putExtra("email",email);
                iProfile.putExtra("address",address);
                iProfile.putExtra("mobile",user.mobile);
                iProfile.putExtra("rollNo",rollNo);
                iProfile.putExtra("branch",branch);
                iProfile.putExtra("semester",semester);
                iProfile.putExtra("xRollNo",xRollNo);
                iProfile.putExtra("xSchool",xSchool);
                iProfile.putExtra("xPercentage",xPercentage);
                iProfile.putExtra("xiiRollNo",xiiRollNo);
                iProfile.putExtra("xiiSchool",xiiSchool);
                iProfile.putExtra("xiiPercentage",xiiPercentage);
                iProfile.putExtra("profilePic",pic);

                startActivity(iProfile);
                onBackPressed();
                return true;
            }
        });
        about.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {

                Intent iAbout=new Intent(MainActivity.this,AboutUs.class);
                startActivity(iAbout);
                onBackPressed();
                return true;
            }
        });
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                Dialog logoutDialog= new Dialog(MainActivity.this);
                logoutDialog.setContentView(R.layout.logout_dialog);
                logoutDialog.show();

                Button logoutBtn,cancelBtn;
                logoutBtn=logoutDialog.findViewById(R.id.logout_btn_dialog);
                cancelBtn=logoutDialog.findViewById(R.id.cancel_btn_dialog);

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        logoutDialog.dismiss();
                    }
                });
                logoutBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        auth.signOut();
                        logoutDialog.dismiss();
                        Intent iLogin=new Intent(MainActivity.this, Login.class);
                        startActivity(iLogin);
                        finish();
                    }
                });
                onBackPressed();
                return true;
            }
        });


//        vehicleAssistanceBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri gmmIntentUri = Uri.parse("geo:0,0?q=Car Repair");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
//            }
//        });

//        needDirectionBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iNeedDirection=new Intent(MainActivity.this,NeedDirection.class);
//                startActivity(iNeedDirection);
//            }
//        });

//        sosBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iCall=new Intent(Intent.ACTION_DIAL);
//                iCall.setData(Uri.parse("tel: 112"));
//                startActivity(iCall);
//            }
//        });


//        chatBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent iAvailableChats=new Intent(MainActivity.this,AvailableChats.class);
//                startActivity(iAvailableChats);
//            }
//        });

//        mapsBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri gmmIntentUri = Uri.parse("geo:");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);
//            }
//        });
    }
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

}