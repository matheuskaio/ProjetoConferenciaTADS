package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.componet.CargaListViewAdapter;
import com.example.marcio.a3mconf.view.componet.LoteListViewAdapter;
import com.example.marcio.a3mconf.view.listeners.TelaAddLoteListener;
import com.example.marcio.a3mconf.view.listeners.TelaInitConferenceListener;

import control.Carga;
import control.Conferente;
import control.Lote;

public class FragmentConference extends Fragment {
    private Carga carga;
    private TelaInitConferenceListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conference,container,false);
        carga = (Carga) getArguments().getSerializable("carga");

        ListView lista = view.findViewById(R.id.conference_list_lotes);
        LoteListViewAdapter cargasAdapter = new LoteListViewAdapter(carga.getLotes(),getActivity());
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.openTelaLote((Lote) parent.getAdapter().getItem(position));

            }
        });
        lista.setAdapter(cargasAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TelaInitConferenceListener) context;
    }
}
