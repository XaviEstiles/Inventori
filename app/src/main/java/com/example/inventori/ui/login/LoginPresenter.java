package com.example.inventori.ui.login;

import com.example.inventori.data.model.User;

public class LoginPresenter implements LoginContract.Presenter, LoginContract.LoginInteractor {

    private LoginContract.View view;
    private LoginInteractorImpl interactor;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        interactor = new LoginInteractorImpl(this);
    }

//region Metodos del contrato presnter-interactor
    @Override
    public void validateCredentials(User user) {
        interactor.validateCredentials(user);
        view.showProgressBar();

    }

    @Override
    public void onSuccess(String msg) {
        view.hideProgressBar();
        view.onSuccess(msg);
    }

    @Override
    public void setFailature(String msg) {
        view.setFailature(msg);
    }

    @Override
    public void onEmailEmptyErr() {
        view.hideProgressBar();
        view.setEmailEmptyError();
    }

    @Override
    public void onPasswordEmptyErr() {
        view.hideProgressBar();
        view.setPasswordEmptyError();
    }

    @Override
    public void onPasswordErr() {
        view.hideProgressBar();
        view.setPasswordError();
    }
//endregion
}
