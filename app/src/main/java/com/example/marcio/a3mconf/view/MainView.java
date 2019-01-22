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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.fragment.*;
import com.example.marcio.a3mconf.view.listeners.*;

import model.Carga;
import model.Conferente;
import model.Funcionario;
import model.Lote;

public class MainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TelaInitConferenceListener,TelaAddLoteListener,TelaConferencesListener {

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

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
