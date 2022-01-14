package com.example.tareaa5_navigationview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Button btnIngresar;
    EditText txtuser;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnIngresar=(Button) findViewById(R.id.btnIniciarSesion);
        txtuser=(EditText) findViewById(R.id.txtuser);
        txtPassword=(EditText) findViewById(R.id.txtpassword);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtuser.getText().length()>0 & txtPassword.getText().length()>0) {
                    ExtracciónDeDatosVolley();
                }else{ Toast toast = Toast.makeText(getApplicationContext(), "Porfavor ingrese usuario y contraseña", Toast.LENGTH_SHORT);
                    toast.show();}

            }
        });


    }


    public void ExtracciónDeDatosVolley() {

        RequestQueue RequestQueue = Volley.newRequestQueue(getApplicationContext());
        String urlJSON = "https://my-json-server.typicode.com/mmendezg2/usuariosJSON/db";
        JsonObjectRequest JsonOR = new JsonObjectRequest(Request.Method.GET,
                urlJSON, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray U = response.getJSONArray("users");
                    for (int i = 0; i < U.length(); i++) {

                        JSONObject info= new JSONObject(U.get(i).toString());
                        String usuario, contrasenia;
                        usuario=info.getString("username");
                        contrasenia=info.getString("password");

                        if(usuario.equals(txtuser.getText().toString()) & contrasenia.equals(txtPassword.getText().toString()))
                        {
                            String a;
                            a=info.getString("username");
                            Intent is=new Intent(MainActivity.this, Menuusuarios.class);
                            is.putExtra("username", info.getString("username"));
                            is.putExtra("password", info.getString("password"));
                            is.putExtra("roleid", info.getString("roleid"));
                            is.putExtra("img", info.getString("img"));
                            startActivity(is);



                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }
        );
        RequestQueue.add(JsonOR);


    }



}