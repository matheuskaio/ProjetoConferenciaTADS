package com.example.marcio.a3mconf.view.componet;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;

import java.util.List;

import control.Carga;
import control.Lote;

public class CargaListViewAdapter extends BaseAdapter {
    private final List<Carga> cargas;
    private final Activity activity;

    public CargaListViewAdapter(List<Carga> cargas, Activity activity) {
        this.cargas = cargas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return cargas.size();
    }

    @Override
    public Object getItem(int position) {
        return cargas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.carga_item_list,parent,false);
        TextView nome = view.findViewById(R.id.lista_cargas_personalizada_nome);
        TextView descrica = view.findViewById(R.id.lista_cargas_personalizada_descricao);
        Carga carga = cargas.get(position);
        nome.setText(carga.toString());
        descrica.setText("Descricao");
        return view;
    }
}