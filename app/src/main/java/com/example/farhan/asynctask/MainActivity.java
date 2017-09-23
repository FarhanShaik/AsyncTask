package com.example.farhan.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class MainActivity extends AppCompatActivity {

    Button btn;
    String result;
    String strUrl = "http://s3.amazonaws.com/ametest-nbcuni/hello.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SyncTask().execute();

            }
        });
    }


    public class SyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {


            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(Void... params) {

            try {
                URL url = new URL(strUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                BufferedReader bf = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String value = bf.readLine();
                System.out.println("result: " + value);
                result = value;
            } catch (Exception e) {
                System.out.println(e);
            }

            return null;
        }
    }
}
