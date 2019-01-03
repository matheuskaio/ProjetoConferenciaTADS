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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.fragment.FragmentAbout;
import com.example.marcio.a3mconf.view.fragment.FragmentAddLote;
import com.example.marcio.a3mconf.view.fragment.FragmentConference;
import com.example.marcio.a3mconf.view.fragment.FragmentInitConference;
import com.example.marcio.a3mconf.view.fragment.FragmentMyConference;
import com.example.marcio.a3mconf.view.fragment.FragmentProfile;
import com.example.marcio.a3mconf.view.fragment.FragmentReport;
import com.example.marcio.a3mconf.view.listeners.TelaInitConferenceListener;

import control.Funcionario;

public class MainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TelaInitConferenceListener {
        Toolbar toolbar;
        private Funcionario funcionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentProfile()).commit();
                break;
            case R.id.nav_init_conference:
                Fragment fic = new FragmentInitConference();
                //((FragmentInitConference) fic).addListner(this);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fic).commit();
                break;
            case R.id.nav_my_conferences:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMyConference()).commit();
                break;
            case R.id.nav_conferences:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentConference()).commit();
                break;
            case R.id.nav_report:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentReport()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentAbout()).commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void openTela() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentAddLote()).commit();
    }

    @Override
    public void finalizar() {

    }
}
