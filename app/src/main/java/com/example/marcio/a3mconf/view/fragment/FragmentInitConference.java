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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.componet.LoteListViewAdapter;
import com.example.marcio.a3mconf.view.listeners.TrocaDeTelasListener;

import java.util.ArrayList;
import java.util.List;

import model.Caminhao;
import model.Conferente;
import model.Expedicao;
import model.Lote;
import model.Motorista;

public class FragmentInitConference extends Fragment {
    private TrocaDeTelasListener listener;
    private Conferente conferente;
    TextView mTextView;
    private Spinner expedicoes,caminhoes,motoristas;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_init_conference,container,false);

       mTextView = (TextView) view.findViewById(R.id.text);
       getActivity().setTitle("Realizar Conferência");
       conferente = (Conferente) getArguments().getSerializable("funcionario");

       final ListView lista = view.findViewById(R.id.lista_de_lotes);
       conferente.iniciarCoferencia();

        expedicoes   = view.findViewById(R.id.spinner_expedicoes);
        caminhoes    = view.findViewById(R.id.spinner_caminhoes);
        motoristas   = view.findViewById(R.id.spinner_motorista);

        Button btnOpenTela = view.findViewById(R.id.add_lote);
        Button btnFinalizar = view.findViewById(R.id.finalizar_conferencia);

        LoteListViewAdapter lotesAdapter = new LoteListViewAdapter(conferente.getCarga().getLotes(),getActivity());
        lista.setAdapter(lotesAdapter);


        btnOpenTela.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.openTelaAddLote();
           }
        });
        btnFinalizar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               conferente.getCarga().setExpedicao((Expedicao) expedicoes.getSelectedItem());
               conferente.getCarga().setCaminhao((Caminhao) caminhoes.getSelectedItem());
               conferente.finalizarConferencia(((Motorista) motoristas.getSelectedItem()).getCpf());

               Toast.makeText(getContext(),"Carga registrada com sucesso!",Toast.LENGTH_SHORT).show();
               lista.setAdapter(null);
               conferente.iniciarCoferencia();
           }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.openTelaLote((Lote) parent.getAdapter().getItem(position));

            }
        });

        List<Expedicao> listExpedicoes = conferente.expedicaos();
        ArrayAdapter<Expedicao> spinnerExpedicoesArrayAdapter = new ArrayAdapter<Expedicao>(getContext(), android.R.layout.simple_spinner_item,
                listExpedicoes);
        spinnerExpedicoesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        expedicoes.setAdapter(spinnerExpedicoesArrayAdapter);

        List<Caminhao> listCaminhoes = conferente.caminhoes();
        ArrayAdapter<Caminhao> spinnerCaminhoesArrayAdapter = new ArrayAdapter<Caminhao>(getContext(), android.R.layout.simple_spinner_item,
                listCaminhoes);
        spinnerCaminhoesArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        caminhoes.setAdapter(spinnerCaminhoesArrayAdapter);

        List<Motorista> listMotoristas = conferente.motoristas();
        ArrayAdapter<Motorista> spinnerMotoristaArrayAdapter = new ArrayAdapter<Motorista>(getContext(), android.R.layout.simple_spinner_item,
                listMotoristas);
        spinnerMotoristaArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        motoristas.setAdapter(spinnerMotoristaArrayAdapter);


       return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (TrocaDeTelasListener) context;
    }

}