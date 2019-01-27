package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.componet.LoteListViewAdapter;
import com.example.marcio.a3mconf.view.listeners.TrocaDeTelasListener;

import model.Conferente;
import model.Lote;

public class FragmentInitConference extends Fragment {
    private TrocaDeTelasListener listener;
    private Conferente conferente;
    TextView mTextView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_init_conference,container,false);

       mTextView = (TextView) view.findViewById(R.id.text);

       conferente = (Conferente) getArguments().getSerializable("funcionario");
       conferente.iniciarCoferencia();


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
               conferente.finalizarConferencia();
           }
       });
       ListView lista = view.findViewById(R.id.lista_de_lotes);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.openTelaLote((Lote) parent.getAdapter().getItem(position));

            }
        });
       LoteListViewAdapter lotesAdapter = new LoteListViewAdapter(conferente.getCarga().getLotes(),getActivity());
       lista.setAdapter(lotesAdapter);

       return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TrocaDeTelasListener) context;
    }

}