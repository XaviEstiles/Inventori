package com.example.inventori.ui.SingUp;

import android.util.Patterns;

import com.example.inventori.data.repository.LoginRepositoryImpl;
import com.example.inventori.data.repository.RepisitoryStaic;
import com.example.inventori.ui.login.LoginContract;
import com.example.inventori.utils.CommonUtils;

public class SingUpInteractorImpl implements SingUpContract.OnSingUpListener{

    private SingUpContract.Interactor presenter;
    private SingUpContract.Repositorio repositorio;

    public SingUpInteractorImpl(SingUpContract.Interactor presenter){
        this.presenter = presenter;
        repositorio = RepisitoryStaic.getInstanceSingUp(presenter);//LoginRepositoryImpl.getInstanceSingUp(presenter);
    }

    public void ValidateCredenciales(String nomUser, String email, String pass, String confirmPass){
        if(nomUser.isEmpty()){
            presenter.onUserEmptyErr();
            return;
        }
        if(email.isEmpty()){
            presenter.onEmailEmptyErr();
            return;
        }
        if(pass.isEmpty()){
            presenter.onPassEmptyErr();
            return;
        }
        if(!pass.equals(confirmPass)){
            presenter.onPassDontMach();
            return;
        }
        if(!CommonUtils.isPasswordValid(pass)){
            presenter.onPassErr();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            presenter.onEmailErr();
            return;
        }
        repositorio.SingUp(nomUser, email, pass, confirmPass);
    }

    @Override
    public void onSuccess(String msg) {
        presenter.onSuccess(msg);
    }

    @Override
    public void onFailure(String msg) {
        presenter.onFailure(msg);
    }
}
