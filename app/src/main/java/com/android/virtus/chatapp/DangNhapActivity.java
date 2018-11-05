package com.android.virtus.chatapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DangNhapActivity extends AppCompatActivity {
    TextView editEmailDN, editPasswordDN;
    Button btnDangKy, btnDangNhap;
    CheckBox chkNhoMatKhau;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    ProgressBar pbLoading;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            Intent intent = new Intent(DangNhapActivity.this, MainChatActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        mAuth = FirebaseAuth.getInstance();
        editEmailDN = (EditText) findViewById(R.id.editEmailDN);
        editPasswordDN = (EditText) findViewById(R.id.editPasswordDN);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itDangKy = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(itDangKy);
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap();
            }
        });
    }

    public void DangNhap() {
        String email = editEmailDN.getText().toString().trim();
        String password = editPasswordDN.getText().toString().trim();
        if (email.isEmpty()) {
            editEmailDN.setError("Bạn chưa nhập email");
            editEmailDN.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editPasswordDN.setError("Bạn chưa nhập mật khẩu");
            editPasswordDN.requestFocus();
            return;
        }
        btnDangNhap.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pbLoading.setVisibility(View.GONE);
                        btnDangNhap.setVisibility(View.VISIBLE);
                        if (task.isSuccessful()) {
                            Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            Intent ItMainChat = new Intent(DangNhapActivity.this, MainChatActivity.class);
                            startActivity(ItMainChat);
                        } else {
                            Toast.makeText(DangNhapActivity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
}
