package com.ssproduction.shashank.ucn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    private TextInputLayout loginEmailOrMob;
    private TextInputLayout loginPassword;
    private TextView loginTerms;
    private Button loginBtn;
    private TextView linkRegisterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);


        loginEmailOrMob = (TextInputLayout) findViewById(R.id.login_email_or_mob);
        loginPassword = (TextInputLayout)  findViewById(R.id.login_password);
        loginTerms = (TextView) findViewById(R.id.login_terms);
        loginBtn = (Button) findViewById(R.id.login_btn);
        linkRegisterText = (TextView) findViewById(R.id.link_register_text);

        linkRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(regIntent);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String email = loginEmailOrMob.getEditText().getText().toString();
                String password = loginPassword.getEditText().getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
                {
                    Toast.makeText(LoginActivity.this, "please  fill all the details", Toast.LENGTH_SHORT).show();
                }
                else if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
                {
                    progressDialog.setTitle("Logging");
                    progressDialog.setMessage("please wait while we are logging your account..");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    loginAccount(email, password);
                }

            }
        });

    }

    private void loginAccount(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();

                    Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                    finish();
                }
                else {
                    progressDialog.hide();
                    Toast.makeText(LoginActivity.this, "Error..try again later", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}








