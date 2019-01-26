package com.example.marcio.a3mconf.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

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

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuncionarioControl funcionarioControl = new FuncionarioControl();
                String msg = funcionarioControl.altenticado(/*cpf.getText().toString()*/"11111111112",/*password.getText().toString()*/"123");
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
