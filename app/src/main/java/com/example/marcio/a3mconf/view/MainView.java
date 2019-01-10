package com.example.marcio.a3mconf.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.marcio.a3mconf.R;
import com.example.marcio.a3mconf.view.fragment.FragmentAbout;
import com.example.marcio.a3mconf.view.fragment.FragmentAddLote;
import com.example.marcio.a3mconf.view.fragment.FragmentConference;
import com.example.marcio.a3mconf.view.fragment.FragmentInitConference;
import com.example.marcio.a3mconf.view.fragment.FragmentMyConference;
import com.example.marcio.a3mconf.view.fragment.FragmentProfile;
import com.example.marcio.a3mconf.view.fragment.FragmentReport;
import com.example.marcio.a3mconf.view.listeners.TelaAddLoteListener;
import com.example.marcio.a3mconf.view.listeners.TelaInitConferenceListener;

import org.apache.http.HttpConnection;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import control.Carga;
import control.Conferente;
import control.Funcionario;
import control.Lote;

public class MainView extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TelaInitConferenceListener,TelaAddLoteListener{

        private Toolbar toolbar;
        private Funcionario funcionario;
        private List<Lote> lotes;
        private ActionBarDrawerToggle toggle;
        private ImageView imageUpload;
        private DrawerLayout drawer;
        private NavigationView navigationView;

        public static final int RESULT_LOAD_IMAGE = 1;
        private static final String SERVER_ADDRESS = "localhost/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);

        initView();

    }

    private void initView(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        funcionario = new Conferente();

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
                lotes = new ArrayList<>();
                openTela(new FragmentInitConference());

                break;
            case R.id.nav_my_conferences:
                openTela(new FragmentMyConference());
                break;
            case R.id.nav_conferences:
                openTela(new FragmentConference());
                break;
            case R.id.nav_report:
                openTela(new FragmentReport());
                break;
            case R.id.nav_about:
                openTela(new FragmentAbout());
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void openTelaAddLote() {
        FragmentAddLote addLote = new FragmentAddLote();
        Bundle quantidade = new Bundle();
        quantidade.putInt("quantidade",lotes.size());
        quantidade.putString("teste","test");
        addLote.setArguments(quantidade);
        openTela(addLote);
    }

    @Override
    public void finalizar() {
        Carga carga = new Carga();
        ((Conferente) funcionario).finalizarConferencia(carga);

    }

    @Override
    public List<Lote> getLotes() {
        return lotes;
    }

    @Override
    public void setLote(List<Lote> lotes) {
        this.lotes = lotes;
    }

    @Override
    public void addLote(Lote lote) {
        this.lotes.add(lote);
        openTela(new FragmentInitConference());
    }

    @Override
    public void uploadImage(ImageView image) {
        imageUpload = image;
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedimage = data.getData();
            imageUpload.setImageURI(selectedimage);
        }
    }

    public void openTela(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

}
