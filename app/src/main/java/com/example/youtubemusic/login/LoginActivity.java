package com.example.youtubemusic.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.youtubemusic.dashboard.DashboardActivity;
import com.example.youtubemusic.databinding.ActivityLoginBinding;
import com.example.youtubemusic.register.RegisterActivity;
import com.example.youtubemusic.util.UtilProvider;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    ActivityLoginBinding binding;
    LoginContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new LoginPresenter(this, UtilProvider.getSharedPreferencesUtil());
//        setContentView(R.layout.activity_login);
        binding.tvToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString();
                String password = binding.etPassword.getText().toString();
                presenter.login(email, password);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(presenter.hasUser()) {
            redirectToHome();
        }
    }

    @Override
    public void loginSuccess(String message) {
        makeToast(message);
        redirectToHome();
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFailed(String message) {
        makeToast(message);
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}