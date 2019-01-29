package com.example.marcio.a3mconf.view.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.listeners.TrocaDeTelasListener;

import java.util.ArrayList;
import java.util.List;

import model.Conferente;
import model.Expedicao;

public class FragmentAddExpedicao extends Fragment {
    private ImageButton btnAddExpedicao;
    private Button btnCadastrar,btnCancelar;
    private LinearLayout linearLayout;
    private TextInputEditText cidade1, nameExpedicao;
    private int cont;
    private List<TextInputEditText> cidades;
    private Conferente conferente;
    private TrocaDeTelasListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_expedicao,container,false);
        getActivity().setTitle("Adicionar Expedição");
        conferente      = (Conferente) getArguments().getSerializable("funcionario");
        cidades         = new ArrayList<>();
        linearLayout    = view.findViewById(R.id.container_expedicao);
        btnAddExpedicao = view.findViewById(R.id.btn_add_expedicao);
        btnCancelar     = view.findViewById(R.id.cancelar_cadastro_expedicao);
        btnCadastrar    = view.findViewById(R.id.cardastrar_expedicao);
        cidade1         = view.findViewById(R.id.cidade1);
        nameExpedicao   = view.findViewById(R.id.expedicao);
        cont = 2;
        btnAddExpedicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createLayout();

            }
        });
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Expedicao expedicao = new Expedicao(new ArrayList<String>(), nameExpedicao.getText().toString());
                expedicao.add(cidade1.getText().toString());
                for (TextInputEditText cidade:cidades){
                    expedicao.add(cidade.getText().toString());
                }
                conferente.cadastraExpedicao(expedicao);
                listener.openTelaHome();
            }
        });
        return view;
    }
    private void createLayout(){

        LinearLayout cidade     = new LinearLayout(getContext());
        TextInputEditText nome  = new TextInputEditText(getContext());
        TextView descricao      = new TextView(getContext());
        TextInputLayout layout  = new TextInputLayout(getContext());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        descricao.setTextColor(Color.WHITE);
        descricao.setTextSize(30);
        descricao.setText("Cidade "+(cont ++)+": ");

        nome.setLayoutParams(params);

        cidades.add(nome);

        layout.setLayoutParams(params);
        layout.addView(nome);
        layout.setBackgroundResource(R.drawable.bg_edit_text);

        cidade.setLayoutParams(params);
        cidade.addView(descricao);
        cidade.addView(layout);

        linearLayout.addView(cidade);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TrocaDeTelasListener) context;
    }
}
