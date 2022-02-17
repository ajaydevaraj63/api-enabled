package com.example.mzccourseapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
AppCompatButton b1;
EditText ed1,ed2,ed3,ed4,ed5;
String title,venue,desc,date,duration;
String api_url="https://mountzioncollege.herokuapp.com/addcourse";

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.btn1);
        ed1=findViewById(R.id.title);
        ed2=findViewById(R.id.disc);
        ed3=findViewById(R.id.venue);
        ed4=findViewById(R.id.date);
        ed5=findViewById(R.id.duration);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=ed1.getText().toString();
                venue=ed2.getText().toString();
                desc=ed3.getText().toString();
                date=ed4.getText().toString();
               duration=ed5.getText().toString();

              StringRequest sr=new StringRequest(Request.Method.POST, api_url, new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                      Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();

                  }
              }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError error) {
                      Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                  }
              })
              {

                  @Override
                  protected Map<String, String> getParams() throws AuthFailureError {
                      HashMap<String,String> params=new HashMap<>();
                      params.put("courseTitle",title);
                      params.put("courseVenue",venue);
                      params.put("courseDescription",desc);
                      params.put("courseDate",date);
                      params.put("courseDuration",duration);

                      return params;
                  }
              };
           RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
           rq.add(sr);
            }
        });



    }

}