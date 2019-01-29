package com.example.marcio.a3mconf.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.github.rtoshiro.util.format.MaskFormatter;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import control.FuncionarioControl;

public class LoginView extends AppCompatActivity {
    private Button logar;
    private EditText cpf,password;
    private TextView mensage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView(){

        logar = findViewById(R.id.logar);
        cpf = findViewById(R.id.login);
        password = findViewById(R.id.password);
        mensage = findViewById(R.id.mensage);

        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(cpf,simpleMaskFormatter);
        cpf.addTextChangedListener(maskTextWatcher);

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuncionarioControl funcionarioControl = FuncionarioControl.getIstace();
                String msg = funcionarioControl.altenticado(cpf.getText().toString(),password.getText().toString());
                if(msg==null){
                    Intent intent = new Intent(LoginView.this,MainView.class);
                    intent.putExtra("funcionario",funcionarioControl.logar());
                    startActivity(intent);

                }else{
                    mensage.setText(msg);
                }
            }
        });
    }

}
