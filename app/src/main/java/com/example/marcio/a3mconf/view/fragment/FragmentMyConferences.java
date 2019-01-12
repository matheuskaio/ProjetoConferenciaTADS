package com.example.marcio.a3mconf.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.marcio.a3mconf.R;

import control.Carga;
import control.Conferente;
import control.Lote;

public class FragmentMyConferences extends Fragment {
    private Conferente conferente;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_conferences,container,false);
        conferente = (Conferente) getArguments().getSerializable("funcionario");
        ListView lista = view.findViewById(R.id.lv_my_conferences);
        ArrayAdapter<Carga> lotesAdapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_list_item_1,conferente.myCargas());
        lista.setAdapter(lotesAdapter);
        return view;
    }
}
