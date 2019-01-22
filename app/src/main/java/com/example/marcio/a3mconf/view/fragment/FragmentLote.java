package com.example.marcio.a3mconf.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;

import model.Lote;

public class FragmentLote extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view               = inflater.inflate(R.layout.fragment_lote,container,false);
        Lote lote               = (Lote) getArguments().getSerializable("lote");
        TextView name           = view.findViewById(R.id.name_prod);
        TextView altura         = view.findViewById(R.id.altura_prod);
        TextView lastro         = view.findViewById(R.id.lastro_prod);
        ImageView imageAltura   = view.findViewById(R.id.image_altura_prod);
        ImageView imageLastro   = view.findViewById(R.id.image_lastro_prod);

        name.setText(lote.getNomeProduto());
        altura.setText(lote.getAltura()+"");
        lastro.setText(lote.getLastro()+"");
        imageAltura.setImageBitmap(getImage(lote.getFotoAltura()));
        imageLastro.setImageBitmap(getImage(lote.getFotoLastro()));

        return view;
    }
    private Bitmap getImage(String path){

        byte[] decodedString = Base64.decode(path, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

    }

}
