package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.listeners.TelaAddLoteListener;

import control.Lote;

public class FragmentAddLote extends Fragment {

    private TelaAddLoteListener listener;
    private ImageView imageAltura;
    private ImageView imageLote;
    private Button btnAdd;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add_lote,container,false);

        setQuantidade((TextView) view.findViewById(R.id.quantidade));

        btnAdd = view.findViewById(R.id.adicionar);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.addLote(newLote(view));
            }
        });

        imageAltura = view.findViewById(R.id.imageAlturaUpload);
        setUploadImageListener(imageAltura);

        imageLote = view.findViewById(R.id.imageLastroUpload);
        setUploadImageListener(imageLote);


        return view;
    }

    public Lote newLote(View view){

        TextInputEditText produto   = view.findViewById(R.id.produto);
        TextInputEditText altura    = view.findViewById(R.id.altura);
        TextInputEditText lastro    = view.findViewById(R.id.lastro);
        TextInputEditText unMedida  = view.findViewById(R.id.un_medida);

        Lote lote = new Lote();
        lote.setNomeProduto(produto.getText().toString());
        lote.setAltura(Integer.parseInt(altura.getText().toString()));
        lote.setLastro(Integer.parseInt(lastro.getText().toString()));

        return lote;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TelaAddLoteListener) context;
    }

    private void setUploadImageListener(final ImageView image){
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.uploadImage(image);
                Log.e("teste","funcionou");
            }
        });
    }

    private void setQuantidade(TextView quantidade){
        quantidade.setText(""+getArguments().getInt("quantidade"));
    }
}
