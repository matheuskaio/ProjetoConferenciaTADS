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
import android.widget.ListView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.componet.CargaListViewAdapter;
import com.example.marcio.a3mconf.view.listeners.TrocaDeTelasListener;

import control.FuncionarioControl;
import control.GerenteIndirection;
import model.Carga;
import model.Gerente;

public class FragmentConferences extends Fragment {
    private TrocaDeTelasListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conferences,container,false);
        getActivity().setTitle("Conferências");
        final ListView lista = view.findViewById(R.id.lv_all_conferences);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.openTelaConference((Carga) parent.getAdapter().getItem(position));
            }
        });
        CargaListViewAdapter cargasAdapter = new CargaListViewAdapter(GerenteIndirection.getInstance().allCargas(),getActivity());
        lista.setAdapter(cargasAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TrocaDeTelasListener) context;
    }
}
