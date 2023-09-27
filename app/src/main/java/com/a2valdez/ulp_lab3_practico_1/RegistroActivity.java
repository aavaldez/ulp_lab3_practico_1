package com.a2valdez.ulp_lab3_practico_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.a2valdez.ulp_lab3_practico_1.databinding.ActivityRegistroBinding;
import com.a2valdez.ulp_lab3_practico_1.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegistroBinding binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        //Usuario u = (Usuario)intent.getParcelableExtra("usuario");
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        mv.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if (usuario != null){
                    binding.etDni.setText(String.valueOf(usuario.getDni()));
                    binding.etApellido.setText(usuario.getApellido());
                    binding.etNombre.setText(usuario.getNombre());
                    binding.etMail.setText(usuario.getMail());
                    binding.etPassword.setText(usuario.getPassword());
                }
            }
        });
        mv.LeerUsuario();
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mv.GuardarUsuario(
                        binding.etDni.getText().toString(),
                        binding.etApellido.getText().toString(),
                        binding.etNombre.getText().toString(),
                        binding.etMail.getText().toString(),
                        binding.etPassword.getText().toString()                );
            }
        });
    }
}