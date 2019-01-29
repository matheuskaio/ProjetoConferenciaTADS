package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.listeners.TrocaDeTelasListener;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import control.FuncionarioControl;
import model.Gerente;

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
        getActivity().setTitle("Adicionar Funcionario");
        name        = view.findViewById(R.id.name_func);
        cpf         = view.findViewById(R.id.cpf_fun);
        senha       = view.findViewById(R.id.senha_func);
        cadastrar   = view.findViewById(R.id.btn_cadastro);
        group       = view.findViewById(R.id.group_profile);
        radioConferente = ((RadioButton) view.findViewById(R.id.radio_conferente));
        radioMotorista  = ((RadioButton) view.findViewById(R.id.radio_motorista));

        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(cpf,simpleMaskFormatter);
        cpf.addTextChangedListener(maskTextWatcher);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                char perfil= 'n';
                if(radioConferente.isChecked()){
                    perfil = 'C';
                }else if(radioMotorista.isChecked()){
                    perfil = 'M';
                }
                FuncionarioControl.getIstace().addFuncionario(name.getText().toString(),cpf.getText().toString(),senha.getText().toString(), perfil);
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
