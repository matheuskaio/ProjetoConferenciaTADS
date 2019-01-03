package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.listeners.TelaInitConferenceListener;

public class FragmentInitConference extends Fragment {
    private TelaInitConferenceListener addLoteListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_init_conference,container,false);
       Button btn = view.findViewById(R.id.add_lote);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               addLoteListener.openTela();
           }
       });
       return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        addLoteListener = (TelaInitConferenceListener) context;
    }
}
