package com.example.inmobiliaria;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobiliaria.MainActivityViewModel;
import com.example.inmobiliaria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //vm = new ViewModelProvider(this).get(MainActivityViewModel.class);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        vm.getErrorLogin().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tilClave.setError(s);
            }
        });

        binding.bIngresar.setOnClickListener(v -> {

            String usuario = binding.etUsuario.getText().toString();
            String clave = binding.etClave.getText().toString();

            vm.login(usuario, clave);
        });
        /*
        binding.etUsuario.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Mientas Escribe
                binding.tilClave.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Cuando termina de escribir
            }
        });*/
        TextWatcher watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             //Mientas esta escribiendo
                binding.tilClave.setError(null);

            }

            @Override
            public void afterTextChanged(Editable s) {
                //Cuando termina de escribir
            }
        };

        binding.etClave.addTextChangedListener(watcher);
        binding.etUsuario.addTextChangedListener(watcher);
    }
}