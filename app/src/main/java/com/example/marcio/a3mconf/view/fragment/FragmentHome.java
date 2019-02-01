package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.listeners.TrocaDeTelasListener;

import control.FuncionarioControl;
import model.Conferente;
import model.Funcionario;

public class FragmentHome extends Fragment {
    private Button btnNovaConferencia;
    private TrocaDeTelasListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        getActivity().setTitle("Home");


        btnNovaConferencia = view.findViewById(R.id.btn_nova_conferencia);
        if(!(FuncionarioControl.getIstace().isConferente())){
            btnNovaConferencia.setVisibility(View.GONE);
        }
        btnNovaConferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.openTelaNovaConferencia();
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
