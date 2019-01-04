package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
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
import com.example.marcio.a3mconf.view.listeners.TelaAddLoteListener;

import control.Lote;

public class FragmentAddLote extends Fragment {
    private TelaAddLoteListener listener;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_lote,container,false);
        Button btnAdd = view.findViewById(R.id.adicionar);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TextInputEditText produto = view.findViewById(R.id.produto);
//                TextInputEditText altura = view.findViewById(R.id.altura);
//                TextInputEditText lastro = view.findViewById(R.id.lastro);
//                TextInputEditText unMedida = view.findViewById(R.id.un_medida);
                Lote lote = new Lote();


                listener.addLote(lote);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TelaAddLoteListener) context;
    }
}
