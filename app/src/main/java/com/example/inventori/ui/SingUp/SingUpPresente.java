package com.example.inventori.ui.SingUp;

public class SingUpPresente implements SingUpContract.Presenter, SingUpContract.Interactor{

    private SingUpContract.View view;
    private SingUpInteractorImpl interactor;

    public SingUpPresente(SingUpContract.View view){
        this.view = view;
        interactor = new SingUpInteractorImpl(this);
    }

    @Override
    public void onSuccess(String msg) {
        view.hideProgressBar();
        view.onSuccess(msg);
    }

    @Override
    public void onFailure(String msg) {
        view.onFailure(msg);
    }

    @Override
    public void SingUpValidateCredentials(String nomUser, String email, String pass, String confirmPass) {
        interactor.ValidateCredenciales(nomUser, email, pass, confirmPass);
        view.showProgressBar();
    }

    @Override
    public void onUserEmptyErr() {
        view.hideProgressBar();
        view.setUserEmptyErr();
    }

    @Override
    public void onEmailEmptyErr() {
        view.hideProgressBar();
        view.setEmailEmptyErr();
    }

    @Override
    public void onPassEmptyErr() {
        view.hideProgressBar();
        view.setPassEmptyErr();
    }

    @Override
    public void onEmailErr() {
        view.hideProgressBar();
        view.setEmailErr();
    }

    @Override
    public void onPassErr() {
        view.hideProgressBar();
        view.setPassErr();
    }

    @Override
    public void onPassDontMach() {
        view.hideProgressBar();
        view.setPassDontMach();
    }
}
