package com.example.inventori.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.inventori.R;
import com.example.inventori.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private static final long WAIT_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    /**
     *Vamos a simular un tiempo de espera con un hilo que duerme durante 2 segundos y cuando despierta
     * se ejecuta un metodo startLogin() que inicia la activity Login
     */
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(() -> startLogin(),WAIT_TIME);
    }

    private void startLogin(){
        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
        //Voy a llamar de forma explicita al metodo finish() de una activity, para eliminar
        //esta activity de la pila de actividades, porque si pulsa back no se muestre.
        finish();
    }
}