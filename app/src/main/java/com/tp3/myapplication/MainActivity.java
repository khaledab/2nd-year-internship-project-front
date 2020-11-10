package com.tp3.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.chart.common.listener.Event;
import com.anychart.chart.common.listener.ListenersInterface;
import com.anychart.charts.Pie;
import com.anychart.enums.Align;
import com.anychart.enums.LegendLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageView a;
    LinearLayout add,cons;
    TextView total;
    String[] title={"titlea","titleb","titlec","titled","titlee","titlef"};
    String[] descr={"descrpa","descrpb","descrpc","descrpd","descrpe","descrpf"};
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        total =(TextView)findViewById(R.id.total);
        add=(LinearLayout)findViewById(R.id.AddLis);
        cons=(LinearLayout)findViewById(R.id.ConsultiLis);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(in);
            }
        });
        cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(MainActivity.this,Main22Activity.class);
                startActivity(in);
            }
        });
        String url="http://192.168.43.243:3000/Licences";
        StringRequest rq=new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    final JSONArray array = new JSONArray(response);

                    new CountDownTimer(25000, 5000) {

                        public void onTick(long millisUntilFinished) {
                            total.setText(Integer.toString(array.length()));
                        }

                        public void onFinish() {
                            total.setText(Integer.toString(array.length()));
                        }
                    }.start();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this," "+error,Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(rq);



    }
}
