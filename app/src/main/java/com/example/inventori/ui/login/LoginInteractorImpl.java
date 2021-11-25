package com.example.inventori.ui.login;

import android.os.Handler;
import android.text.TextUtils;

import com.example.inventori.data.model.User;
import com.example.inventori.data.repository.LoginRepositoryImpl;
import com.example.inventori.data.repository.RepisitoryStaic;
import com.example.inventori.utils.CommonUtils;

public class LoginInteractorImpl implements LoginContract.OnLoginListener{


    private LoginContract.LoginInteractor listener;
    private LoginContract.LoginRepository repository;
    //El programador que quiera utilizar esta clase debe implementar esta interfaz

    public LoginInteractorImpl(LoginContract.LoginInteractor listener) {
        this.listener = listener;
        this.repository = RepisitoryStaic.getInstanceLogin(listener);//LoginRepositoryImpl.getInstanceLogin(this.listener);
    }

    public void validateCredentials( User user){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TextUtils.isEmpty(user.getUser())){
                    listener.onEmailEmptyErr();
                    return;
                }
                if(TextUtils.isEmpty(user.getPassword())){
                    listener.onPasswordEmptyErr();
                    return;
                }
                if (!CommonUtils.isPasswordValid(user.getPassword())){
                    listener.onPasswordErr();
                    return;
                }

                repository.login(user);

            }
        }, 0);
    }

    @Override
    public void onSuccess(String msg) {
        listener.onSuccess(msg);
    }

    @Override
    public void setFailature(String msg) {
        listener.setFailature(msg);
    }
}
