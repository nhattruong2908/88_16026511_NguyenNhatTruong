package com.example.a88_16026511_nguyennhattruong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button btndangnhap;
    private EditText etemail,etpassword;
    private FirebaseAuth firebaseAuth;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            firebaseAuth=FirebaseAuth.getInstance();
            btndangnhap=(Button)findViewById(R.id.btndangnhap);
            etemail=(EditText)findViewById(R.id.etemail);
            etpassword=(EditText)findViewById(R.id.etpassword);

            btndangnhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email=etemail.getText().toString();
                    String password=etpassword.getText().toString();

                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                if(password.length()<6){
                                etpassword.setError("hon 6 ki tu");
                                }else {
                                    Toast.makeText(MainActivity.this,"email hoac password sai",Toast.LENGTH_SHORT).show();
                                }

                            }else {
                                Intent intent=new Intent(MainActivity.this,Manager.class);
                                startActivity(intent);
                                finish();

                            }
                        }
                    });
                }
            });
        }
}