package com.example.marcio.a3mconf.view.componet;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;

import java.util.List;

import control.Lote;

public class LoteListViewAdapter extends BaseAdapter {
    private final List<Lote> lotes;
    private final Activity activity;

    public LoteListViewAdapter(List<Lote> lotes, Activity activity) {
        this.lotes = lotes;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lotes.size();
    }

    @Override
    public Object getItem(int position) {
        return lotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.lote_item_list,parent,false);
        TextView nome = view.findViewById(R.id.lista_lotes_personalizada_nome);
        TextView descrica = view.findViewById(R.id.lista_lotes_personalizada_descricao);
        Lote lote = lotes.get(position);
        nome.setText(lote.toString());
        descrica.setText("Descricao");
        return view;
    }
}
