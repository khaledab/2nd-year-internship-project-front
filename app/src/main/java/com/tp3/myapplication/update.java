package com.tp3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class update extends AppCompatActivity {
    EditText enseigne;
    EditText site;
    EditText siret;
    EditText code_naf;
    EditText code_tva;
    EditText phone;
    EditText adress;
    EditText zip;
    EditText ville;
    EditText pays;
    EditText nb;
    EditText duree;
    EditText email;
    EditText pwd;
    EditText licence;
    EditText code_licence;
    EditText exercice;
    EditText etat;
    String id;
    Button confirmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Intent intent = getIntent();
        confirmer = (Button) findViewById(R.id.confirmer);
        enseigne = (EditText)findViewById(R.id.add1);
        siret = (EditText)findViewById(R.id.add2);
        code_naf = (EditText)findViewById(R.id.add3);
        code_tva = (EditText)findViewById(R.id.add4);
        phone = (EditText)findViewById(R.id.add5);
        adress = (EditText)findViewById(R.id.add6);
        zip = (EditText)findViewById(R.id.add7);
        ville = (EditText)findViewById(R.id.add8);
        pays = (EditText)findViewById(R.id.add9);
        nb = (EditText)findViewById(R.id.add10);
        duree = (EditText)findViewById(R.id.add11);
        email = (EditText)findViewById(R.id.add12);
        pwd = (EditText)findViewById(R.id.add13);
        site = (EditText)findViewById(R.id.add15);
        exercice = (EditText)findViewById(R.id.add16);

        final String id = intent.getStringExtra("id");

        String url="http://192.168.43.243:3000/Licences/"+id;
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
                    exercice.setText(obj.getString("exercice"));


                }catch (Exception ex){
                    Toast.makeText(update.this,ex+" ",Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(update.this,error+" ",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(req);




        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url="http://192.168.43.243:3000/Licences/"+id;
                StringRequest reqUp=new StringRequest(StringRequest.Method.PATCH, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(update.this,error+" ",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<String, String>();
                        try {
                            params.put("enseigne", enseigne.getText().toString());
                            params.put("site",site.getText().toString());
                            params.put("siret",siret.getText().toString());
                            params.put("code_naf",code_naf.getText().toString());
                            params.put("code_tva",code_tva.getText().toString());
                            params.put("phone",phone.getText().toString());
                            params.put("adress",adress.getText().toString());
                            params.put("zip_code",zip.getText().toString());
                            params.put("ville",ville.getText().toString());
                            params.put("pays",pays.getText().toString());
                            params.put("nb_postes",nb.getText().toString());
                            params.put("duree_utilisation",duree.getText().toString());
                            params.put("client_email",email.getText().toString());
                            params.put("client_pwd",pwd.getText().toString());
                            params.put("exercice",exercice.getText().toString());
                            params.put("email",email.getText().toString());

                            return params;
                        }catch(Exception e){

                            return null;
                        }
                    }
                };
                Volley.newRequestQueue(update.this).add(reqUp);
                new MaterialAlertDialogBuilder(update.this)
                        .setTitle("doneeeeee")
                        .setMessage("Licence modifieé")
                        .setPositiveButton("All Licences", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final Intent in=new Intent(update.this,Main22Activity.class);
                                startActivity(in);
                            }
                        }).setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                        .show();

            }
        });
    }
}
