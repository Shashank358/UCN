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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {


    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference mDatabaseRef;


    private TextInputLayout regFirstName;
    private TextInputLayout regLastName;
    private TextInputLayout regEmailOrMob;
    private TextInputLayout regPassword;
    private TextView regTerms;
    private Button regBtn;
    private TextView linkLoginText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();


        regFirstName = (TextInputLayout) findViewById(R.id.register_first_name);
        regLastName = (TextInputLayout) findViewById(R.id.register_last_name);
        regEmailOrMob = (TextInputLayout) findViewById(R.id.register_email_or_mob);
        regPassword = (TextInputLayout) findViewById(R.id.register_new_password);
        regTerms = (TextView) findViewById(R.id.register_terms_text);
        regBtn = (Button) findViewById(R.id.register_get_started_btn);
        linkLoginText = (TextView) findViewById(R.id.link_register_text);

        linkLoginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(regIntent);
            }
        });



        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = regFirstName.getEditText().getText().toString();
                String lastName = regLastName.getEditText().getText().toString();
                String emailOrMob = regEmailOrMob.getEditText().getText().toString();
                String password = regPassword.getEditText().getText().toString();

                if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) &&
                        !TextUtils.isEmpty(emailOrMob) && !TextUtils.isEmpty(password)  )
                {
                    progressDialog.setTitle("Creating Account");
                    progressDialog.setMessage("please wait while we are creating your account..");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    registerAccount(firstName, lastName, emailOrMob, password);
                }

            }
        });



    }

    private void registerAccount(final String firstName, final String lastName, String emailOrMob, String password) {

        mAuth.createUserWithEmailAndPassword(emailOrMob, password).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
                            String uid = current_user.getUid();

                            mDatabaseRef = database.getInstance().getReference().child("Users").child(uid);

                            HashMap<String, String> userMap = new HashMap<>();
                            userMap.put("user_first_name", firstName);
                            userMap.put("user_last_name", lastName);
                            userMap.put("user_profile_image", "default");
                            userMap.put("user_cover_image", "default");
                            userMap.put("user_status_text", "Hey there.I am using USN");
                            userMap.put("user_uploaded_image", "default");
                            userMap.put("user_uploaded_video", "default");
                            userMap.put("user_tweets", "default");
                            userMap.put("user_status_video", "default");

                            mDatabaseRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful())
                                    {
                                        progressDialog.dismiss();

                                        Intent regIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                        regIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(regIntent);
                                        finish();
                                    }
                                }
                            });
                        }
                        else
                        {
                            progressDialog.hide();
                            Toast.makeText(RegisterActivity.this, "Error..try again later", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );


    }
}
