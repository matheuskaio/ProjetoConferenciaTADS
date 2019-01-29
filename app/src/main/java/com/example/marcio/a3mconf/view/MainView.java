package com.example.marcio.a3mconf.view;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.fragment.*;
import com.example.marcio.a3mconf.view.listeners.*;

import model.Carga;
import model.Conferente;
import model.Funcionario;
import model.Lote;
import model.Motorista;

public class MainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,TrocaDeTelasListener {

        private Toolbar toolbar;
        private Funcionario funcionario;
        private ActionBarDrawerToggle toggle;
        private DrawerLayout drawer;
        private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        initView();

    }

    private void initView(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        funcionario = (Funcionario) getIntent().getSerializableExtra("funcionario");
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        TextView nameFuncionario = navigationView.getHeaderView(0).findViewById(R.id.name_user);
//        nameFuncionario.setText(funcionario.getNome());
        if(funcionario instanceof Conferente){
            mostrarItemMenuConferente();
        }else if(funcionario instanceof Motorista){
            mostrarItemMenuMotorista();
        }else {
            mostrarItemMenuGerente();
        }
        openTela(new FragmentHome());

    }

    private void mostrarItemMenuConferente(){
        MenuItem addExpedicao,initConferencias,myConferencias,addCaminhao;

        addExpedicao    = navigationView.getMenu().findItem(R.id.nav_add_expedicao);
        initConferencias= navigationView.getMenu().findItem(R.id.nav_init_conference);
        myConferencias  = navigationView.getMenu().findItem(R.id.nav_my_conferences);
        addCaminhao     = navigationView.getMenu().findItem(R.id.nav_add_caminhao);

        addExpedicao.setVisible(true);
        initConferencias.setVisible(true);
        myConferencias.setVisible(true);
        addCaminhao.setVisible(true);
    }

    private void mostrarItemMenuMotorista(){
        MenuItem myCargas = navigationView.getMenu().findItem(R.id.nav_my_cargas);
        myCargas.setVisible(true);
    }

    private void mostrarItemMenuGerente(){
        MenuItem addFuncionario,conferencias,relatorio;

        addFuncionario  = navigationView.getMenu().findItem(R.id.nav_add_funcionario);
        conferencias    = navigationView.getMenu().findItem(R.id.nav_conferences);
        relatorio       = navigationView.getMenu().findItem(R.id.nav_report);

        addFuncionario.setVisible(true);
        conferencias.setVisible(true);
        relatorio.setVisible(true);
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_profile:
                // Handle the camera action
                openTela(new FragmentProfile());
                break;
            case R.id.nav_add_funcionario:
                openTela(new FragmentAddFunc());
                break;
            case R.id.nav_add_expedicao:
                openTela(new FragmentAddExpedicao());
                break;
            case R.id.nav_add_caminhao:
                openTela(new FragmentAddCaminhao());
                break;
            case R.id.nav_init_conference:
                openTela(new FragmentInitConference());
                break;
            case R.id.nav_my_conferences:
                openTela(new FragmentMyConferences());
                break;
            case R.id.nav_conferences:
                openTela(new FragmentConferences());
                break;
            case R.id.nav_report:
                openTela(new FragmentReport());
                break;
            case R.id.nav_my_cargas:
                openTela(new FragmentMyCargas());
                break;
            case R.id.nav_about:
                openTela(new FragmentAbout());
                break;
            case R.id.nav_sair:
                finishAffinity();
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void openTelaAddLote() {
        openTela(new FragmentAddLote());
    }


    @Override
    public void openTelaLote(Lote lote) {
        FragmentLote fragmentLote = new FragmentLote();
        Bundle bundleLote = new Bundle();
        bundleLote.putSerializable("lote",lote);
        fragmentLote.setArguments(bundleLote);
        openTela(fragmentLote);
    }

    @Override
    public void openTelaHome() {
        openTela(new FragmentHome());
    }

    @Override
    public void openTelaNovaConferencia() {
        openTela(new FragmentInitConference());
    }

    @Override
    public void addLote(Lote lote) {
        ((Conferente) funcionario).addLote(lote);
        openTela(new FragmentInitConference());
    }

    public void openTela(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, passFuncionario(fragment)).commit();
    }

    private Fragment passFuncionario(Fragment fragment){
        if(fragment instanceof FragmentLote || fragment instanceof FragmentConference){
            return fragment;
        }
        Bundle bundleFuncionario = new Bundle();
        bundleFuncionario.putSerializable("funcionario",funcionario);
        fragment.setArguments(bundleFuncionario);
        return fragment;
    }

    @Override
    public void openTelaConference(Carga carga) {
        FragmentConference fragmentConference = new FragmentConference();
        Bundle bundleConference = new Bundle();
        bundleConference.putSerializable("carga",carga);
        fragmentConference.setArguments(bundleConference);
        openTela(fragmentConference);
    }

}
