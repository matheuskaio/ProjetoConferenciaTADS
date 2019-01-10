package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.MainView;
import com.example.marcio.a3mconf.view.listeners.TelaInitConferenceListener;

import java.util.ArrayList;
import java.util.List;

import control.Carga;
import control.Lote;

public class FragmentInitConference extends Fragment {
    private TelaInitConferenceListener listener;
    private List<Lote> lotes;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_init_conference,container,false);
       Button btnOpenTela = view.findViewById(R.id.add_lote);
       Button btnFinalizar = view.findViewById(R.id.finalizar_conferencia);
       btnOpenTela.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.openTelaAddLote();
           }
       });
       btnFinalizar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.finalizar();
           }
       });
       ListView lista = view.findViewById(R.id.lista_de_lotes);
       lotes = listener.getLotes();
       ArrayAdapter<Lote> lotesAdapter = new ArrayAdapter<Lote>(this.getContext(),android.R.layout.simple_list_item_1,lotes);
       lista.setAdapter(lotesAdapter);
       return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TelaInitConferenceListener) context;
    }
}
