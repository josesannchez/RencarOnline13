package sanchezartega.facci.rencar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import sanchezartega.facci.rencar.clases.Utilidades;
import sanchezartega.facci.rencar.fragments.ContenedorFragment;
import sanchezartega.facci.rencar.fragments.FormularioFragment;
import sanchezartega.facci.rencar.fragments.GreenFragment;
import sanchezartega.facci.rencar.fragments.ListaPersonajesFragment;
import sanchezartega.facci.rencar.fragments.RedFragment;
import sanchezartega.facci.rencar.lista3.listaAAA;

public  class MainActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener, RedFragment.OnFragmentInteractionListener,
            GreenFragment.OnFragmentInteractionListener, FormularioFragment.OnFragmentInteractionListener,
            ListaPersonajesFragment.OnFragmentInteractionListener, ContenedorFragment.OnFragmentInteractionListener {

        private RecyclerView recycler;
        private LinearLayoutManager lManager;
        private CollapsingToolbarLayout collapser;

    private TextView nombre,correo;

        public static final String user="names";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();
            if (Utilidades.validaPantalla==true){
                Fragment fragment=new FormularioFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_mai,fragment).commit();
                Utilidades.validaPantalla=false;

            }
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setItemIconTintList(null);
            navigationView.setNavigationItemSelectedListener(this);



        }

        private void setToolbar() {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            Intent intent;
            int id = item.getItemId();
            switch (id) {
                case R.id.nav_slideshow:
                    FirebaseAuth.getInstance().signOut();
                    intent =new Intent(sanchezartega.facci.rencar.MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.nav_view:
                    showSnackBar("Se abren los ajustes");
                    finish();
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }

        private void showSnackBar(String msg) {
            Snackbar
                    .make(findViewById(R.id.coordinator), msg, Snackbar.LENGTH_LONG)
                    .show();

        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            // Handle navigation view item clicks here.
            int id = item.getItemId();
            Fragment miFragment=null;
            boolean fragmentSeleccionado=false;

            if (id == R.id.home) {
                Intent intent = new Intent(sanchezartega.facci.rencar.MainActivity.this, listaAAA.class);
                startActivity(intent);

            }  else if (id == R.id.nav_slideshow) {
                miFragment=new RedFragment();
                fragmentSeleccionado=true;

            }else if (id == R.id.nav_view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(sanchezartega.facci.rencar.MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
            if (fragmentSeleccionado==true) {
                getSupportFragmentManager().beginTransaction().replace(R.id.content_mai, miFragment).commit();

            }
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }

        @Override
        public void onFragmentInteraction(Uri uri) {

        }
    }
