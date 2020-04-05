package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.login.models.GithubRepo;
import com.example.login.services.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit  retrofit =  new Retrofit.Builder().
                baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        Client client  =retrofit.create(Client.class);

        Call<List<GithubRepo>>  call =  client.getReposForUser("albertoandroid");


        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {

                for(int i  = 0 ;  i < response.body().size() ;  i++){
                    GithubRepo repo =  response.body().get(i);
                    Log.d("TAG1" , "Respositorio "+i+" Nombre: "+repo.getName());
                }

            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Log.d("TAG" , "ERROR "+t.getMessage().toString());
            }
        });


        btnLogin =  findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext() ,  MenuIcons.class);
//                startActivity(i);
                Intent principal = new Intent(getApplicationContext() , Principal.class);
                startActivity(principal);
            }
        });
    }
}
