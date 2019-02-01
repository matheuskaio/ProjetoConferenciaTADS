package com.example.marcio.a3mconf.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.squareup.picasso.Picasso;

import control.FuncionarioControl;
import model.Conferente;
import model.Funcionario;
import model.Motorista;
import model.request.Connection;

public class FragmentProfile extends Fragment {
    private TextView nome,cpf,senha,cargo;
    private ImageView image;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        getActivity().setTitle("Perfil");

        FuncionarioControl funcionarioControl = FuncionarioControl.getIstace();

        nome    = view.findViewById(R.id.name_perfil_func);
        cpf     = view.findViewById(R.id.cpf_perfil_func);
        senha   = view.findViewById(R.id.senha_perfil_func);
        cargo   = view.findViewById(R.id.cargo_perfil_func);
        image   = view.findViewById(R.id.foto_profile);

        nome.setText(funcionarioControl.getFuncionario().getNome());
        cpf.setText(funcionarioControl.getFuncionario().getCpf());
        senha.setText(funcionarioControl.getFuncionario().getSenha());

        if(funcionarioControl.getFuncionario().getFoto() != null){
            Picasso.get().load(Connection.URL+funcionarioControl.getFuncionario().getFoto()+".jpg").into(image);
        }

        if(funcionarioControl.isConferente()){
            cargo.setText("Conferente");
        }else if(funcionarioControl.isMotorista()){
            cargo.setText("Motorista");
        }else{
            cargo.setText("Gerente");
        }
        return view;
    }
}
