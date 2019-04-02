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
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.fragment.*;
import com.example.marcio.a3mconf.view.listeners.*;
import com.squareup.picasso.Picasso;

import control.FuncionarioControl;
import model.Carga;
import model.Conferente;
import model.Funcionario;
import model.Lote;
import model.Motorista;
import model.request.Connection;

public class MainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,TrocaDeTelasListener {

        private Toolbar toolbar;
        private ActionBarDrawerToggle toggle;
        private DrawerLayout drawer;
        private NavigationView navigationView;
        private FuncionarioControl funcionario;
        private boolean home,addlote,lote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        initView();

    }

    private void initView(){
        home = true;
        addlote = false;
        toolbar     = findViewById(R.id.toolbar);
        drawer      = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        funcionario = FuncionarioControl.getIstace();

        setSupportActionBar(toolbar);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        View head = navigationView.getHeaderView(0);
        TextView nameFuncionario    = head.findViewById(R.id.name_user);
        ImageView imageProfile      = head.findViewById(R.id.image_head_profile);

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTela(new FragmentProfile());
                drawer.closeDrawer(GravityCompat.START);
            }
        });
        nameFuncionario.setText(funcionario.getFuncionario().getNome());
        String fotoPerfil = funcionario.getFuncionario().getFoto();
        if(fotoPerfil!= null){
            Picasso.get().load(Connection.URL+fotoPerfil+".jpg").into(imageProfile);
        }
        if(funcionario.isConferente()){
            mostrarItemMenuConferente();
        }else if(funcionario.isMotorista()){
            mostrarItemMenuMotorista();
        }else {
            mostrarItemMenuGerente();
        }
        openTela(new FragmentHome());

    }

    private void mostrarItemMenuConferente(){
        MenuItem initConferencias,myConferencias,addCaminhao;

        initConferencias= navigationView.getMenu().findItem(R.id.nav_init_conference);
        myConferencias  = navigationView.getMenu().findItem(R.id.nav_my_conferences);
        addCaminhao     = navigationView.getMenu().findItem(R.id.nav_add_caminhao);

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
            if(home){
                this.moveTaskToBack(true);
            }else if(addlote){
                openTela(new FragmentInitConference());
            }else{
                openTela(new FragmentHome());
            }
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_profile:
                // Handle the camera action
                home =false;
                openTela(new FragmentProfile());
                break;
            case R.id.nav_add_funcionario:
                home =false;
                openTela(new FragmentAddFunc());
                break;
            case R.id.nav_add_caminhao:
                home =false;
                openTela(new FragmentAddCaminhao());
                break;
            case R.id.nav_init_conference:
                home =false;
                openTela(new FragmentInitConference());
                break;
            case R.id.nav_my_conferences:
                home =false;
                openTela(new FragmentMyConferences());
                break;
            case R.id.nav_conferences:
                home =false;
                openTela(new FragmentConferences());
                break;
            case R.id.nav_report:
                home =false;
                openTela(new FragmentReport());
                break;
            case R.id.nav_my_cargas:
                home =false;
                openTela(new FragmentMyCargas());
                break;

            case R.id.nav_about:
                home =false;
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
        addlote = true;
        openTela(new FragmentAddLote());
    }

    @Override
    public void openTelaLote(Lote lote) {
        addlote = false;
        FragmentLote fragmentLote = new FragmentLote();
        Bundle bundleLote = new Bundle();
        bundleLote.putSerializable("lote",lote);
        fragmentLote.setArguments(bundleLote);
        openTela(fragmentLote);
    }

    @Override
    public void openTelaHome() {
        addlote = false;
        openTela(new FragmentHome());
    }

    @Override
    public void openTelaNovaConferencia() {
        addlote = false;
        openTela(new FragmentInitConference());
    }


    public void openTela(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }


    @Override
    public void openTelaConference(Carga carga) {
        addlote = false;
        FragmentConference fragmentConference = new FragmentConference();
        Bundle bundleConference = new Bundle();
        bundleConference.putSerializable("carga",carga);
        fragmentConference.setArguments(bundleConference);
        openTela(fragmentConference);
    }

}
