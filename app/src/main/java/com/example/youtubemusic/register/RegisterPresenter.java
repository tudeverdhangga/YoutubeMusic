package com.example.youtubemusic.register;

public class RegisterPresenter implements  RegisterContract.Presenter{
    private RegisterContract.View view;
    private RegisterContract.Interactor interactor;

    public RegisterPresenter(RegisterContract.View view, RegisterContract.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void register(String name, String phone, String email, String password, String confirmPassword) {

    }
}
