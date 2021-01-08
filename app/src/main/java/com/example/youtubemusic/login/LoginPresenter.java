package com.example.youtubemusic.login;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.youtubemusic.util.SharedPreferencesUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;

import static android.content.ContentValues.TAG;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final SharedPreferencesUtil sharedPreferencesUtil;
    private FirebaseAuth mAuth;

    public LoginPresenter(LoginContract.View view, SharedPreferencesUtil sharedPreferencesUtil) {
        this.view = view;
        this.sharedPreferencesUtil = sharedPreferencesUtil;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
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
                            view.loginSuccess("Sukses Melakukan Login");
                        } else {
                            // If sign in fails, display a message to the user.
                            String errorMessage = task.getException().getMessage();
                            view.loginFailed(errorMessage);
                            Log.d(TAG, "gagal" +  task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public boolean hasUser() {
        return (sharedPreferencesUtil.getToken() != null);
    }

}
