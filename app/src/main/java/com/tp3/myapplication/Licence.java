package com.tp3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Licence extends AppCompatActivity {
    TextView enseigne;
    TextView site;
    TextView siret;
    TextView code_naf;
    TextView code_tva;
    TextView phone;
    TextView adress;
    TextView zip;
    TextView ville;
    TextView pays;
    TextView nb;
    TextView duree;
    TextView email;
    TextView pwd;
    TextView licence;
    TextView code_licence;
    TextView exercice;
    String id;
    Button update,supprimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.licence);
        Intent intent = getIntent();
        update = (Button) findViewById(R.id.update);
        supprimer = (Button) findViewById(R.id.delete);
        enseigne = (TextView)findViewById(R.id.ens);
        site = (TextView)findViewById(R.id.site);
        siret = (TextView)findViewById(R.id.siret);
        code_naf = (TextView)findViewById(R.id.codenaf);
        code_tva = (TextView)findViewById(R.id.codetva);
        phone = (TextView)findViewById(R.id.phone);
        adress = (TextView)findViewById(R.id.adress);
        zip = (TextView)findViewById(R.id.zip);
        ville = (TextView)findViewById(R.id.ville);
        pays = (TextView)findViewById(R.id.pays);
        nb = (TextView)findViewById(R.id.nb);
        duree = (TextView)findViewById(R.id.duree);
        email = (TextView)findViewById(R.id.email);
        pwd = (TextView)findViewById(R.id.pwd);
        licence = (TextView)findViewById(R.id.licence);
        code_licence = (TextView)findViewById(R.id.code);
        exercice = (TextView)findViewById(R.id.exer);

        final String id = intent.getStringExtra("Id");

        String url="http://192.168.43.243:3000/Licences/"+getIntent().getStringExtra("Id");
        StringRequest req=new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object=new JSONObject(response);
                    JSONObject obj=new JSONObject(object.getString("product"));
                    enseigne.setText(obj.getString("enseigne"));
                    site.setText(obj.getString("site"));
                    siret.setText(obj.getString("siret"));
                    code_naf.setText(obj.getString("code_naf"));
                    code_tva.setText(obj.getString("code_tva"));
                    phone.setText(obj.getString("phone"));
                    adress.setText(obj.getString("adress"));
                    zip.setText(obj.getString("zip_code"));
                    ville.setText(obj.getString("ville"));
                    pays.setText(obj.getString("pays"));
                    nb.setText(obj.getString("nb_postes"));
                    duree.setText(obj.getString("duree_utilisation"));
                    email.setText(obj.getString("client_email"));
                    pwd.setText(obj.getString("client_pwd"));
                    licence.setText(obj.getString("licence"));
                    code_licence.setText(obj.getString("code_licence"));
                    exercice.setText(obj.getString("exercice"));


                }catch (Exception ex){
                    Toast.makeText(Licence.this,ex+" ",Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Licence.this,error+" ",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(req);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent in=new Intent(Licence.this,update.class);
                in.putExtra("id",id);
                startActivity(in);
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialAlertDialogBuilder(Licence.this)
                        .setTitle("Verification")
                        .setMessage("You are sure u want to delete this licence")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url="http://192.168.43.243:3000/Licences/"+getIntent().getStringExtra("Id");
                                StringRequest rq=new StringRequest(StringRequest.Method.DELETE, url, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        JSONArray array = null;

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                });
                                Volley.newRequestQueue(Licence.this).add(rq);
                                final Intent in=new Intent(Licence.this,Main22Activity.class);
                                startActivity(in);
                            }
                        }).setNegativeButton(android.R.string.no, null)
                        .show();

            }
        });

    }
}
