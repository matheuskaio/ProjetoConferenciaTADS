package com.example.marcio.a3mconf.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.marcio.a3mconf.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import control.ConferenteIndirection;
import model.exceptions.EmptyFieldException;


public class FragmentAddCaminhao extends Fragment {
    private TextInputEditText placa,modelo;
    private Button cadastrar,cancelar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Adicionar Caminhão");

        View view = inflater.inflate(R.layout.fragment_add_caminhao,container,false);
        modelo  = view.findViewById(R.id.modelo_caminhao);
        placa   = view.findViewById(R.id.placa_caminhao);
        cadastrar = view.findViewById(R.id.cadastrar_caminhao);
        cancelar = view.findViewById(R.id.cancelar_cadastro_caminhao);

        SimpleMaskFormatter simpleMaskFormatter = new SimpleMaskFormatter("LLL-NNNN");
        MaskTextWatcher maskTextWatcher = new MaskTextWatcher(placa,simpleMaskFormatter);
        placa.addTextChangedListener(maskTextWatcher);
        placa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() >= 3){
                    placa.setInputType(InputType.TYPE_CLASS_NUMBER);
                }else {
                    placa.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPlaca = placa.getText().toString();
                strPlaca = replacePlaca(strPlaca);
                try {
                    ConferenteIndirection.getInstance().cadastrarCaminhao(modelo.getText().toString(),strPlaca);
                    Toast.makeText(getContext(),"Caminhão cadastrado com sucesso",Toast.LENGTH_SHORT).show();

                    placa.setText(null);
                    modelo.setText(null);

                } catch (EmptyFieldException e) {
                    Toast.makeText(getContext(),"Preencha todos os campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
    private String replacePlaca(String placa){
        return placa.replace("-","");
    }
}
