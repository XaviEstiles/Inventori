package com.example.inventori.ui.SingUp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.inventori.R;
import com.example.inventori.databinding.ActivitySingUpBinding;
import com.example.inventori.ui.login.LoginActivity;
import com.example.inventori.utils.CommonUtils;

public class SingUpActivity extends AppCompatActivity implements SingUpContract.View{
    private ActivitySingUpBinding binding;
    private SingUpContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SingUpPresente(this);
        binding = ActivitySingUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /**
        * Se utiliza para simular el boton back para restaurar la de login y eliminar singUp
        * */
        binding.btnRegistrar.setOnClickListener(v -> {
            presenter.SingUpValidateCredentials(binding.txtUser.getText().toString(),
                                                binding.txtEmail.getText().toString(),
                                                binding.txtPass.getText().toString(),
                                                binding.txtConfirmPass.getText().toString());
        });

        binding.txtUser.addTextChangedListener(new SingUpActivity.SingUpTextWacher(binding.txtUser));
        binding.txtEmail.addTextChangedListener(new SingUpActivity.SingUpTextWacher(binding.txtEmail));
        binding.txtPass.addTextChangedListener(new SingUpActivity.SingUpTextWacher(binding.txtPass));
    }

    @Override
    public void setUserEmptyErr() {
        binding.tieUser.setError(getString(R.string.errUserEmpty));
    }

    @Override
    public void setEmailEmptyErr() {
        binding.tieEmail.setError(getString(R.string.errEmailEmpty));
    }

    @Override
    public void setPassEmptyErr() {
        binding.tiePassword.setError(getString(R.string.errPasswordEmpty));
    }

    @Override
    public void setEmailErr() {
        binding.tieEmail.setError(getString(R.string.errEmailErr));
    }

    @Override
    public void setPassErr() {
        binding.tiePassword.setError(getString(R.string.errPassword));
    }

    @Override
    public void setPassDontMach() { binding.tieConfirmPassword.setError(getString(R.string.errConfirmPass));
    }

    @Override
    public void showProgressBar() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(String msg) {
        onBackPressed();
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        hideProgressBar();
    }


    //endregion

    //region Clase que controla cada vez que el usuario introduce un caractor en un editable text imput
    class SingUpTextWacher implements TextWatcher {

        View view;

        public SingUpTextWacher(View view) {
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
                case R.id.txtUser:
                    validateUser(editable.toString());
                    break;
                case R.id.txtEmail:
                    validateEmail(editable.toString());
                    break;
                case R.id.txtPass:
                    validatePassword(editable.toString());
                    break;
            }
        }

        /**
         * Metodo para validar la contraseña
         * @param password
         */
        private void validatePassword(String password) {
            if (password.isEmpty()) {
                binding.tiePassword.setError(getString(R.string.errPasswordEmpty));
            } else if (!CommonUtils.isPasswordValid(password)) {
                binding.tiePassword.setError(getString(R.string.errPassword));
            } else {
                binding.tiePassword.setError(null);
            }
        }

        /**
         * Metodo que valida el Email
         * @param email
         */
        private void validateEmail(String email) {
            if (email.isEmpty()) {
                binding.tieEmail.setError(getString(R.string.errEmailEmpty));
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.tieEmail.setError(getString(R.string.errEmail));
            } else {
                binding.tieEmail.setError(null);

            }
        }

        private void validateUser(String user) {
            if (user.isEmpty()) {
                binding.tieUser.setError(getString(R.string.errUserEmpty));
            }else {
                binding.tieUser.setError(null);
            }
        }
    }
}