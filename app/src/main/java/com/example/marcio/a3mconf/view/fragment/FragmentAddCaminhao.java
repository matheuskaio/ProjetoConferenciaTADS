package com.example.marcio.a3mconf.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marcio.a3mconf.R;

import control.FuncionarioControl;
import model.Conferente;


public class FragmentAddCaminhao extends Fragment {
    private TextInputEditText placa,modelo;
    private Button cadastrar,cancelar;
    private Conferente conferente;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Adicionar Caminhão");
        View view = inflater.inflate(R.layout.fragment_add_caminhao,container,false);
        conferente = (Conferente) getArguments().getSerializable("funcionario");
        modelo  = view.findViewById(R.id.modelo_caminhao);
        placa   = view.findViewById(R.id.placa_caminhao);
        cadastrar = view.findViewById(R.id.cadastrar_caminhao);
        cancelar = view.findViewById(R.id.cancelar_cadastro_caminhao);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FuncionarioControl.getIstace().cadastrarCaminhao(modelo.getText().toString(),placa.getText().toString());
            }
        });



        return view;
    }
}
