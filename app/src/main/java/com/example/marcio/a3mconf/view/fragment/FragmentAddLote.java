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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.listeners.TelaAddLoteListener;

import control.Conferente;
import control.Lote;

public class FragmentAddLote extends Fragment {

    private TelaAddLoteListener listener;
    private ImageView imageAltura;
    private ImageView imageLastro;
    private Button btnAdd;
    private Conferente conferente;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add_lote,container,false);

        conferente = (Conferente) getArguments().getSerializable("funcionario");

        ((TextView) view.findViewById(R.id.quantidade)).setText(""+conferente.getCarga().getLotes().size());

        btnAdd = view.findViewById(R.id.adicionar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.addLote(newLote(view));
            }
        });

        imageAltura = view.findViewById(R.id.imageAlturaUpload);
        selectedImageListener(imageAltura);

        imageLastro = view.findViewById(R.id.imageLastroUpload);
        selectedImageListener(imageLastro);


        return view;
    }

    public Lote newLote(View view){

        String produto  = ((TextInputEditText) view.findViewById(R.id.produto)).getText().toString();
        int altura      = Integer.parseInt(((TextInputEditText) view.findViewById(R.id.altura)).getText().toString());
        int lastro      = Integer.parseInt(((TextInputEditText)view.findViewById(R.id.lastro)).getText().toString());

        return new Lote(altura,lastro, produto, "");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TelaAddLoteListener) context;
    }

    private void selectedImageListener(final ImageView image){
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.uploadImage(image);
            }
        });
    }
}
