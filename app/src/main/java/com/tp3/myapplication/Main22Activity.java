package com.tp3.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Main22Activity extends AppCompatActivity {
    LinearLayout rl;
    ArrayList<String> title;
    ArrayList<String>  descr;
    ArrayList<String>  numr;
    TextView A;
    Button back;

    ListView lv;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultlicence);
        A=(TextView) findViewById(R.id.number);
        lv=(ListView)findViewById(R.id.mylist);

        title=new ArrayList<>();
        descr=new ArrayList<>();
        numr=new ArrayList<>();
        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent in=new Intent(Main22Activity.this,MainActivity.class);
                startActivity(in);
            }
        });

        String url="http://192.168.43.243:3000/Licences";
        StringRequest rq=new StringRequest(StringRequest.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray array = null;
                try {
                    array=new JSONArray(response);
                    for (int i=0;i<array.length();i++){
                        int j=i+1;
                        numr.add(" "+j);
                        JSONObject obj=array.getJSONObject(i);
                        title.add(obj.getString("_id"));
                        descr.add(obj.getString("enseigne"));
                       /* title[i]=obj.getString("enseigne");
                        descr[i]=obj.getString("siret");*/

                    }

                    MyAdapter adapter = new MyAdapter(Main22Activity.this,title,descr,numr);
                    lv.setAdapter(adapter);
                    setListViewHeightBasedOnChildren(lv);
                    registerForContextMenu(lv);
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            final Intent in=new Intent(Main22Activity.this,Licence.class);
                            in.putExtra("Id",title.get(position));
                            startActivity(in);
                        }
                    });
                }catch (JSONException ex){

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main22Activity.this," "+error,Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(this).add(rq);


    }

    //Listview in Scroll view need this function to calculate height
    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup)
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);

    }

        class MyAdapter extends ArrayAdapter<String> {

            Context context;
            ArrayList<String> rTitle;
            ArrayList<String> rdesc;
            ArrayList<String> numr;


            MyAdapter (Context c, ArrayList title,ArrayList desc,ArrayList<String> num) {
                super(c, R.layout.ligne, R.id.tit, title);
                this.context = c;
                this.rTitle = title;
                this.rdesc=desc;
                this.numr=num;


            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = layoutInflater.inflate(R.layout.ligne, parent, false);
                TextView myTitle = row.findViewById(R.id.tit);
                TextView myDesc = row.findViewById(R.id.tit1);
                TextView num = row.findViewById(R.id.number);


                // now set our resources on views
                myTitle.setText(rTitle.get(position));
                myDesc.setText(rdesc.get(position));
                num.setText(numr.get(position));





                return row;
            }
        }
    }