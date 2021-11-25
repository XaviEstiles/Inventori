package com.example.inventori.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventori.R;
import com.example.inventori.data.model.User;
import com.example.inventori.databinding.ActivityLoginBinding;
import com.example.inventori.ui.SingUp.SingUpActivity;
import com.example.inventori.ui.dashboard.MainActivity;
import com.example.inventori.utils.CommonUtils;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private ActivityLoginBinding binding;
    private LoginContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new LoginPresenter(this);

        binding.btnRegistrar.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SingUpActivity.class));
        });

        binding.btnLogin.setOnClickListener(v -> {
            presenter.validateCredentials(new User(binding.tieUser.getText().toString(), binding.tiePassword.getText().toString()));
        });

        binding.tieUser.addTextChangedListener(new LoginTextWacher(binding.tieUser));
        binding.tiePassword.addTextChangedListener(new LoginTextWacher(binding.tiePassword));

    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter = null; //Se evita un futuro memory leaks
    }

    //region Metodos del contrato View-Presenter
    @Override
    public void setEmailEmptyError() {
        binding.tilEmail.setError(getString(R.string.errUserEmpty));
    }

    @Override
    public void setPasswordEmptyError() {
        binding.tilPassword.setError(getString(R.string.errPasswordEmpty));
    }

    @Override
    public void setPasswordError() {
        binding.tilPassword.setError(getString(R.string.errPassword));
    }

    @Override
    public void onSuccess(String msg) {
        startMainActivity();
    }

    @Override
    public void setFailature(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        hideProgressBar();
    }

    @Override
    public void showProgressBar() {
        binding.pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.pb.setVisibility(View.GONE);
    }
//endregion

    //region Clase que controla cada vez que el usuario introduce un caractor en un editable text imput
    class LoginTextWacher implements TextWatcher {

        View view;

        public LoginTextWacher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.tieUser:
                    validateEmail(editable.toString());
                    break;
                case R.id.tiePassword:
                    validatePassword(editable.toString());
                    break;
            }
        }

        /**
         * Metodo para validar la contraseña
         *
         * @param password
         */
        private void validatePassword(String password) {
            if (password.isEmpty()) {
                binding.tilPassword.setError(getString(R.string.errPasswordEmpty));
            } else if (!CommonUtils.isPasswordValid(password)) {
                binding.tilPassword.setError(getString(R.string.errPassword));
            } else {
                binding.tilPassword.setError(null);
            }
        }

        /**
         * Metodo que valida el Email
         * @param email
         */
        private void validateEmail(String email) {
            if (email.isEmpty()) {
                binding.tilEmail.setError(getString(R.string.errUserEmpty));
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.tilEmail.setError(getString(R.string.errEmail));
            } else {
                binding.tilEmail.setError(null);

            }
        }
    }
}

//endregion
