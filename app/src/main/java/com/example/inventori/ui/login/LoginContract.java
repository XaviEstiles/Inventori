package com.example.inventori.ui.login;

import com.example.inventori.data.model.User;

public class LoginContract {
    public interface View extends OnLoginListener{

        //Alternativas del caso de uso. set porque se modifica
        void setEmailEmptyError();
        void setPasswordEmptyError();
        void setPasswordError();
        //Contrato de la vista y presentador
        void showProgressBar();
        void hideProgressBar();

    }
    public interface  Presenter {
        void validateCredentials( User user);
    }

    public interface LoginRepository{
        void login(User user);
    }

    public interface OnLoginListener{
        void onSuccess(String msg);
        void setFailature(String msg);
    }

    interface LoginInteractor extends OnLoginListener{
        void onEmailEmptyErr();
        void onPasswordEmptyErr();
        void onPasswordErr();
    }
}
