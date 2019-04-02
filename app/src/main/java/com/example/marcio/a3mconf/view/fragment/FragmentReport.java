package com.example.marcio.a3mconf.view.fragment;

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

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.componet.CargaListViewAdapter;

import java.util.ArrayList;
import java.util.List;

import control.GerenteIndirection;
import model.Caminhao;
import model.Conferente;
import model.Motorista;

public class FragmentReport extends Fragment {
    private List<String> types;
    private Spinner type,reference;
    private GerenteIndirection gerente;
    private Button generateReport;
    private ListView reports;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Relatório");
        View view = inflater.inflate(R.layout.fragment_report,container,false);
        gerente = GerenteIndirection.getInstance();
        types = new ArrayList<>();
        types.add("Caminhao");
        types.add("Conferente");
        types.add("Expedicao");
        types.add("Motorista");

        type            = view.findViewById(R.id.report_type);
        reports         = view.findViewById(R.id.report_list);
        reference       = view.findViewById(R.id.report_reference);
        generateReport  = view.findViewById(R.id.generate_report);

        ArrayAdapter<String> listType = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,types);
        type.setAdapter(listType);
        final ArrayAdapter<Caminhao> listCaminhao = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,gerente.allCaminhoes());
        final ArrayAdapter<Conferente> listConferentes = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,gerente.allConferentes());
        final ArrayAdapter<Motorista> listMotoristas = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_dropdown_item,gerente.allMotoristas());

        reference.setAdapter(listCaminhao);



        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(type.getSelectedItem().equals(types.get(0))){
                    reference.setAdapter(listCaminhao);
                }else if(type.getSelectedItem().equals(types.get(1))){
                    reference.setAdapter(listConferentes);
                }else if(type.getSelectedItem().equals(types.get(2))){
                    reference.setAdapter(null);
                }else{
                    reference.setAdapter(listMotoristas);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        generateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargaListViewAdapter listCargas = null;
                if(reference.getSelectedItem() instanceof Conferente){
                    listCargas = new CargaListViewAdapter(((Conferente) reference.getSelectedItem()).myCargas(),getActivity());
                }else if(reference.getSelectedItem() instanceof Motorista){
                    listCargas = new CargaListViewAdapter(((Motorista) reference.getSelectedItem()).myCargas(),getActivity());
                }else if(reference.getSelectedItem() instanceof String){
                    listCargas = new CargaListViewAdapter(null,getActivity());
                }else{
                    listCargas = new CargaListViewAdapter(((Caminhao) reference.getSelectedItem()).cargas(),getActivity());
                }
                reports.setAdapter(listCargas);
            }
        });

        return view;
    }
}
