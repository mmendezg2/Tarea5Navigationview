package com.example.tareaa5_navigationview;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tareaa5_navigationview.databinding.ActivityMenuusuariosBinding;
import com.squareup.picasso.Picasso;

public class Menuusuarios extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMenuusuariosBinding binding;

    String usuario;
    String idroller;
    String url, idroluser;
    ImageView imagen;
    TextView txtusuarito;
    NavigationView NavigationV1, NavigationV2;
    MenuItem menuI1, menuI2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Validación de usuarios
        //idroler=1// administrador
        //idroler=2 //usuarios
        usuario=getIntent().getStringExtra("username");
        url=getIntent().getStringExtra("img");
        idroluser=getIntent().getStringExtra("roleid");

        binding = ActivityMenuusuariosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMenuusuarios.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.item_info, R.id.item_users,R.id.item_product, R.id.item_ajustes, R.id.item_acerca, R.id.item_cs)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menuusuarios);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        Toast mensaje = Toast.makeText(getApplicationContext(), "Ingreso correcto", Toast.LENGTH_SHORT);
        mensaje.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menuusuarios, menu);
        txtusuarito=findViewById(R.id.txtuser);
        txtusuarito.setText(usuario);

        if(idroluser.equals("2"))
        {
            NavigationV1 = (NavigationView)findViewById(R.id.nav_view);
            menuI1 =  NavigationV1.getMenu().findItem(R.id.item_users);
            menuI1.setVisible(false);

            NavigationV2 = (NavigationView)findViewById(R.id.nav_view);
            menuI2 =  NavigationV2.getMenu().findItem(R.id.item_product);
            menuI2.setVisible(false);
        }

        imagen=findViewById(R.id.imgUser);
        Picasso.get().load(url).resize(150, 150).centerCrop().into(imagen);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_menuusuarios);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}