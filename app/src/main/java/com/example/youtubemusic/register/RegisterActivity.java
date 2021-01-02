package com.example.youtubemusic.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubemusic.databinding.ActivityRegisterBinding;
import com.example.youtubemusic.play.PlayActivity;
import com.example.youtubemusic.util.UtilProvider;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View, View.OnClickListener {
    private RegisterContract.Presenter presenter;
    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        presenter = new RegisterPresenter(this, new RegisterInteractor(UtilProvider.getSharedPreferencesUtil()));

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null) {
            Intent homeActivity = new Intent(this, PlayActivity.class);
            homeActivity.putExtra("USER", currentUser);
            startActivity(homeActivity);
        }
        //updateUI(currentUser);
    }



    private void initView() {
        binding.btRegister.setOnClickListener(this);
    }

    @Override
    public void registerSuccess(String message) {

    }

    @Override
    public void registerFailed(String message) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btRegister.getId())
            onSubmit();
    }

    private void onSubmit() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        String confirmPassword = binding.etConfirmationPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SUCCESS", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(RegisterActivity.this, PlayActivity.class);
                            startActivity(intent);
                            finish();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("FAILED", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
