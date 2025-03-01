package com.codewizards.opportunitpo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button loginBtn;
    ProgressBar progress;
    TextView registerBtnIntent;
    EditText emailTxt,passwordTxt;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        registerBtnIntent=findViewById(R.id.resigter_btn_login);
        loginBtn=findViewById(R.id.login_btn);

        emailTxt=findViewById(R.id.email_txt_login);
        passwordTxt=findViewById(R.id.password_txt_login);
        progress=findViewById(R.id.login_progress);

        auth=FirebaseAuth.getInstance();

        //Move to register window
        registerBtnIntent.setOnClickListener(view -> {
            Intent toRegister=new Intent(Login.this, Register.class);
            startActivity(toRegister);
            emailTxt.setText("");
            passwordTxt.setText("");
        });

        loginBtn.setOnClickListener(view -> {
            //hide keyboard on click
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);

            progress.setVisibility(View.VISIBLE);
            loginBtn.setClickable(false);

            String email=emailTxt.getText().toString();
            String password=passwordTxt.getText().toString();
            String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

            if(TextUtils.isEmpty(email)){
                progress.setVisibility(View.GONE);
                loginBtn.setClickable(true);
                Toast.makeText(Login.this, "Enter E-Mail", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(password)){
                progress.setVisibility(View.GONE);
                loginBtn.setClickable(true);
                Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
            }
            else if(!email.matches(emailPattern)){
                progress.setVisibility(View.GONE);
                loginBtn.setClickable(true);
                emailTxt.setError("Invaild E-Mail");
            }
            else if(password.length()<6){
                progress.setVisibility(View.GONE);
                loginBtn.setClickable(true);
                passwordTxt.setError("Less than Six characters");
            }
            else{
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            try {
                                Intent iMain = new Intent(Login.this, MainActivity.class);
                                progress.setVisibility(View.GONE);
                                loginBtn.setClickable(true);
                                startActivity(iMain);
                                finish();
                            }
                            catch(Exception e){
                                progress.setVisibility(View.GONE);
                                loginBtn.setClickable(true);
                                Toast.makeText(Login.this, "All fields are mandatory!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            progress.setVisibility(View.GONE);
                            loginBtn.setClickable(true);
                            Toast.makeText(Login.this, "Bad Credentials", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

        });


    }
}