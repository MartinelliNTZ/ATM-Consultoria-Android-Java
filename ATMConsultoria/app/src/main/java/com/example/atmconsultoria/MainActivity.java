package com.example.atmconsultoria;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.atmconsultoria.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mandarEmail();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_clientes, R.id.nav_servicos,R.id.nav_contato,R.id.nav_sobre)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void mandarEmail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"atendimento@atmconsultoria.com.br"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"Atendimento via email");
        intent.putExtra(Intent.EXTRA_TEXT,"Mensagem automatica");
        //define o tipo de arquivo que estou encaminhando
        //message/rfc822                define o tipo como email
        //text/plain                    define o  como texto
        //image/* ou image/png          define o  como image
        //define todos os tipos em myne types

        intent.setType("message/rfc822");

        startActivity(Intent.createChooser(intent,"Compartilhar"));
    }
    public void ligar(){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:18998204856"));
        startActivity(intent);
    }
    public void mandarImagem(){
        String imagem = "https://br.freepik.com/fotos-gratis/imagem-aproximada-em-tons-de-cinza-de-uma-aguia-careca-americana-em-um-fundo-escuro_13382096.htm#query=imagem&position=0&from_view=keyword";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imagem));
        startActivity(intent);
    }
    public void mandarGoogleMaps(){
        String endereco = "https://www.google.com/maps/place/Parque+Burac%C3%A3o/@-22.6669593,-50.4204909,15z/data=!4m5!3m4!1s0x9495397f7263eddf:0x1ecaa69e1b3e292c!8m2!3d-22.6580662!4d-50.4276427";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(endereco));
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}