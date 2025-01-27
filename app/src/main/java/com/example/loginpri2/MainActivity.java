package com.example.loginpri2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.EmptyStackException;

public class MainActivity extends AppCompatActivity {

    private EditText userName, userPasswored, userEmail;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //upload thid data to the database
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPasswored.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                        //    if(task.isSuccessful()){
                          //      Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            //}else{
                        // Toast.makeText(MainActivity.this, "Registration Failure", Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }
        });

    }


    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etUserName);
        userPasswored = (EditText)findViewById(R.id.etUserPassword);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        regButton = (Button) findViewById(R.id.btnRegister);

    }

    private boolean validate(){
        Boolean result = false;

        String name = userName.getText().toString();
        String password = userPasswored.getText().toString();
        String email = userEmail.getText().toString();

        if(name.isEmpty() && password.isEmpty() && email.isEmpty()){
            Toast.makeText(this, "Please Enter All The Details", Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;



    }
}
