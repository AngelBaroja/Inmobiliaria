package com.example.inmobiliaria;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
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


        binding.bIngresar.setOnClickListener(v -> {

            String usuario = binding.etUsuario.getText().toString();
            String clave = binding.etClave.getText().toString();

            vm.login(usuario, clave);
        });
    }
}