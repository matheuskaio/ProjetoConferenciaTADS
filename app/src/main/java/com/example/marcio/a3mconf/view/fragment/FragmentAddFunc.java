package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.listeners.TrocaDeTelasListener;

import control.FuncionarioControl;
import model.Gerente;
import model.Perfil;

public class FragmentAddFunc extends Fragment {
    private Gerente gerente;
    private TextView name,cpf,senha;
    private RadioGroup group;
    private Button cadastrar;
    private RadioButton radioConferente;
    private RadioButton radioMotorista;
    private TrocaDeTelasListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        gerente     = (Gerente) getArguments().getSerializable("funcionario");
        final View view   = inflater.inflate(R.layout.fragment_add_funcionario,container,false);
        name        = view.findViewById(R.id.name_func);
        cpf         = view.findViewById(R.id.cpf_fun);
        senha       = view.findViewById(R.id.senha_func);
        cadastrar   = view.findViewById(R.id.btn_cadastro);
        group       = view.findViewById(R.id.group_profile);
        radioConferente = ((RadioButton) view.findViewById(R.id.radio_conferente));
        radioMotorista  = ((RadioButton) view.findViewById(R.id.radio_motorista));

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Perfil perfil= null;
                if(radioConferente.isChecked()){
                    perfil = Perfil.CONFERENTE;
                }else if(radioMotorista.isChecked()){
                    perfil = perfil.MOTORISTA;
                }
                new FuncionarioControl().addFuncionario(name.getText().toString(),cpf.getText().toString(),senha.getText().toString(), perfil);
                listener.openTelaHome();
            }
        });
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TrocaDeTelasListener) context;

    }
}
