package com.example.youtubemusic.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubemusic.dashboard.DashboardActivity;
import com.example.youtubemusic.databinding.ActivityRegisterBinding;
import com.example.youtubemusic.util.UtilProvider;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View, View.OnClickListener {
    private RegisterContract.Presenter presenter;
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new RegisterPresenter(this, UtilProvider.getSharedPreferencesUtil());

        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(presenter.hasUser()) {
            redirectToHome();
        }
    }

    private void initView() {
        binding.btRegister.setOnClickListener(this);
    }

    private void redirectToHome() {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public void registerSuccess(String message) {
        makeToast(message);
        redirectToHome();
    }

    @Override
    public void registerFailed(String message) {
        makeToast(message);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == binding.btRegister.getId())
            onSubmit();
    }

    private void onSubmit() {
        String username = binding.etUsername.getText().toString();
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        String confirmPassword = binding.etConfirmationPassword.getText().toString();
        presenter.register(username, email, password, confirmPassword);
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
