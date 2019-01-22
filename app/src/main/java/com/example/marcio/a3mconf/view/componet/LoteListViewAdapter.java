package com.example.marcio.a3mconf.view.componet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;

import java.util.List;

import model.Lote;

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
        ImageView image = view.findViewById(R.id.lista_lotes_personalizada_imagem);
        Lote lote = lotes.get(position);
        nome.setText(lote.toString());
        descrica.setText(lote.getObservacao());
        image.setImageBitmap(getImage(lote.getFotoAltura()));
        return view;
    }
    private Bitmap getImage(String path){
        path = path.replace(" ","");
        Log.e("image",path);

        byte[] decodedString = Base64.decode(path, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    }
}
