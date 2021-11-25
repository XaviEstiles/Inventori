package com.example.inventori.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.inventori.data.model.User;
import com.example.inventori.ui.SingUp.SingUpContract;
import com.example.inventori.ui.login.LoginContract;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * vamos a simular qie la instancia de LoginRepository es unica.Si es asi
 * PATRON SINGLETON
 * -- Constructor vacio
 * -- Implementar getInstans()
 */
public class LoginRepositoryImpl implements LoginContract.LoginRepository, SingUpContract.Repositorio {

    private static LoginRepositoryImpl instance;
    private LoginContract.OnLoginListener loginListener;
    private SingUpContract.OnSingUpListener singUpListener;
    private static final String TAG= LoginRepositoryImpl.class.getName();

    private LoginRepositoryImpl(){

    }

    public static LoginRepositoryImpl getInstanceLogin(LoginContract.OnLoginListener loginListener){
        if(instance==null){
            instance = new LoginRepositoryImpl();
        }
        instance.loginListener = loginListener;
        return instance;
    }

    public static LoginRepositoryImpl getInstanceSingUp(SingUpContract.OnSingUpListener singUpListener){
        if(instance==null){
            instance = new LoginRepositoryImpl();
        }
        instance.singUpListener = singUpListener;
        return instance;
    }

    @Override
    public void login(User user) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(user.getUser(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success");
                    loginListener.onSuccess("usuario correcto");

                }
                else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    loginListener.setFailature("Error Autenticacion:"+task.getException());
                }
            }
        });
    }

    @Override
    public void SingUp(String nomUser, String email, String pass, String confirmPass) {
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    singUpListener.onSuccess("Error Autenticacion:"+task.getException());
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    singUpListener.onFailure("Error Autenticacion:"+task.getException());
                }
            }
        });
    }
}
