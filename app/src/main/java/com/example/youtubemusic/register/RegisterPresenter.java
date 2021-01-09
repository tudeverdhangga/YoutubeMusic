package com.example.youtubemusic.register;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.youtubemusic.util.SharedPreferencesUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import java.util.concurrent.Executor;

public class RegisterPresenter implements  RegisterContract.Presenter {
    private final RegisterContract.View view;
    private final SharedPreferencesUtil sharedPreferencesUtil;
    private FirebaseAuth mAuth;

    public RegisterPresenter(RegisterContract.View view, SharedPreferencesUtil sharedPreferencesUtil) {
        this.view = view;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void register(String username, String email, String password, String confirmPassword) {
        if(password.equals(confirmPassword)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d("SUCCESS", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<GetTokenResult> task) {
                                        if (task.isSuccessful()) {
                                            String idToken = task.getResult().getToken();
                                            sharedPreferencesUtil.setToken(idToken);
                                        } else {
                                            System.out.println(task.getException());
                                        }
                                    }
                                });
                                view.registerSuccess("Sukses Melakukan Registrasi");
                            } else {
                                String errorMessage = task.getException().getMessage();
                                view.registerFailed(errorMessage);
                                System.out.println(errorMessage);
                            }
                        }
                    });
        } else {
            view.registerFailed("Password dan konfirmasi password tidak cocok");
        }
    }

    public boolean hasUser() {
        return (sharedPreferencesUtil.getToken() != null);
    }
}
