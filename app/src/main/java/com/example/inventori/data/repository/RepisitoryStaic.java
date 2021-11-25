package com.example.inventori.data.repository;

import com.example.inventori.data.model.User;
import com.example.inventori.ui.SingUp.SingUpContract;
import com.example.inventori.ui.login.LoginContract;

import java.util.ArrayList;

public class RepisitoryStaic implements LoginContract.LoginRepository, SingUpContract.Repositorio{

    private static RepisitoryStaic instance;
    private LoginContract.OnLoginListener loginListener;
    private SingUpContract.OnSingUpListener singUpListener;

    private ArrayList<String[]> users;

    private RepisitoryStaic(){
        users = new ArrayList<>();
        users.add(new String[]{"Xajot","vallsxavi01@gmail.com","Xa29014.","Xa29014."});
        users.add(new String[]{"Xajot","vallsxavi02@gmail.com","Xa29014.","Xa29014."});
        users.add(new String[]{"Xajot","vallsxavi03@gmail.com","Xa29014.","Xa29014."});
        users.add(new String[]{"Xajot","vallsxavi04@gmail.com","Xa29014.","Xa29014."});
    }

    public static RepisitoryStaic getInstanceSingUp( SingUpContract.OnSingUpListener singUpListener){
        if(instance == null){
            instance = new RepisitoryStaic();
        }
        instance.singUpListener = singUpListener;
        return instance;
    }

    public static RepisitoryStaic getInstanceLogin(LoginContract.OnLoginListener loginListener){
        if(instance == null){
            instance = new RepisitoryStaic();
        }
        instance.loginListener = loginListener;
        return instance;
    }

    @Override
    public void SingUp(String nomUser, String email, String pass, String confirmPass) {
        String[] user = new String[]{nomUser,email,pass,confirmPass};
        for(String[] usuario : users){
            if (usuario[1].equals(email)){
                singUpListener.onFailure("Usuario existente");
                return;
            }
        }
        users.add(user);
        singUpListener.onSuccess("");
    }

    @Override
    public void login(User user) {
        for(String[] usuario : users){
            if (usuario[1].equals(user.getUser()) && usuario[2].equals(user.getPassword()) ){
                loginListener.onSuccess("");
                return;
            }
        }
        loginListener.setFailature("Contraseña o email incorrectos");
    }
}
