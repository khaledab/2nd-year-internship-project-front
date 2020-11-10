package com.tp3.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
Button a ;
EditText a1,a2,a3,a4,a5,a6,a7,a8,a9,a10,a11,a12,a13,a14,a15,a16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addlicence);
        a = findViewById(R.id.material_button);
        a1=(EditText) findViewById(R.id.add1);
        a2=(EditText) findViewById(R.id.add2);
        a3=(EditText) findViewById(R.id.add3);
        a4=(EditText) findViewById(R.id.add4);
        a5=(EditText) findViewById(R.id.add5);
        a6=(EditText) findViewById(R.id.add6);
        a7=(EditText) findViewById(R.id.add7);
        a8=(EditText) findViewById(R.id.add8);
        a9=(EditText) findViewById(R.id.add9);
        a10=(EditText) findViewById(R.id.add10);
        a11=(EditText) findViewById(R.id.add11);
        a12=(EditText) findViewById(R.id.add12);
        a13=(EditText) findViewById(R.id.add13);
        a14=(EditText) findViewById(R.id.add14);
        a15=(EditText) findViewById(R.id.add15);
        a16=(EditText) findViewById(R.id.add16);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

       a.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               StringRequest rq =new StringRequest(StringRequest.Method.POST,"http://192.168.43.243:3000/Licences/create", new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {

                   }
               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       Toast.makeText(Main2Activity.this," "+error,Toast.LENGTH_LONG).show();
                   }
               }){
                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError{
                       Map<String,String> params=new HashMap<String, String>();
                       try {

                           params.put("enseigne", a1.getText().toString());
                           params.put("site",a2.getText().toString());
                           params.put("siret",a3.getText().toString());
                           params.put("code_naf",a4.getText().toString());
                           params.put("code_tva",a5.getText().toString());
                           params.put("phone",a6.getText().toString());
                           params.put("adress",a7.getText().toString());
                           params.put("zip_code",a8.getText().toString());
                           params.put("ville",a9.getText().toString());
                           params.put("pays",a10.getText().toString());
                           params.put("nb_postes",a11.getText().toString());
                           params.put("duree_utilisation",a12.getText().toString());
                           params.put("client_email",a13.getText().toString());
                           params.put("client_pwd",a14.getText().toString());
                           params.put("exercice",a15.getText().toString());
                           params.put("email",a16.getText().toString());

                           return params;
                       }catch(Exception e){

                        return null;
                       }
                   }
               };
               Volley.newRequestQueue(Main2Activity.this).add(rq);



                   new MaterialAlertDialogBuilder(Main2Activity.this)
                           .setTitle("doneeeeee")
                           .setMessage("worked")
                           .setPositiveButton("All Licences", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   final Intent in=new Intent(Main2Activity.this,Main22Activity.class);
                                   startActivity(in);
                               }
                           }).setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           final Intent in=new Intent(Main2Activity.this,MainActivity.class);
                           String url="http://192.168.43.243:3000/Licences";
                           StringRequest rq=new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
                               @Override
                               public void onResponse(String response) {
                                   try {
                                       JSONArray array = new JSONArray(response);
                                       in.putExtra("numberLicence",array.length());

                                   } catch (JSONException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }, new Response.ErrorListener() {
                               @Override
                               public void onErrorResponse(VolleyError error) {
                                   //     Toast.makeText(Main2Activity.this," "+error,Toast.LENGTH_LONG).show();
                               }
                           });
                           startActivity(in);
                       }
                   })
                           .show();



           }
       });
        freeall();
    }

public void freeall(){
    a1.setText(null);
    a2.setText(null);
    a3.setText(null);
    a4.setText(null);
    a5.setText(null);
    a6.setText(null);
    a7.setText(null);
    a8.setText(null);
    a9.setText(null);
    a10.setText(null);
    a11.setText(null);
    a12.setText(null);
    a13.setText(null);
    a14.setText(null);
    a15.setText(null);
    a16.setText(null);


}
}
