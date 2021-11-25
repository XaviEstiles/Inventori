package com.example.inventori.ui.SingUp;

public interface SingUpContract {

    interface OnSingUpListener{
        void onSuccess(String msg);
        void onFailure(String msg);
    }

    interface View extends OnSingUpListener{
        void setUserEmptyErr();
        void setEmailEmptyErr();
        void setPassEmptyErr();

        void setEmailErr();
        void setPassErr();
        void setPassDontMach();

        void showProgressBar();
        void hideProgressBar();
    }

    interface Presenter{
        void SingUpValidateCredentials(String nomUser, String email, String pass, String confirmPass);
    }

    interface Interactor extends OnSingUpListener{
        void onUserEmptyErr();
        void onEmailEmptyErr();
        void onPassEmptyErr();

        void onEmailErr();
        void onPassErr();
        void onPassDontMach();
    }

    interface Repositorio{
        void SingUp(String nomUser, String email, String pass, String confirmPass);
    }




}
